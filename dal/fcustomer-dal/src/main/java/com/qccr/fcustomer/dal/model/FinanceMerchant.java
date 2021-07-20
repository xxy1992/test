package com.qccr.fcustomer.dal.model;

import java.util.Date;

public class FinanceMerchant {
    private Long id;

    private String mchNo;

    private Long customerId;

    private String settleChannel;

    private String settleAccount;

    private Date createTime;

    private String createPerson;

    private Date updateTime;

    private String updatePerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo == null ? null : mchNo.trim();
    }

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
        this.settleChannel = settleChannel == null ? null : settleChannel.trim();
    }

    public String getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(String settleAccount) {
        this.settleAccount = settleAccount == null ? null : settleAccount.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }
}