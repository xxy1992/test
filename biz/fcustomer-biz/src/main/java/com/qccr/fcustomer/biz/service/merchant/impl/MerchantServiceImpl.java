package com.qccr.fcustomer.biz.service.merchant.impl;

import com.qccr.commons.datetime.Time;
import com.qccr.fcustomer.biz.service.customer.CustomerService;
import com.qccr.fcustomer.biz.service.merchant.MerchantService;
import com.qccr.fcustomer.common.cache.CacheKey;
import com.qccr.fcustomer.common.cache.OneCacheTool;
import com.qccr.fcustomer.common.generator.MchNoGenerator;
import com.qccr.fcustomer.dal.mapper.EnterpriseInfoMapper;
import com.qccr.fcustomer.dal.mapper.FinanceCustomerMapper;
import com.qccr.fcustomer.dal.mapper.FinanceMerchantMapper;
import com.qccr.fcustomer.dal.model.EnterpriseInfo;
import com.qccr.fcustomer.dal.model.FinanceCustomer;
import com.qccr.fcustomer.dal.model.FinanceMerchant;
import com.qccr.fcustomer.dal.model.FinanceMerchantQuery;
import com.qccr.fcustomer.facade.base.constants.CustomerType;
import com.qccr.fcustomer.facade.base.constants.FinanceChannel;
import com.qccr.fcustomer.facade.base.status.StatusCodes;
import com.qccr.fcustomer.facade.customer.ro.CustomerQueryRo;
import com.qccr.fcustomer.facade.customer.ro.CustomerRo;
import com.qccr.fcustomer.facade.merchant.ro.FinaceMerchantAndEnterpriseRo;
import com.qccr.fcustomer.facade.merchant.ro.FinanceMerchantRo;
import com.qccr.fcustomer.facade.merchant.ro.MerchantCreateRo;
import com.qccr.fcustomer.facade.merchant.ro.MerchantQueryRo;
import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 商户服务实现
 *
 * @author yankaiqiang
 * @version $$Id: MerchantServiceImpl.java, v 0.1 2018/6/1 9:43 yankaiqiang Exp $$
 */
@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

    private static Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Resource
    private CustomerService customerService;

    @Resource
    private FinanceMerchantMapper financeMerchantMapper;

    @Resource
    private FinanceCustomerMapper financeCustomerMapper;

    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<FinanceMerchantRo> create(MerchantCreateRo merchantCreateRo) {

        if (merchantCreateRo.getSettleChannel() == null) {
            return Results.newFailedResult(CommonStateCode.PARAMETER_LACK, "结算渠道");
        }

        if (merchantCreateRo.getSettleAccount() == null) {
            return Results.newFailedResult(CommonStateCode.PARAMETER_LACK, "结算账号不能为空");
        }

        CustomerQueryRo customerQueryRo = new CustomerQueryRo();
        BeanUtils.copyProperties(merchantCreateRo, customerQueryRo);
        customerQueryRo.setCustomerType(CustomerType.ENTERPRISE);

        Result<CustomerRo> rs = customerService.query(customerQueryRo);
        if (StatusCodes.CUSTOMER_NOT_EXISITS.equals(rs.getStateCode())) {
            return Results.newFailedResult(StatusCodes.CUSTOMER_NOT_EXISITS, "请先登记客户信息");
        }

        FinanceMerchant financeMerchant = new FinanceMerchant();
        BeanUtils.copyProperties(merchantCreateRo, financeMerchant);
        financeMerchant.setMchNo(MchNoGenerator.generate(code(financeMerchant.getSettleChannel())));

        try {
            financeMerchantMapper.insertSelective(financeMerchant);
        } catch (DuplicateKeyException e) {
            logger.info("重复创建商户", e);
        }

        MerchantQueryRo merchantQueryRo = new MerchantQueryRo();
        BeanUtils.copyProperties(merchantCreateRo, merchantQueryRo);
        return this.query(merchantQueryRo);
    }

    private String code(String settleChannel) {
        switch (settleChannel) {
            case FinanceChannel.CSUPER:
                return "01";
            case FinanceChannel.PALM_SALE:
                return "02";
            case FinanceChannel.TIANMAO_SERVICE:
                return "03";
            default:
                throw new IllegalArgumentException("settleChannel is null");
        }
    }


    @Override
    public Result<FinanceMerchantRo> query(MerchantQueryRo merchantQueryRo) {

        String mchCacheKey = getMchCacheKey(merchantQueryRo);
        if (StringUtils.isNotBlank(mchCacheKey)) {
            FinanceMerchantRo merchantRo = OneCacheTool.getBean(mchCacheKey);
            if (merchantRo != null) {
                OneCacheTool.setBean(mchCacheKey, merchantRo, Time.minutes(5));
                return Results.newSuccessResult(merchantRo);
            }
        }

        FinanceMerchantQuery query = new FinanceMerchantQuery();
        BeanUtils.copyProperties(merchantQueryRo, query);

        if (StringUtils.isNotBlank(merchantQueryRo.getBizShopCode())
                || StringUtils.isNotBlank(merchantQueryRo.getBizStoreId())) {
            CustomerQueryRo customerQueryRo = new CustomerQueryRo();
            BeanUtils.copyProperties(merchantQueryRo, customerQueryRo);
            customerQueryRo.setCustomerType(CustomerType.ENTERPRISE);
            Result<CustomerRo> rs = customerService.query(customerQueryRo);
            if (rs.isSuccess() && rs.getData() != null) {
                query.setCustomerId(rs.getData().getId());
            } else {
                return Results.newFailedResult(StatusCodes.MERCHANT_NOT_EXISITS);
            }
        }

        FinanceMerchant financeMerchant = financeMerchantMapper.selectByUniqParams(query);
        if (financeMerchant == null) {
            return Results.newFailedResult(StatusCodes.MERCHANT_NOT_EXISITS);
        }

        FinanceMerchantRo financeMerchantRo = new FinanceMerchantRo();
        BeanUtils.copyProperties(financeMerchant, financeMerchantRo);

        FinanceCustomer customer = financeCustomerMapper.selectByPrimaryKey(financeMerchant.getCustomerId());
        if (customer != null) {
            financeMerchantRo.setCustomerName(customer.getCustomerName());
            financeMerchantRo.setCustomerInfoId(customer.getCustomerInfoId());
        }

        if (StringUtils.isNotBlank(mchCacheKey)) {
            OneCacheTool.setBean(mchCacheKey, financeMerchantRo, Time.minutes(5));
        }

        return Results.newSuccessResult(financeMerchantRo);
    }

    @Override
    public Result<FinaceMerchantAndEnterpriseRo> queryEnterprise(MerchantQueryRo merchantQueryRo) {
        Result<FinanceMerchantRo>  merchantRoResult= this.query(merchantQueryRo);
        if(merchantRoResult.isFailed()){
            return Results.newFailedResult(merchantRoResult.getStateCode());
        }
        FinanceMerchantRo financeMerchant = merchantRoResult.getData();
        if(financeMerchant.getCustomerInfoId()==null){
            return Results.newFailedResult(CommonStateCode.FAILED,"未找到企业信息");
        }
        EnterpriseInfo enterpriseInfo = enterpriseInfoMapper.selectByPrimaryKey(financeMerchant.getCustomerInfoId());
        if(enterpriseInfo==null){
            return Results.newFailedResult(CommonStateCode.FAILED,"未找到企业信息");
        }
        FinaceMerchantAndEnterpriseRo finaceMerchantAndEnterprise = new FinaceMerchantAndEnterpriseRo();
        BeanUtils.copyProperties(financeMerchant,finaceMerchantAndEnterprise);
        BeanUtils.copyProperties(enterpriseInfo,finaceMerchantAndEnterprise);
        return Results.newSuccessResult(finaceMerchantAndEnterprise);
    }

    private String getMchCacheKey(MerchantQueryRo merchantQueryRo) {
        if (merchantQueryRo == null) {
            return null;
        }

        if (merchantQueryRo.getMchId() != null) {
            return CacheKey.MCH_CACHE_PREFIX + merchantQueryRo.getMchId();
        }

        if (StringUtils.isNotBlank(merchantQueryRo.getMchNo())) {
            return CacheKey.MCH_CACHE_PREFIX + merchantQueryRo.getMchNo();
        }

        if (StringUtils.isNotBlank(merchantQueryRo.getSettleChannel())) {
            if (StringUtils.isNotBlank(merchantQueryRo.getBizStoreId())) {
                return CacheKey.MCH_CACHE_PREFIX + merchantQueryRo.getSettleChannel() + "_" + merchantQueryRo.getBizStoreId();
            }
            if (StringUtils.isNotBlank(merchantQueryRo.getBizShopCode())) {
                return CacheKey.MCH_CACHE_PREFIX + merchantQueryRo.getSettleChannel() + "_" + merchantQueryRo.getBizShopCode();
            }
        }
        return null;
    }

}
