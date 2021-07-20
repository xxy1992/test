package com.qccr.fcustomer.facade.customer.ro;

import java.io.Serializable;
import java.util.List;

/**
 * 客户查询信息ro
 *
 * @author yankaiqiang
 * @version $$Id: CustomerQueryRo.java, v 0.1 2018/5/30 16:50 yankaiqiang Exp $$
 */
public class CustomerQueryRo implements Serializable {

    private static final long serialVersionUID = 7370503105441627082L;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 非ID查询，客户类型必传, 企业客户 2 或者 个人客户 1
     */
    private Integer customerType;

    /**
     * 个人或者企业客户法人业务系统唯一识别号，用户ID
     */
    private String bizUserId;

    /**
     * 批量查询请传入集合
     */
    private List<String> bizUserIds;

    /**
     * 企业客户业务系统唯一识别号，店铺主体ID
     */
    private String bizStoreId;

    /**
     * 批量查询请传入集合
     */
    private List<String> bizStoreIds;

    /**
     * 企业客户业务系统唯一识别号，店铺主体CODE
     */
    private String bizShopCode;

    /**
     * 营业执照号码, 有的话必传，唯一确认企业主体, 可通过营业执照查询客户
     */
    private String businessLicenceNo;

    /**
     * 批量查询请传入集合
     */
    private List<String> businessLicenceNos;

    /**
     * 账号渠道 {@link com.qccr.fcustomer.facade.base.constants.FinanceChannel}
     */
    private String accountChannel;

    /**
     * 资金账号, 根据资金账号查询
     */
    private String accountNo;

    private Integer page;

    private Integer pageSize;

    /**
     * 客户登记渠道 {@link com.qccr.fcustomer.facade.base.constants.FinanceChannel}
     */
    private String sourceChannel;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getAccountChannel() {
        return accountChannel;
    }

    public void setAccountChannel(String accountChannel) {
        this.accountChannel = accountChannel;
    }

    public String getBizUserId() {
        return bizUserId;
    }

    public void setBizUserId(String bizUserId) {
        this.bizUserId = bizUserId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getBusinessLicenceNo() {
        return businessLicenceNo;
    }

    public void setBusinessLicenceNo(String businessLicenceNo) {
        this.businessLicenceNo = businessLicenceNo;
    }

    public List<String> getBizUserIds() {
        return bizUserIds;
    }

    public void setBizUserIds(List<String> bizUserIds) {
        this.bizUserIds = bizUserIds;
    }

    public List<String> getBizStoreIds() {
        return bizStoreIds;
    }

    public void setBizStoreIds(List<String> bizStoreIds) {
        this.bizStoreIds = bizStoreIds;
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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
