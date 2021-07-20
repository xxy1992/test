package com.qccr.fcustomer.biz.facade.customer;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qccr.fcustomer.biz.service.customer.CustomerCacheManager;
import com.qccr.fcustomer.biz.service.customer.CustomerService;
import com.qccr.fcustomer.common.log.FacadeLogUtils;
import com.qccr.fcustomer.dal.mapper.CustomerAccountMapper;
import com.qccr.fcustomer.dal.model.CustomerAccount;
import com.qccr.fcustomer.facade.customer.CustomerAccountFacade;
import com.qccr.fcustomer.facade.customer.ro.CustomerAccountRo;
import com.qccr.fcustomer.facade.customer.ro.CustomerQueryRo;
import com.qccr.fcustomer.facade.customer.ro.CustomerRo;
import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 客户账号接口实现
 *
 * @author yankaiqiang
 * @version $$Id: CustomerAccountFacadeImpl.java, v 0.1 2018/6/1 10:51 yankaiqiang Exp $$
 */
@Service("customerAccountFacade")
public class CustomerAccountFacadeImpl implements CustomerAccountFacade {

    private static Logger logger = LoggerFactory.getLogger(CustomerAccountFacadeImpl.class);

    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerAccountMapper customerAccountMapper;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public Result<CustomerAccountRo> query(CustomerQueryRo customerQueryRo) {

        FacadeLogUtils.info(logger, customerQueryRo, "queryCustomerAccount");

        Result<CustomerRo> rs = customerService.query(customerQueryRo);

        if (rs.isFailed()) {
            return Results.newFailedResult(rs.getStateCode());
        }

        List<CustomerAccountRo> customerAccounts = rs.getData().getAccountRos();

        FacadeLogUtils.info(logger, customerQueryRo, customerAccounts, "queryCustomerAccount");

        if (CollectionUtils.isEmpty(customerAccounts)) {
            return Results.newSuccessResult(null);
        }

        CustomerAccountRo ro = customerAccounts.get(0);

        return Results.newSuccessResult(ro);

    }

    @Override
    public Result<List<CustomerAccountRo>> addAccount(List<CustomerAccountRo> customerAccountRos) {
        if (CollectionUtils.isEmpty(customerAccountRos)) {
            return Results.newFailedResult(CommonStateCode.PARAMETER_LACK);
        }

        List<CustomerAccountRo> failAccounts = Lists.newLinkedList();

        final Set<Long> customerIds = new HashSet<>(customerAccountRos.size());

        for (CustomerAccountRo customerAccountRo : customerAccountRos) {
            if (customerAccountRo.getCustomerId() == null
                    || customerAccountRo.getAccountNo() == null || customerAccountRo.getChannel() == null) {
                failAccounts.add(customerAccountRo);
            }

            CustomerAccount customerAccount = new CustomerAccount();
            BeanUtils.copyProperties(customerAccountRo, customerAccount);
            try {
                customerAccountMapper.insertSelective(customerAccount);
                customerIds.add(customerAccountRo.getCustomerId());
            } catch (DuplicateKeyException e) {
                logger.info("账号已关联, " + JSON.toJSONString(customerAccount));
            }
        }

        if (CollectionUtils.isNotEmpty(failAccounts)) {
            return Results.newFailedResult(failAccounts, CommonStateCode.ILLEGAL_PARAMETER);
        }

        if (CollectionUtils.isNotEmpty(customerIds)) {
            threadPoolTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    for (Long customerId : customerIds) {
                        CustomerCacheManager.refreshCache(customerId);
                    }
                }
            });
        }

        return Results.newSuccessResult(failAccounts);
    }
}
