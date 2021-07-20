package com.qccr.fcustomer.dal.model;

import java.util.List;

/**
 * @author yankaiqiang
 * @version $$Id: CustomerAccountQuery.java, v 0.1 2018/6/1 10:59 yankaiqiang Exp $$
 */
public class CustomerAccountQuery {
    /**
     * 客户ID
     */
    private Long customerId;

    private List<String> channels;

    private String accountNo;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }
}
