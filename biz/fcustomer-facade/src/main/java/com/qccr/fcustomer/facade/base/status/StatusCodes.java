package com.qccr.fcustomer.facade.base.status;

import com.qccr.knife.result.StateCode;

/**
 * 金融客户交互状态码
 *
 * @author yankaiqiang
 * @version $$Id: StatusCodes.java, v 0.1 2018/5/31 20:21 yankaiqiang Exp $$
 */
public interface StatusCodes {

    /**
     * 客户业务，代码04
     */
    StateCode CUSTOMER_NOT_EXISITS = new StateCode(-9620401, "客户不存在");


    /**
     * 商户业务，代码05
     */
    StateCode MERCHANT_NOT_EXISITS = new StateCode(-9620501, "商户不存在");

}
