package com.qccr.fcustomer.facade.customer;

import com.qccr.commons.ro.PagedDataRO;
import com.qccr.fcustomer.facade.customer.ro.*;
import com.qccr.knife.result.Result;

import java.util.List;


/**
 * 提供金融客户信息查询基础接口
 *
 * @author yankaiqiang
 * @version $$Id: FinanceCustomerFacade.java, v 0.1 2018/5/30 15:53 yankaiqiang Exp $$
 */
public interface FinanceCustomerFacade {

    /**
     * 创建个人客户
     *
     * @param individualCustomerRo 个人客户
     * @return
     */
    Result<IndividualCustomerRo> createIndividualCustomer(IndividualCustomerRo individualCustomerRo);

    /**
     * 查询个人客户信息
     *
     * @return
     */
    Result<IndividualCustomerRo> queryIndividualCustomer(CustomerQueryRo customerQueryRo);


    /**
     * 创建企业客户信息
     *
     * @param enterpriseCustomerRo 企业客户
     * @return
     */
    Result<EnterpriseCustomerRo> createEnterpriseCustomer(EnterpriseCustomerRo enterpriseCustomerRo);


    /**
     * 更新企业客户信息
     *
     * @param enterpriseCustomerRo 企业客户
     * @return
     */
    Result<EnterpriseCustomerRo> updateEnterpriseCustomer(EnterpriseCustomerRo enterpriseCustomerRo);


    /**
     * 查询企业客户信息
     *
     * @return
     */
    Result<EnterpriseCustomerRo> queryEnterpriseCustomer(CustomerQueryRo customerQueryRo);


    /**
     * 批量查询企业客户信息
     *
     * @return
     */
    Result<PagedDataRO<EnterpriseCustomerRo>> pageQueryEnterpriseCustomer(CustomerQueryRo customerQueryRo);

    /**
     * 批量查询企业客户信息
     *
     * @return
     */
    Result<PagedDataRO<EnterpriseCustomerAccountRo>> pageQueryEnterpriseCustomerAccountForManager(CustomerQueryRo customerQueryRo);


    Result<List<AccountAndEnterpriseInfoRo>> queryAccountAndEnterpriseInfo(CustomerQueryRo customerQueryRo);
}
