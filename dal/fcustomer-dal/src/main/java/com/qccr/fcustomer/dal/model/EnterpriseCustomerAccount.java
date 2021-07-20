package com.qccr.fcustomer.dal.model;

import java.io.Serializable;

/**
 * 企业客户创建基本信息
 *
 * @author yankaiqiang
 * @version $$Id: EnterpriseCustomerRo.java, v 0.1 2018/5/30 15:56 yankaiqiang Exp $$
 */
public class EnterpriseCustomerAccount{

    /**
     * 客户资金账户表id
     */
    private Long customerAccountId;

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

    /**
     * 客户名称，默认需要传递 店铺名称
     */
    private String customerName;

    /**
     * 创建客户来源渠道, {@link com.qccr.fcustomer.facade.base.constants.FinanceChannel}
     */
    private String sourceChannel;

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


    public Long getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Long customerAccountId) {
        this.customerAccountId = customerAccountId;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

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
