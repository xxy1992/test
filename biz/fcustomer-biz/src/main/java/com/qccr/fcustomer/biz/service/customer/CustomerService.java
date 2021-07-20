package com.qccr.fcustomer.biz.service.customer;

import com.qccr.commons.ro.PagedDataRO;
import com.qccr.fcustomer.dal.model.AccountAndEnterpriseInfo;
import com.qccr.fcustomer.dal.model.CustomerQuery;
import com.qccr.fcustomer.facade.customer.ro.AccountAndEnterpriseInfoRo;
import com.qccr.fcustomer.facade.customer.ro.CustomerQueryRo;
import com.qccr.fcustomer.facade.customer.ro.CustomerRo;
import com.qccr.knife.result.Result;

import java.util.List;

/**
 * 金融客户服务
 *
 * @author yankaiqiang
 * @version $$Id: CustomerService.java, v 0.1 2018/5/31 18:54 yankaiqiang Exp $$
 */
public interface CustomerService {

    /**
     * 增加新的客户
     *
     * @param customerRo 客户信息
     * @return
     */
    <T extends CustomerRo> Result<T> create(T customerRo);

    /**
     * 更新客户信息
     *
     * @param customerRo 客户信息
     * @return
     */
    <T extends CustomerRo> Result<T> update(T customerRo);


    /**
     * 查询客户信息
     *
     * @param customerQueryRo 客户信息
     * @return
     */
    <T extends CustomerRo> Result<T> query(CustomerQueryRo customerQueryRo);

    /**
     * 查询客户信息
     *
     * @param customerQueryRo 客户信息
     * @return
     */
    <T extends CustomerRo> Result<PagedDataRO<T>> pageQuery(CustomerQueryRo customerQueryRo);

    /**
     * 查询带企业信息的账户信息
     * @param customerQueryRo
     * @return
     */
    List<AccountAndEnterpriseInfoRo> queryAccountAndEnterpriseInfo(CustomerQueryRo customerQueryRo);
}
