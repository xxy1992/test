package com.qccr.fcustomer.facade.merchant.ro;

import java.io.Serializable;

/**
 * 金融商户
 *
 * @author yankaiqiang
 * @version $$Id: FinanceMerchantRo.java, v 0.1 2018/5/30 16:20 yankaiqiang Exp $$
 */
public class FinanceMerchantRo implements Serializable {

    private static final long serialVersionUID = -6017811015350258977L;

    private Long id;

    private String mchNo;

    private Long customerId;

    private String bizChannel;

    private String settleAccount;

    private String customerName;

    private String enterpriseName;

    private Long customerInfoId;

    public Long getCustomerInfoId() {
        return customerInfoId;
    }

    public void setCustomerInfoId(Long customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

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
        this.mchNo = mchNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getBizChannel() {
        return bizChannel;
    }

    public void setBizChannel(String bizChannel) {
        this.bizChannel = bizChannel;
    }

    public String getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(String settleAccount) {
        this.settleAccount = settleAccount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
