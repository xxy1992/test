package com.qccr.fcustomer.facade.customer.ro;

/**
 * 个人客户Ro
 *
 * @author yankaiqiang
 * @version $$Id: IndividualCustomerRo.java, v 0.1 2018/5/30 15:55 yankaiqiang Exp $$
 */
public class IndividualCustomerRo extends CustomerRo {

    private static final long serialVersionUID = -333026993983495836L;

    private String idCardNo;

    private String legalName;

    private String mobile;

    private String bizUserId;

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBizUserId() {
        return bizUserId;
    }

    public void setBizUserId(String bizUserId) {
        this.bizUserId = bizUserId;
    }
}
