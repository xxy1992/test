package com.qccr.fcustomer.dal.model;

import java.util.List;

/**
 * @author yankaiqiang
 * @version $$Id: CustomerQuery.java, v 0.1 2018/6/27 14:54 yankaiqiang Exp $$
 */
public class CustomerQuery {

    private Long id;

    private Integer customerType;

    private String customerName;

    private String enterpriseName;

    private Long customerInfoId;

    private List<String> bizStoreIds;

    private List<String> bizUserIds;

    private List<String> businessLicenceNos;

    private Integer start;

    private Integer limit;

    private String sourceChannel;

    private String accountChannel;

    /**
     * 资金账号, 根据资金账号查询
     */
    private String accountNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomerInfoId() {
        return customerInfoId;
    }

    public void setCustomerInfoId(Long customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<String> getBizStoreIds() {
        return bizStoreIds;
    }

    public void setBizStoreIds(List<String> bizStoreIds) {
        this.bizStoreIds = bizStoreIds;
    }

    public List<String> getBizUserIds() {
        return bizUserIds;
    }

    public void setBizUserIds(List<String> bizUserIds) {
        this.bizUserIds = bizUserIds;
    }

    public List<String> getBusinessLicenceNos() {
        return businessLicenceNos;
    }

    public void setBusinessLicenceNos(List<String> businessLicenceNos) {
        this.businessLicenceNos = businessLicenceNos;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getAccountChannel() {
        return accountChannel;
    }

    public void setAccountChannel(String accountChannel) {
        this.accountChannel = accountChannel;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
