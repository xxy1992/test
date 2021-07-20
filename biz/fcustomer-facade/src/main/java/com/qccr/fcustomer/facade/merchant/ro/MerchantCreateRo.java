package com.qccr.fcustomer.facade.merchant.ro;

import java.io.Serializable;

/**
 * 收单商户创建基本信息
 *
 * @author yankaiqiang
 * @version $$Id: MerchantCreateRo.java, v 0.1 2018/5/30 16:16 yankaiqiang Exp $$
 */
public class MerchantCreateRo implements Serializable {

    private static final long serialVersionUID = -7304950285825942892L;

    /**
     * 企业客户信息
     */
    private Long customerId;

    /**
     * 收单结算业务渠道，
     * 空则全局通用
     */
    private String settleChannel;

    /**
     * 已有结算账号，则传入
     */
    private String settleAccount;

    private String createPerson;

    private String updatePerson;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getSettleChannel() {
        return settleChannel;
    }

    public void setSettleChannel(String settleChannel) {
        this.settleChannel = settleChannel;
    }

    public String getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(String settleAccount) {
        this.settleAccount = settleAccount;
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
