package com.qccr.fcustomer.facade.customer;

import com.qccr.fcustomer.facade.customer.ro.CustomerAccountRo;
import com.qccr.fcustomer.facade.customer.ro.CustomerQueryRo;
import com.qccr.knife.result.Result;

import java.util.List;

/**
 * 客户账户接口服务, 查询客户账号信息服务
 *
 * @author yankaiqiang
 * @version $$Id: CustomerAccountFacade.java, v 0.1 2018/5/30 16:46 yankaiqiang Exp $$
 */
public interface CustomerAccountFacade {


    /**
     * 查询客户账户信息
     *
     * @return
     */
    Result<CustomerAccountRo> query(CustomerQueryRo customerQueryRo);


    /**
     * 添加客户账号, 用于已有账号，需要和客户关联
     *
     * @return 失败的关联列表
     */
    Result<List<CustomerAccountRo>> addAccount(List<CustomerAccountRo> customerAccountRos);

}
