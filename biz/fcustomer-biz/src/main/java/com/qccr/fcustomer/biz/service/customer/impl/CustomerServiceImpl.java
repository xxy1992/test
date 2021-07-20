package com.qccr.fcustomer.biz.service.customer.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.qccr.commons.objects.ObjectConverter;
import com.qccr.commons.ro.PagedDataRO;
import com.qccr.fcustomer.biz.service.customer.CustomerCacheManager;
import com.qccr.fcustomer.biz.service.customer.CustomerService;
import com.qccr.fcustomer.common.utils.PageUtil;
import com.qccr.fcustomer.dal.mapper.*;
import com.qccr.fcustomer.dal.model.*;
import com.qccr.fcustomer.facade.base.constants.CustomerType;
import com.qccr.fcustomer.facade.base.constants.FinanceChannel;
import com.qccr.fcustomer.facade.base.status.StatusCodes;
import com.qccr.fcustomer.facade.customer.ro.*;
import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 客户服务实现
 *
 * @author yankaiqiang
 * @version $$Id: CustomerServiceImpl.java, v 0.1 2018/5/31 19:05 yankaiqiang Exp $$
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Resource
    private FinanceCustomerMapper financeCustomerMapper;

    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;

    @Resource
    private IndividualInfoMapper individualInfoMapper;

    @Resource
    private CustomerAccountMapper customerAccountMapper;

    @Resource
    private EnterpriseContactsMapper enterpriseContactsMapper;

    @Resource
    private BankCardMapper bankCardMapper;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public <T extends CustomerRo> Result<T> create(T customerRo) {

        if (customerRo instanceof EnterpriseCustomerRo) {
            return add((EnterpriseCustomerRo) customerRo);
        } else if (customerRo instanceof IndividualCustomerRo) {
            return add((IndividualCustomerRo) customerRo);
        }

        return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER);
    }

    private <T extends CustomerRo> Result<T> add(IndividualCustomerRo customerRo) {

        IndividualInfo individualInfo = new IndividualInfo();
        BeanUtils.copyProperties(customerRo, individualInfo);
        individualInfo.setId(null);

        try {
            individualInfoMapper.insertSelective(individualInfo);
        } catch (DuplicateKeyException e) {
            logger.warn("个人客户已创建, " + customerRo.getCustomerName());
            CustomerQueryRo customerQueryRo = new CustomerQueryRo();
            BeanUtils.copyProperties(customerRo, customerQueryRo);
            return this.query(customerQueryRo);
        }

        FinanceCustomer customer = new FinanceCustomer();
        customer.setCustomerType(CustomerType.INDIVIDUAL);
        customer.setCustomerName(customerRo.getCustomerName());
        customer.setCustomerInfoId(individualInfo.getId());
        financeCustomerMapper.insertSelective(customer);

        insertAccounts(customerRo.getAccountRos(), customer.getId());

        CustomerQueryRo queryRo = new CustomerQueryRo();
        queryRo.setCustomerId(customer.getId());
        return this.query(queryRo);

    }

    private <T extends CustomerRo> Result<T> add(EnterpriseCustomerRo customerRo) {

        Long individualInfoId = null;
        if (customerRo.getBizUserId() != null) {
            IndividualInfo individualInfo = new IndividualInfo();
            BeanUtils.copyProperties(customerRo, individualInfo);
            individualInfo.setBizUserId(Long.valueOf(customerRo.getBizUserId()));
            individualInfo.setId(null);
            individualInfo.setLegalName(customerRo.getLegalPersonName());
            try {
                individualInfoMapper.insertSelective(individualInfo);
            } catch (DuplicateKeyException e) {
                individualInfo = individualInfoMapper.selectByBizId(Long.valueOf(customerRo.getBizUserId()));
            }
            individualInfoId = individualInfo.getId();
        }

        Long cardId = null;
        if (customerRo.getCardNo() != null) {
            BankCard card = new BankCard();
            BeanUtils.copyProperties(customerRo, card);
            card.setId(null);
            try {
                bankCardMapper.insertSelective(card);
            } catch (DuplicateKeyException e) {
                logger.info("企业银行卡已录入" + card.getCardNo());
                card = bankCardMapper.selectByCardNoAndBankNo(card.getCardNo(), card.getBankNo());
            }
            cardId = card.getId();
        }

        EnterpriseInfo enterprise = new EnterpriseInfo();
        BeanUtils.copyProperties(customerRo, enterprise);
        if (customerRo.getBizUserId() != null) {
            enterprise.setBizUserId(Long.valueOf(customerRo.getBizUserId()));
        }
        enterprise.setLegalPersonId(individualInfoId);
        enterprise.setId(null);
        enterprise.setBankCardId(cardId);
        try {
            enterpriseInfoMapper.insertSelective(enterprise);
        } catch (DuplicateKeyException e) {
            logger.warn("企业客户已创建, " + customerRo.getCustomerName());
            CustomerQueryRo customerQueryRo = new CustomerQueryRo();
            customerQueryRo.setBizUserId(customerRo.getBizUserId());
            customerQueryRo.setBizStoreId(customerRo.getBizStoreId());
            customerQueryRo.setBizShopCode(customerRo.getBizShopCode());
            customerQueryRo.setCustomerType(CustomerType.ENTERPRISE);
            return this.query(customerQueryRo);
        }

        addContact(Optional.fromNullable(customerRo.getContactRos()).or(Collections.<ContactRo>emptyList()), enterprise.getId());

        FinanceCustomer customer = new FinanceCustomer();
        customer.setCustomerType(CustomerType.ENTERPRISE);
        customer.setCustomerName(customerRo.getCustomerName());
        customer.setCustomerInfoId(enterprise.getId());
        customer.setSourceChannel(customerRo.getSourceChannel());
        customer.setCreatePerson(customerRo.getCreatePerson());
        financeCustomerMapper.insertSelective(customer);

        insertAccounts(customerRo.getAccountRos(), customer.getId());

        CustomerQueryRo queryRo = new CustomerQueryRo();
        queryRo.setCustomerId(customer.getId());
        queryRo.setCustomerType(CustomerType.ENTERPRISE);
        return this.query(queryRo);
    }

    private void insertAccounts(List<CustomerAccountRo> accountRos, Long customerId) {
        if (CollectionUtils.isNotEmpty(accountRos)) {
            for (CustomerAccountRo customerAccountRo : accountRos) {
                CustomerAccount customerAccount = new CustomerAccount();
                BeanUtils.copyProperties(customerAccountRo, customerAccount);
                customerAccount.setCustomerId(customerId);
                customerAccountMapper.insertSelective(customerAccount);
            }
        }
    }

    @Override
    public <T extends CustomerRo> Result<T> update(T customerRo) {
        if (customerRo == null) {
            return Results.newFailedResult(CommonStateCode.PARAMETER_LACK);
        }

        if (customerRo instanceof EnterpriseCustomerRo) {
            return updateEnterpriseCustomer(customerRo);
        }

        return Results.newSuccessResult(customerRo);
    }

    @SuppressWarnings("unchecked")
    private <T extends CustomerRo> Result<T> updateEnterpriseCustomer(final T customer) {

        EnterpriseCustomerRo customerRo = (EnterpriseCustomerRo) customer;

        FinanceCustomer orginCustomer = financeCustomerMapper.selectByPrimaryKey(customerRo.getId());
        if (orginCustomer == null) {
            return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
        }

        EnterpriseInfo orginEnterprise = enterpriseInfoMapper.selectByPrimaryKey(orginCustomer.getCustomerInfoId());
        if (orginEnterprise == null) {
            return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
        }

        Long individualInfoId = null;
        if (orginEnterprise.getLegalPersonId() != null && customerRo.getBizUserId() != null) {
            IndividualInfo individualInfo = new IndividualInfo();
            BeanUtils.copyProperties(customerRo, individualInfo);
            individualInfo.setBizUserId(Long.valueOf(customerRo.getBizUserId()));
            individualInfo.setId(orginEnterprise.getLegalPersonId());
            individualInfo.setLegalName(customerRo.getLegalPersonName());
            individualInfoMapper.updateByPrimaryKeySelective(individualInfo);
            individualInfoId = orginEnterprise.getLegalPersonId();
        }

        BankCard bankCard = null;
        if (StringUtils.isNotBlank(customerRo.getCardNo())) {
            bankCard = new BankCard();
            BeanUtils.copyProperties(customerRo, bankCard);
            if (orginEnterprise.getBankCardId() != null) {
                bankCard.setId(orginEnterprise.getBankCardId());
                bankCardMapper.updateByPrimaryKeySelective(bankCard);
            } else {
                try {
                    bankCardMapper.insertSelective(bankCard);
                } catch (DuplicateKeyException e) {
                    logger.info("企业银行卡已录入" + bankCard.getCardNo());
                }
            }
        }

        if (customerRo.getBizStoreId() != null
                || customerRo.getBizUserId() != null
                || StringUtils.isNotBlank(customerRo.getEnterpriseName())
                || StringUtils.isNotBlank(customerRo.getBusinessLicenceNo())
                || StringUtils.isNotBlank(customerRo.getAddress())
                || bankCard != null) {
            EnterpriseInfo enterprise = new EnterpriseInfo();
            BeanUtils.copyProperties(customerRo, enterprise);
            if (customerRo.getBizUserId() != null) {
                enterprise.setBizUserId(Long.valueOf(customerRo.getBizUserId()));
            }
            enterprise.setLegalPersonId(individualInfoId);
            enterprise.setId(orginEnterprise.getId());
            if (bankCard != null) {
                enterprise.setBankCardId(bankCard.getId());
            }
            enterpriseInfoMapper.updateByPrimaryKeySelective(enterprise);
        }

        if (CollectionUtils.isNotEmpty(customerRo.getContactRos())) {
            updateContacts(customerRo.getContactRos(), orginEnterprise.getId());
        }

        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                CustomerCacheManager.refreshCache(customer.getId());
            }
        });

        return Results.newSuccessResult(customer);
    }

    private void updateContacts(List<ContactRo> contactRos, Long enterpriseId) {
        for (ContactRo contactRo : contactRos) {
            EnterpriseContacts enterpriseContacts = new EnterpriseContacts();
            BeanUtils.copyProperties(contactRo, enterpriseContacts);
            enterpriseContacts.setEnterpriseId(enterpriseId);
            if (contactRo.getId() != null) {
                enterpriseContactsMapper.updateByPrimaryKeySelective(enterpriseContacts);
                continue;
            }
            try {
                enterpriseContactsMapper.insertSelective(enterpriseContacts);
            } catch (DuplicateKeyException e) {
                logger.info("企业联系方式已录入");
            }
        }
    }

    private void addContact(List<ContactRo> contactRos, Long enterpriseId) {
        for (ContactRo contactRo : contactRos) {
            EnterpriseContacts enterpriseContacts = new EnterpriseContacts();
            BeanUtils.copyProperties(contactRo, enterpriseContacts);
            enterpriseContacts.setId(null);
            enterpriseContacts.setEnterpriseId(enterpriseId);
            try {
                enterpriseContactsMapper.insertSelective(enterpriseContacts);
            } catch (DuplicateKeyException e) {
                logger.info("企业联系方式已录入");
            }
        }
    }

    @Override
    public <T extends CustomerRo> Result<T> query(CustomerQueryRo customerQueryRo) {

        if (customerQueryRo.getCustomerType() == null) {
            return Results.newFailedResult(CommonStateCode.PARAMETER_LACK, "查询需要区分企业客户，还是个人客户");
        }

        Result<T> res;
        switch (customerQueryRo.getCustomerType()) {
            case CustomerType.INDIVIDUAL:
                res = queryIndividualCustomer(customerQueryRo);
                break;
            case CustomerType.ENTERPRISE:
                res = queryEnterpriseCustomer(customerQueryRo);
                break;
            default:
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
        }

        return res;
    }

    private <T extends CustomerRo> Result<T> queryEnterpriseCustomer(final CustomerQueryRo customerQueryRo) {

        T customerRo = CustomerCacheManager.get(customerQueryRo);
        if (customerRo != null) {
            filterAccount(customerRo, customerQueryRo.getAccountChannel());
            return Results.newSuccessResult(customerRo);
        }

        FinanceCustomer customer = null;
        EnterpriseInfo enterpriseInfo = null;
        IndividualInfo legalPersonInfo = null;

        if (customerQueryRo.getCustomerId() != null || StringUtils.isNotBlank(customerQueryRo.getCustomerName())) {
            CustomerQuery query = new CustomerQuery();
            query.setId(customerQueryRo.getCustomerId());
            query.setCustomerName(customerQueryRo.getCustomerName());
            query.setCustomerType(customerQueryRo.getCustomerType());
            customer = financeCustomerMapper.selectUniqueByQuery(query);
            if (customer == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
            enterpriseInfo = enterpriseInfoMapper.selectByPrimaryKey(customer.getCustomerInfoId());
            if (enterpriseInfo == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
        } else if (StringUtils.isNotBlank(customerQueryRo.getBizUserId())
                || StringUtils.isNotBlank(customerQueryRo.getBizStoreId())
                || StringUtils.isNotBlank(customerQueryRo.getBusinessLicenceNo())
                || StringUtils.isNotBlank(customerQueryRo.getBizShopCode())) {
            EnterpriseQuery query = new EnterpriseQuery();
            BeanUtils.copyProperties(customerQueryRo, query);
            if (customerQueryRo.getBizUserId() != null) {
                query.setBizUserId(Long.valueOf(customerQueryRo.getBizUserId()));
            }
            enterpriseInfo = enterpriseInfoMapper.queryByParams(query);
            if (enterpriseInfo == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
            CustomerQuery cquery = new CustomerQuery();
            cquery.setCustomerInfoId(enterpriseInfo.getId());
            cquery.setCustomerType(customerQueryRo.getCustomerType());
            customer = financeCustomerMapper.selectUniqueByQuery(cquery);
            if (customer == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
        } else if (StringUtils.isNotBlank(customerQueryRo.getAccountNo())) {
            CustomerAccountQuery accountQuery = new CustomerAccountQuery();
            accountQuery.setAccountNo(customerQueryRo.getAccountNo());
            if (customerQueryRo.getAccountChannel() != null) {
                List<String> channels = Lists.newLinkedList();
                channels.add(customerQueryRo.getAccountChannel());
                accountQuery.setChannels(channels);
            }

            CustomerAccount customerAccount = customerAccountMapper.selectUniqByParams(accountQuery);
            if (customerAccount == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
            CustomerQuery query = new CustomerQuery();
            query.setId(customerAccount.getCustomerId());
            customer = financeCustomerMapper.selectUniqueByQuery(query);
            if (customer == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
            enterpriseInfo = enterpriseInfoMapper.selectByPrimaryKey(customer.getCustomerInfoId());
            if (enterpriseInfo == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
        }

        if (customer == null) {
            return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
        }

        if (enterpriseInfo.getLegalPersonId() != null) {
            legalPersonInfo = individualInfoMapper.selectByPrimaryKey(enterpriseInfo.getLegalPersonId());
        }

        List<CustomerAccount> customerAccounts = getCustomerAccounts(customer.getId(), null);
        List<EnterpriseContacts> contacts = enterpriseContactsMapper.selectByEnterpriseInfoId(enterpriseInfo.getId());
        customerRo = buildEnterpriseCustomerRo(customer, customerAccounts, enterpriseInfo, legalPersonInfo, contacts);

        if (customerRo != null) {
            CustomerCacheManager.cache(customerQueryRo, customerRo);
            filterAccount(customerRo, customerQueryRo.getAccountChannel());
        }

        return Results.newSuccessResult(customerRo);

    }

    private CustomerRo filterAccount(CustomerRo customerRo, String accountChannel) {
        List<CustomerAccountRo> accountRos = customerRo.getAccountRos();

        if (accountChannel == null) {
            customerRo.setAccountRos(accountRos);
            return customerRo;
        }

        List<CustomerAccountRo> filtedAccountRos = Lists.newLinkedList();
        for (CustomerAccountRo accountRo : accountRos) {
            if (Objects.equals(accountChannel, accountRo.getChannel())) {
                filtedAccountRos.add(accountRo);
            } else if (FinanceChannel.BSUPER.equals(accountChannel)
                    && FinanceChannel.CSUPER.equals(accountRo.getChannel())) {
                filtedAccountRos.add(accountRo);
            } else if (FinanceChannel.CSUPER.equals(accountChannel)
                    && FinanceChannel.BSUPER.equals(accountRo.getChannel())) {
                filtedAccountRos.add(accountRo);
            }
        }

        customerRo.setAccountRos(filtedAccountRos);
        return customerRo;
    }

    private List<CustomerAccount> getCustomerAccounts(Long customerId, String accountChannel) {

        CustomerAccountQuery accountQuery = new CustomerAccountQuery();
        accountQuery.setCustomerId(customerId);

        if (accountChannel != null) {
            if (FinanceChannel.BSUPER.equals(accountChannel)
                    || FinanceChannel.CSUPER.equals(accountChannel)) {
                accountQuery.setChannels(Arrays.asList(FinanceChannel.BSUPER, FinanceChannel.CSUPER));
            } else {
                accountQuery.setChannels(Collections.singletonList(accountChannel));
            }
        }

        return customerAccountMapper.selectByParams(accountQuery);

    }

    private <T extends CustomerRo> Result<T> queryIndividualCustomer(CustomerQueryRo customerQueryRo) {

        FinanceCustomer customer = null;
        IndividualInfo individualInfo = null;

        if (customerQueryRo.getCustomerId() != null) {
            customer = financeCustomerMapper.selectByPrimaryKey(customerQueryRo.getCustomerId());
            if (customer == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
        }

        if (customerQueryRo.getBizUserId() != null) {
            individualInfo = individualInfoMapper.selectByBizId(Long.valueOf(customerQueryRo.getBizUserId()));
            if (individualInfo == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
            customer = financeCustomerMapper.selectByCustomerTypeAndInfoId(1, individualInfo.getId());
            if (customer == null) {
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
            }
        }

        if (customer == null) {
            return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
        }

        List<CustomerAccount> customerAccounts = getCustomerAccounts(customer.getId(), customerQueryRo.getAccountChannel());
        T customerRo = buildIndividualCustomerRo(customer, customerAccounts, individualInfo);
        return Results.newSuccessResult(customerRo);
    }

    @SuppressWarnings("unchecked")
    private <T extends CustomerRo> T buildEnterpriseCustomerRo(FinanceCustomer customer, List<CustomerAccount> customerAccounts,
                                                               EnterpriseInfo enterpriseInfo, IndividualInfo individualInfo,
                                                               List<EnterpriseContacts> contacts) {
        EnterpriseCustomerRo enterpriseCustomerRo = new EnterpriseCustomerRo();
        if (individualInfo != null) {
            BeanUtils.copyProperties(individualInfo, enterpriseCustomerRo);
            enterpriseCustomerRo.setLegalPersonId(individualInfo.getId());
        }
        BeanUtils.copyProperties(enterpriseInfo, enterpriseCustomerRo);
        if (enterpriseInfo.getBizUserId() != null) {
            enterpriseCustomerRo.setBizUserId(enterpriseInfo.getBizUserId() + "");
        }
        BeanUtils.copyProperties(customer, enterpriseCustomerRo);
        setAccounts(enterpriseCustomerRo, customerAccounts);
        setContacts(enterpriseCustomerRo, contacts);
        return (T) enterpriseCustomerRo;
    }

    @SuppressWarnings("unchecked")
    private <T extends CustomerRo> T buildIndividualCustomerRo(FinanceCustomer customer, List<CustomerAccount> customerAccounts,
                                                               IndividualInfo individualInfo) {
        IndividualCustomerRo individualCustomerRo = new IndividualCustomerRo();
        BeanUtils.copyProperties(individualInfo, individualCustomerRo);
        BeanUtils.copyProperties(customer, individualCustomerRo);
        setAccounts(individualCustomerRo, customerAccounts);
        return (T) individualCustomerRo;
    }

    private void setContacts(EnterpriseCustomerRo customerRo, List<EnterpriseContacts> contacts) {
        if (CollectionUtils.isEmpty(contacts)) {
            customerRo.setContactRos(Collections.<ContactRo>emptyList());
            return;
        }

        List<ContactRo> contactRos = new LinkedList<>();
        for (EnterpriseContacts contact : contacts) {
            ContactRo contactRo = new ContactRo();
            BeanUtils.copyProperties(contact, contactRo);
            contactRos.add(contactRo);
        }

        customerRo.setContactRos(contactRos);
    }

    private void setAccounts(CustomerRo customerRo, List<CustomerAccount> customerAccounts) {
        if (CollectionUtils.isEmpty(customerAccounts)) {
            customerRo.setAccountRos(Collections.<CustomerAccountRo>emptyList());
            return;
        }

        List<CustomerAccountRo> accountRos = new LinkedList<>();
        for (CustomerAccount account : customerAccounts) {
            CustomerAccountRo accountRo = new CustomerAccountRo();
            BeanUtils.copyProperties(account, accountRo);
            accountRos.add(accountRo);
        }

        customerRo.setAccountRos(accountRos);
    }

    @Override
    public <T extends CustomerRo> Result<PagedDataRO<T>> pageQuery(CustomerQueryRo customerQueryRo) {

        if (customerQueryRo.getPage() == null || customerQueryRo.getPageSize() == null || customerQueryRo.getPageSize() > 30) {
            return Results.newFailedResult(CommonStateCode.PARAMETER_LACK, "分页参数必传，且每页小于等于30");
        }

        if (customerQueryRo.getCustomerType() == null) {
            return Results.newFailedResult(CommonStateCode.PARAMETER_LACK, "查询需要区分企业客户，还是个人客户");
        }

        switch (customerQueryRo.getCustomerType()) {
            case CustomerType.INDIVIDUAL:
                return queryIndividualCustomers(customerQueryRo);
            case CustomerType.ENTERPRISE:
                return queryEnterpriseCustomers(customerQueryRo);
            default:
                return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS);
        }

    }

    /**
     * 目前没有个人客户
     */
    private <T extends CustomerRo> Result<PagedDataRO<T>> queryIndividualCustomers(CustomerQueryRo customerQueryRo) {
        return null;
    }

    private <T extends CustomerRo> Result<PagedDataRO<T>> queryEnterpriseCustomers(CustomerQueryRo customerQueryRo) {

        CustomerQuery customerQuery = new CustomerQuery();
        BeanUtils.copyProperties(customerQueryRo, customerQuery);
        customerQuery.setId(customerQueryRo.getCustomerId());
        customerQuery.setStart(PageUtil.getStart(customerQueryRo.getPage(), customerQueryRo.getPageSize()));
        customerQuery.setLimit(customerQueryRo.getPageSize());

        List<FinanceCustomer> financeCustomers = financeCustomerMapper.selectEnterpriseCustomerByQuery(customerQuery);
        List<T> enterpriseCustomerRos = Lists.newLinkedList();

        EnterpriseInfo enterpriseInfo;
        IndividualInfo legalPersonInfo = null;
        for (FinanceCustomer customer : financeCustomers) {

            enterpriseInfo = enterpriseInfoMapper.selectByPrimaryKey(customer.getCustomerInfoId());
            if (enterpriseInfo.getLegalPersonId() != null) {
                legalPersonInfo = individualInfoMapper.selectByPrimaryKey(enterpriseInfo.getLegalPersonId());
            }
            List<CustomerAccount> customerAccounts = getCustomerAccounts(customer.getId(), customerQueryRo.getAccountChannel());
            List<EnterpriseContacts> contacts = enterpriseContactsMapper.selectByEnterpriseInfoId(enterpriseInfo.getId());

            T customerRo = buildEnterpriseCustomerRo(customer, customerAccounts, enterpriseInfo, legalPersonInfo, contacts);

            enterpriseCustomerRos.add(customerRo);
        }

        PagedDataRO<T> page = new PagedDataRO<>();
        page.setResultList(enterpriseCustomerRos);
        page.setPageNo(customerQueryRo.getPage());
        page.setPageSize(customerQueryRo.getPageSize());
        int total = financeCustomerMapper.countEnterpriseCustomerByQuery(customerQuery);
        page.setTotalSize(total);

        return Results.newSuccessResult(page);
    }

    @Override
    public List<AccountAndEnterpriseInfoRo> queryAccountAndEnterpriseInfo(CustomerQueryRo customerQueryRo) {

        CustomerQuery customerQuery = new CustomerQuery();
        BeanUtils.copyProperties(customerQueryRo, customerQuery);
        List<AccountAndEnterpriseInfo> accountAndEnterpriseInfoList =
                financeCustomerMapper.queryAccountAndEnterpriseInfo(customerQuery);
        return ObjectConverter.convertList(accountAndEnterpriseInfoList, AccountAndEnterpriseInfoRo.class);
    }

}
