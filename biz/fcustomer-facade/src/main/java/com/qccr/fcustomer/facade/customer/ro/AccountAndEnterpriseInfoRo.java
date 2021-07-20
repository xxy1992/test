package com.qccr.fcustomer.facade.customer.ro;

import java.util.List;

/**
 *
 */
public class AccountAndEnterpriseInfoRo extends CustomerRo {

    private static final long serialVersionUID = 8736496652642156082L;

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
