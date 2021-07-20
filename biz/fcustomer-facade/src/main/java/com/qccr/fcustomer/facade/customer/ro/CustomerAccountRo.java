package com.qccr.fcustomer.facade.customer.ro;

import java.io.Serializable;

/**
 * 客户账户复合信息ro
 *
 * @author yankaiqiang
 * @version $$Id: CustomerAccountRo.java, v 0.1 2018/5/30 16:43 yankaiqiang Exp $$
 */
public class CustomerAccountRo implements Serializable {

    private static final long serialVersionUID = -9137572533487937754L;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 客户资金账号
     */
    private String accountNo;

    /**
     * 客户资金账号开通渠道 {@link com.qccr.fcustomer.facade.base.constants.FinanceChannel}
     */
    private String channel;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
