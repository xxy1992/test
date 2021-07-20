package com.qccr.fcustomer.facade.customer.ro;

import java.io.Serializable;
import java.util.List;

/**
 * 客户Ro
 *
 * @author yankaiqiang
 * @version $$Id: CustomerRo.java, v 0.1 2018/5/30 15:54 yankaiqiang Exp $$
 */
public class CustomerRo implements Serializable {

    private static final long serialVersionUID = -2734008067960366753L;

    /**
     * 金融客户ID
     */
    private Long id;

    /**
     * 客户类型 {@link com.qccr.fcustomer.facade.base.constants.CustomerType}
     */
    private Integer customerType;

    /**
     * 客户名称，默认需要传递 店铺名称
     */
    private String customerName;

    /**
     * 创建客户来源渠道, {@link com.qccr.fcustomer.facade.base.constants.FinanceChannel}
     */
    private String sourceChannel;

    /**
     * 客户的资金账号
     */
    private List<CustomerAccountRo> accountRos;

    private String createPerson;

    private String updatePerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<CustomerAccountRo> getAccountRos() {
        return accountRos;
    }

    public void setAccountRos(List<CustomerAccountRo> accountRos) {
        this.accountRos = accountRos;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }
}
