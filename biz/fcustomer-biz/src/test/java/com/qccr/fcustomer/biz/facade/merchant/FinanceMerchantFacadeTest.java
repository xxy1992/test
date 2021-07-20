package com.qccr.fcustomer.biz.facade.merchant;

import com.alibaba.fastjson.JSON;
import com.qccr.fcustomer.facade.merchant.FinanceMerchantFacade;
import com.qccr.fcustomer.facade.merchant.ro.FinaceMerchantAndEnterpriseRo;
import com.qccr.fcustomer.facade.merchant.ro.FinanceMerchantRo;
import com.qccr.fcustomer.facade.merchant.ro.MerchantQueryRo;
import com.qccr.knife.result.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author yankaiqiang
 * @version $$Id: FinanceMerchantFacadeTest.java, v 0.1 2018/6/7 11:07 yankaiqiang Exp $$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springbeans-biz-test.xml"})
public class FinanceMerchantFacadeTest {

    @Resource
    private FinanceMerchantFacade financeMerchantFacade;

    @Test
    public void testQuery() {
        MerchantQueryRo queryRo = new MerchantQueryRo();
        queryRo.setBizStoreId("1645");
        queryRo.setSettleChannel("502");
        Result<FinanceMerchantRo> rs = financeMerchantFacade.query(queryRo);
        System.out.println(JSON.toJSONString(rs));
    }

    @Test
    public void testQueryEnterprise() {
        MerchantQueryRo queryRo = new MerchantQueryRo();
        queryRo.setFaNo("334d5c5b509b01389fa08f849146249c");
        queryRo.setSettleChannel("TIANMAO_SERVICE");
//        queryRo.setSettleChannel("502");
        Result<FinaceMerchantAndEnterpriseRo> rs = financeMerchantFacade.queryEnterprise(queryRo);
        System.out.println(JSON.toJSONString(rs));
    }

}
