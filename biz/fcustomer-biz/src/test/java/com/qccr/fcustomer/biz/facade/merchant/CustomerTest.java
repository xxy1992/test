package com.qccr.fcustomer.biz.facade.merchant;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qccr.commons.ro.PagedDataRO;
import com.qccr.fcustomer.facade.base.constants.FinanceChannel;
import com.qccr.fcustomer.facade.customer.CustomerAccountFacade;
import com.qccr.fcustomer.facade.customer.FinanceCustomerFacade;
import com.qccr.fcustomer.facade.customer.ro.CustomerAccountRo;
import com.qccr.fcustomer.facade.customer.ro.CustomerQueryRo;
import com.qccr.fcustomer.facade.customer.ro.EnterpriseCustomerAccountRo;
import com.qccr.fcustomer.facade.customer.ro.EnterpriseCustomerRo;
import com.qccr.knife.result.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户测试
 *
 * @author yankaiqiang
 * @version $$Id: CustomerTest.java, v 0.1 2018/7/9 10:19 yankaiqiang Exp $$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:biz-test.xml"})
public class CustomerTest {

    @Resource
    private FinanceCustomerFacade financeCustomerFacade;

    @Resource
    private CustomerAccountFacade customerAccountFacade;

    @Test
    public void testQuery() {
        CustomerQueryRo queryRo = new CustomerQueryRo();
        //queryRo.setBusinessLicenceNos(Collections.singletonList("JY123123"));
        //queryRo.setBusinessLicenceNo("JY123123");
        //queryRo.setBizStoreId("1600465");
        queryRo.setBizStoreId("1600835");
        //queryRo.setAccountChannel(FinanceChannel.BSUPER);
        //queryRo.setPage(1);
        //queryRo.setPageSize(30);
        //queryRo.setAccountChannel(FinanceChannelEnum.BSUPER.getValue());
        System.out.println("-------------------------------------------");

        Result query = financeCustomerFacade.queryEnterpriseCustomer(queryRo);

        System.out.println(JSON.toJSONString(query));

        System.out.println("-------------------------------------------");
    }

    @Test
    public void testUpdateAccountQuery() {

        testQuery();

        EnterpriseCustomerRo ro = new EnterpriseCustomerRo();
        ro.setId(35L);
        ro.setEnterpriseName("test0607100001");

        Result<EnterpriseCustomerRo> rs = financeCustomerFacade.updateEnterpriseCustomer(ro);

        System.out.println(rs);

        testQuery();

        System.out.println("-------------------------------------------");
    }

    @Test
    public void testUpdateQuery() {

        //testQuery();

        List<CustomerAccountRo> customerAccountRos = Lists.newArrayList();
        CustomerAccountRo accountRo = new CustomerAccountRo();
        accountRo.setAccountNo("123123142222tests");
        accountRo.setCustomerId(35L);
        accountRo.setChannel(FinanceChannel.CSUPER);
        customerAccountRos.add(accountRo);

        Result<List<CustomerAccountRo>> rs = customerAccountFacade.addAccount(customerAccountRos);

        System.out.println(rs);

        testQuery();

        System.out.println("-------------------------------------------");
    }

    @Test
    public void pageQueryEnterpriseCustomerAccountForManagerTest() {
        CustomerQueryRo queryRo = new CustomerQueryRo();

        queryRo.setEnterpriseName("2");
        queryRo.setAccountChannel(FinanceChannel.CSUPER);
        queryRo.setPage(1);
        queryRo.setPageSize(30);
        Result<PagedDataRO<EnterpriseCustomerAccountRo>> rt = financeCustomerFacade.pageQueryEnterpriseCustomerAccountForManager(queryRo);

        System.out.println(rt);

        System.out.println("-------------------------------------------");

    }

}
