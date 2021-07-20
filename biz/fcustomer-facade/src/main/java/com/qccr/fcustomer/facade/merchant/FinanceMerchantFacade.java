package com.qccr.fcustomer.facade.merchant;


import com.qccr.fcustomer.facade.merchant.ro.FinaceMerchantAndEnterpriseRo;
import com.qccr.fcustomer.facade.merchant.ro.FinanceMerchantRo;
import com.qccr.fcustomer.facade.merchant.ro.MerchantCreateRo;
import com.qccr.fcustomer.facade.merchant.ro.MerchantQueryRo;
import com.qccr.knife.result.Result;

/**
 * 金融收单商户
 * 需要结算的商户，需要创建收单商户, 支付后自动结算
 * <p>
 * 只是资金管理的创建企业客户即可
 *
 * @author yankaiqiang
 * @version $$Id: FinanceMerchantFacade.java, v 0.1 2018/5/30 16:15 yankaiqiang Exp $$
 */
public interface FinanceMerchantFacade {

    /**
     * 创建收单商户, 需要结算的商户，需要创建收单商户
     * <p>
     * 只是资金管理的创建企业客户即可
     *
     * @param merchantCreateRo 创建信息
     * @return
     */
    Result<FinanceMerchantRo> create(MerchantCreateRo merchantCreateRo);


    /**
     * 查询收单商户信息
     *
     * @param merchantQueryRo 查询信息
     * @return
     */
    Result<FinanceMerchantRo> query(MerchantQueryRo merchantQueryRo);

    /**
     * 查询收单商户信息
     *
     * @param merchantQueryRo 查询信息
     * @return
     */
    Result<FinaceMerchantAndEnterpriseRo> queryEnterprise(MerchantQueryRo merchantQueryRo);

}
