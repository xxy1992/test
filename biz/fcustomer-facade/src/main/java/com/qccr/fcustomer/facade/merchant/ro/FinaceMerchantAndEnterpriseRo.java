/**
 * qccr.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.qccr.fcustomer.facade.merchant.ro;

import java.io.Serializable;

/**
 * @author yangjinwei
 * @create 2018-09-13 11:11
 */
public class FinaceMerchantAndEnterpriseRo implements Serializable{


    private static final long serialVersionUID = 1L;

    private Long id;

    private String mchNo;

    private Long customerId;

    private String bizChannel;

    private String settleAccount;

    private String customerName;

    /**
     * 业务店铺ID, saas 店铺必传
     */
    private String bizStoreId;

    /**
     * 业务店铺编码, 暂时不传，对应商品中心店铺，订单结算需要对应
     */
    private String bizShopCode;


    /**
     * 企业法人-用户中心ID
     */
    private String bizUserId;

    /**
     * 法人信息ID，金融客户内部个人信息备案ID
     */
    private Long legalPersonId;

    /**
     * 企业名称， 创建时传入商户名称
     */
    private String enterpriseName;

    /**
     * 营业执照号码, 有的话必传，唯一确认企业主体
     */
    private String businessLicenceNo;

    /**
     * 企业地址， 省市区详细
     */
    private String address;

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

    public String getBizStoreId() {
        return bizStoreId;
    }

    public void setBizStoreId(String bizStoreId) {
        this.bizStoreId = bizStoreId;
    }

    public String getBizShopCode() {
        return bizShopCode;
    }

    public void setBizShopCode(String bizShopCode) {
        this.bizShopCode = bizShopCode;
    }

    public String getBizUserId() {
        return bizUserId;
    }

    public void setBizUserId(String bizUserId) {
        this.bizUserId = bizUserId;
    }

    public Long getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(Long legalPersonId) {
        this.legalPersonId = legalPersonId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getBusinessLicenceNo() {
        return businessLicenceNo;
    }

    public void setBusinessLicenceNo(String businessLicenceNo) {
        this.businessLicenceNo = businessLicenceNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
