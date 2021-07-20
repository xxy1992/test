package com.qccr.fcustomer.dal.model;

import java.util.Date;

public class EnterpriseInfo {
    private Long id;

    private String bizStoreId;

    private String bizShopCode;

    private Long bizUserId;

    private Long legalPersonId;

    private String enterpriseName;

    private Date createTime;

    private String createPerson;

    private Date updateTime;

    private String updatePerson;

    private String businessLicenceNo;

    private String address;

    private Long bankCardId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizStoreId() {
        return bizStoreId;
    }

    public void setBizStoreId(String bizStoreId) {
        this.bizStoreId = bizStoreId == null ? null : bizStoreId.trim();
    }

    public String getBizShopCode() {
        return bizShopCode;
    }

    public void setBizShopCode(String bizShopCode) {
        this.bizShopCode = bizShopCode == null ? null : bizShopCode.trim();
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
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
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

    public Long getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(Long bankCardId) {
        this.bankCardId = bankCardId;
    }

    public Long getBizUserId() {
        return bizUserId;
    }

    public void setBizUserId(Long bizUserId) {
        this.bizUserId = bizUserId;
    }
}