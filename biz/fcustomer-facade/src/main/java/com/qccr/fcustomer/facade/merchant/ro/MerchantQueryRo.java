package com.qccr.fcustomer.facade.merchant.ro;

import java.io.Serializable;

/**
 * 查询收单商户 参数信息 ro
 *
 * @author yankaiqiang
 * @version $$Id: MerchantQueryRo.java, v 0.1 2018/5/30 16:23 yankaiqiang Exp $$
 */
public class MerchantQueryRo implements Serializable {

    private static final long serialVersionUID = 6973253726252228178L;

    /**
     * 商户ID
     */
    private Long mchId;

    /**
     * 商户号
     */
    private String mchNo;

    /**
     * 业务店铺ID
     */
    private String bizStoreId;

    /**
     * 业务店铺码
     */
    private String bizShopCode;

    /**
     * 客户ID 查询必须和settlechannel同时不能为空
     */
    private Long customerId;

    /**
     * 收单结算业务渠道
     */
    private String settleChannel;

    /**
     * 资金账号
     */
    private String faNo;

    public Long getMchId() {
        return mchId;
    }

    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
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

    public String getSettleChannel() {
        return settleChannel;
    }

    public void setSettleChannel(String settleChannel) {
        this.settleChannel = settleChannel;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFaNo() {
        return faNo;
    }

    public void setFaNo(String faNo) {
        this.faNo = faNo;
    }
}
