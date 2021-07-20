package com.qccr.fcustomer.dal.model;

/**
 * @author yankaiqiang
 * @version $$Id: EnterpriseQuery.java, v 0.1 2018/5/31 20:15 yankaiqiang Exp $$
 */
public class EnterpriseQuery {

    /**
     * 企业客户业务系统唯一识别号，店铺主体ID
     */
    private String bizStoreId;

    /**
     * 企业客户法人业务系统唯一识别号，用户ID, 主要适用于掌中配零售端开通账号
     */
    private Long bizUserId;

    /**
     * 企业客户业务系统唯一识别号，店铺主体CODE
     */
    private String bizShopCode;

    private String enterpriseName;

    private String businessLicenceNo;

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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Long getBizUserId() {
        return bizUserId;
    }

    public void setBizUserId(Long bizUserId) {
        this.bizUserId = bizUserId;
    }

    public String getBusinessLicenceNo() {
        return businessLicenceNo;
    }

    public void setBusinessLicenceNo(String businessLicenceNo) {
        this.businessLicenceNo = businessLicenceNo;
    }
}
