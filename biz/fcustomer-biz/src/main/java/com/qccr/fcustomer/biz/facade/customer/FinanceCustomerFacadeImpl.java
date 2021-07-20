package com.qccr.fcustomer.biz.facade.customer;

import com.google.common.collect.Lists;
import com.qccr.commons.objects.ObjectConverter;
import com.qccr.commons.ro.PagedDataRO;
import com.qccr.fcustomer.biz.service.customer.CustomerService;
import com.qccr.fcustomer.common.log.FacadeLogUtils;
import com.qccr.fcustomer.common.utils.PageUtil;
import com.qccr.fcustomer.dal.mapper.CustomerAccountMapper;
import com.qccr.fcustomer.dal.model.CustomerQuery;
import com.qccr.fcustomer.dal.model.EnterpriseCustomerAccount;
import com.qccr.fcustomer.facade.base.constants.CustomerType;
import com.qccr.fcustomer.facade.customer.FinanceCustomerFacade;
import com.qccr.fcustomer.facade.customer.ro.*;
import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 提供金融客户信息查询基础接口 实现
 *
 * @author yankaiqiang
 * @version $$Id: FinanceCustomerFacadeImpl.java, v 0.1 2018/5/31 17:45 yankaiqiang Exp $$
 */
@Service("financeCustomerFacade")
public class FinanceCustomerFacadeImpl implements FinanceCustomerFacade {

    private static Logger logger = LoggerFactory.getLogger(FinanceCustomerFacadeImpl.class);

    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerAccountMapper customerAccountMapper;

    @Override
    public Result<IndividualCustomerRo> createIndividualCustomer(IndividualCustomerRo individualCustomerRo) {
        //一期不验证业务参数
        FacadeLogUtils.info(logger, individualCustomerRo, "createIndividualCustomer");

        Result<IndividualCustomerRo> rs = customerService.create(individualCustomerRo);

        FacadeLogUtils.info(logger, individualCustomerRo, rs, "createIndividualCustomer");

        return rs;
    }

    @Override
    public Result<EnterpriseCustomerRo> createEnterpriseCustomer(EnterpriseCustomerRo enterpriseCustomerRo) {
        //一期不验证业务参数
        FacadeLogUtils.info(logger, enterpriseCustomerRo, "createEnterpriseCustomer");

        if (enterpriseCustomerRo.getBizStoreId() == null && enterpriseCustomerRo.getBizUserId() == null && enterpriseCustomerRo.getBizShopCode()==null) {
            return Results.newFailedResult(CommonStateCode.PARAMETER_LACK, "登记客户，业务门店ID,业务用户ID,shopcode标识不能同时为空");
        }

        Result<EnterpriseCustomerRo> rs = customerService.create(enterpriseCustomerRo);

        FacadeLogUtils.info(logger, enterpriseCustomerRo, rs, "createEnterpriseCustomer");

        return rs;
    }

    @Override
    public Result<EnterpriseCustomerRo> updateEnterpriseCustomer(EnterpriseCustomerRo enterpriseCustomerRo) {
        //一期不验证业务参数
        FacadeLogUtils.info(logger, enterpriseCustomerRo, "updateEnterpriseCustomer");

        if (enterpriseCustomerRo.getBizStoreId() == null && enterpriseCustomerRo.getBizUserId() == null && enterpriseCustomerRo.getId() == null) {
            return Results.newFailedResult(CommonStateCode.PARAMETER_LACK, "更新客户信息，客户ID,业务门店ID和业务用户ID标识不能同时为空");
        }

        Result<EnterpriseCustomerRo> rs = customerService.update(enterpriseCustomerRo);

        FacadeLogUtils.info(logger, enterpriseCustomerRo, rs, "updateEnterpriseCustomer");

        return rs;
    }

    @Override
    public Result<IndividualCustomerRo> queryIndividualCustomer(CustomerQueryRo customerQueryRo) {
        //一期不验证业务参数
        FacadeLogUtils.info(logger, customerQueryRo, "customerQuery");

        Result<CustomerRo> rs = customerService.query(customerQueryRo);

        FacadeLogUtils.info(logger, customerQueryRo, rs, "customerQuery");

        return Results.newResult((IndividualCustomerRo) rs.getData(), rs.getStateCode(), rs.getStatusText());
    }

    @Override
    public Result<EnterpriseCustomerRo> queryEnterpriseCustomer(CustomerQueryRo customerQueryRo) {
        //一期不验证业务参数
        FacadeLogUtils.info(logger, customerQueryRo, "customerQuery");

        customerQueryRo.setCustomerType(CustomerType.ENTERPRISE);
        Result<CustomerRo> rs = customerService.query(customerQueryRo);

        FacadeLogUtils.info(logger, customerQueryRo, rs, "customerQuery");

        return Results.newResult((EnterpriseCustomerRo) rs.getData(), rs.getStateCode(), rs.getStatusText());
    }

    @Override
    public Result<PagedDataRO<EnterpriseCustomerRo>> pageQueryEnterpriseCustomer(CustomerQueryRo customerQueryRo) {

        FacadeLogUtils.info(logger, customerQueryRo, "customerQuery");

        customerQueryRo.setCustomerType(CustomerType.ENTERPRISE);

        return customerService.pageQuery(customerQueryRo);
    }

    @Override
    public Result<PagedDataRO<EnterpriseCustomerAccountRo>> pageQueryEnterpriseCustomerAccountForManager(CustomerQueryRo customerQueryRo) {

        CustomerQuery customerQuery = new CustomerQuery();
        BeanUtils.copyProperties(customerQueryRo, customerQuery);
        customerQuery.setId(customerQueryRo.getCustomerId());
        customerQuery.setStart(PageUtil.getStart(customerQueryRo.getPage(), customerQueryRo.getPageSize()));
        customerQuery.setLimit(customerQueryRo.getPageSize());
        if (!StringUtils.isEmpty(customerQueryRo.getBizStoreId())) {
            if (customerQuery.getBizStoreIds() != null) {
                customerQuery.getBizStoreIds().add(customerQueryRo.getBizStoreId());
            } else {
                List<String> storeIds = Lists.newLinkedList();
                storeIds.add(customerQueryRo.getBizStoreId());
                customerQuery.setBizStoreIds(storeIds);
            }
        }
        if (!StringUtils.isEmpty(customerQueryRo.getBizUserId())) {
            if (customerQuery.getBizUserIds() != null) {
                customerQuery.getBizUserIds().add(customerQueryRo.getBizUserId());
            } else {
                List<String> userIds = Lists.newLinkedList();
                userIds.add(customerQueryRo.getBizUserId());
                customerQuery.setBizUserIds(userIds);
            }
        }
        if (!StringUtils.isEmpty(customerQueryRo.getBusinessLicenceNo())) {
            if (customerQuery.getBusinessLicenceNos() != null) {
                customerQuery.getBusinessLicenceNos().add(customerQueryRo.getBusinessLicenceNo());
            } else {
                List<String> lincenceNos = Lists.newLinkedList();
                lincenceNos.add(customerQueryRo.getBusinessLicenceNo());
                customerQuery.setBusinessLicenceNos(lincenceNos);
            }
        }
        PagedDataRO<EnterpriseCustomerAccountRo> page = new PagedDataRO<>();

        page.setPageNo(customerQueryRo.getPage());
        page.setPageSize(customerQueryRo.getPageSize());

        Long total = customerAccountMapper.countEnterpriseCustomerAccountByQuery(customerQuery);
        if (total.compareTo(0L) > 0) {
            page.setTotalSize(total.intValue());
            List<EnterpriseCustomerAccount> customerAccounts = customerAccountMapper.selectEnterpriseCustomerAccountByQuery(customerQuery);
            if (!CollectionUtils.isEmpty(customerAccounts)) {
                page.setResultList(ObjectConverter.convertList(customerAccounts, EnterpriseCustomerAccountRo.class));
            }
        } else {
            page.setTotalSize(0);
        }
        return Results.newSuccessResult(page);
    }

    @Override
    public Result<List<AccountAndEnterpriseInfoRo>> queryAccountAndEnterpriseInfo(CustomerQueryRo customerQueryRo) {
        FacadeLogUtils.info(logger, customerQueryRo, "queryAccountAndEnterpriseInfo");
        List<AccountAndEnterpriseInfoRo> infoRoList = null;
        try {
            customerQueryRo.setCustomerType(CustomerType.ENTERPRISE);
            infoRoList = customerService.queryAccountAndEnterpriseInfo(customerQueryRo);
        } catch (Exception e) {
            FacadeLogUtils.error(logger, customerQueryRo, "queryAccountAndEnterpriseInfo");
        }

        return Results.newSuccessResult(infoRoList);
    }
}
