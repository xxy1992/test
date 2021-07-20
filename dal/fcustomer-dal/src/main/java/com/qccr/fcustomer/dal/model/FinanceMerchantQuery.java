package com.qccr.fcustomer.dal.model;

/**
 * @author yankaiqiang
 * @version $$Id: FinanceMerchantQuery.java, v 0.1 2018/6/1 10:05 yankaiqiang Exp $$
 */
public class FinanceMerchantQuery {

    /**
     * 商户ID
     */
    private Long mchId;

    /**
     * 商户号
     */
    private String mchNo;

    /**
     * 客户ID 查询必须和settlechannel同时不能为空
     */
    private Long customerId;

    /**
     * 收单结算业务渠道
     */
    private String settleChannel;

    /**
     * 结算资金账号
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getSettleChannel() {
        return settleChannel;
    }

    public void setSettleChannel(String settleChannel) {
        this.settleChannel = settleChannel;
    }

    public String getFaNo() {
        return faNo;
    }

    public void setFaNo(String faNo) {
        this.faNo = faNo;
    }
}
