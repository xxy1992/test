package com.qccr.fcustomer.facade.customer.ro;

import java.util.List;

/**
 * 企业客户创建基本信息
 *
 * @author yankaiqiang
 * @version $$Id: EnterpriseCustomerRo.java, v 0.1 2018/5/30 15:56 yankaiqiang Exp $$
 */
public class EnterpriseCustomerRo extends CustomerRo {

    private static final long serialVersionUID = 8736496652642156082L;

    /**
     * 业务店铺ID, saas 店铺必传
     */
    private String bizStoreId;

    /**
     * 业务店铺编码, 暂时不传，对应商品中心店铺，订单结算需要对应
     */
    private String bizShopCode;

    /**
     * 企业法人姓名
     */
    private String legalPersonName;

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
     * 法人银行卡号，用于验证法人信息
     */
    private String cardNo;

    /**
     * 银行卡编号，用于验证法人信息
     */
    private String bankNo;

    /**
     * 银行卡持有者银行备案姓名，用于验证法人信息
     */
    private String cardOwner;

    /**
     * 银行卡开户支行，用于验证法人信息
     */
    private String cardBank;

    /**
     * 银行卡银行备案手机号，用于验证法人信息
     */
    private String cardMobile;

    /**
     * 企业联系人
     */
    private List<ContactRo> contactRos;

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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public String getCardMobile() {
        return cardMobile;
    }

    public void setCardMobile(String cardMobile) {
        this.cardMobile = cardMobile;
    }

    public List<ContactRo> getContactRos() {
        return contactRos;
    }

    public void setContactRos(List<ContactRo> contactRos) {
        this.contactRos = contactRos;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getBizUserId() {
        return bizUserId;
    }

    public void setBizUserId(String bizUserId) {
        this.bizUserId = bizUserId;
    }
}
