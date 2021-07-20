package com.qccr.fcustomer.facade.customer.ro;

import java.io.Serializable;

/**
 * 联系人信息
 *
 * @author yankaiqiang
 * @version $$Id: ContactRo.java, v 0.1 2018/6/27 11:38 yankaiqiang Exp $$
 */
public class ContactRo implements Serializable {

    private static final long serialVersionUID = -8186655157472028686L;

    private Long id;

    /**
     * 联系人姓名
     */
    private String contactPerson;

    /**
     * 联系人头衔，业务自定义
     */
    private String contactPersonTitle;

    /**
     * 联系人电话
     */
    private String contactTel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPersonTitle() {
        return contactPersonTitle;
    }

    public void setContactPersonTitle(String contactPersonTitle) {
        this.contactPersonTitle = contactPersonTitle;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }
}
