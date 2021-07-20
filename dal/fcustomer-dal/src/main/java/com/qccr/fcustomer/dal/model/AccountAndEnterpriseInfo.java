package com.qccr.fcustomer.dal.model;

/**
 * AccountAndEnterpriseInfo
 *
 * @author yanglibo@qccr.com
 * @version AccountAndEnterpriseInfo.java 2018年07月03日 11:17:03
 */
public class AccountAndEnterpriseInfo {
    private String storeId;

    private String storeName;

    private String accountNo;

    private String headerMobilePhone;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHeaderMobilePhone() {
        return headerMobilePhone;
    }

    public void setHeaderMobilePhone(String headerMobilePhone) {
        this.headerMobilePhone = headerMobilePhone;
    }
}
