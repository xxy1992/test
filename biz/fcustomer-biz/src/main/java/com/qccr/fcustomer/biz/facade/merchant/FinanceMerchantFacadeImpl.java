package com.qccr.fcustomer.biz.facade.merchant;

import com.qccr.fcustomer.biz.service.merchant.MerchantService;
import com.qccr.fcustomer.common.log.FacadeLogUtils;
import com.qccr.fcustomer.facade.base.constants.FinanceChannel;
import com.qccr.fcustomer.facade.merchant.FinanceMerchantFacade;
import com.qccr.fcustomer.facade.merchant.ro.FinaceMerchantAndEnterpriseRo;
import com.qccr.fcustomer.facade.merchant.ro.FinanceMerchantRo;
import com.qccr.fcustomer.facade.merchant.ro.MerchantCreateRo;
import com.qccr.fcustomer.facade.merchant.ro.MerchantQueryRo;
import com.qccr.knife.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 商户接口实现
 *
 * @author yankaiqiang
 * @version $$Id: FinanceMerchantFacadeImpl.java, v 0.1 2018/6/1 9:40 yankaiqiang Exp $$
 */
@Service("financeMerchantFacade")
public class FinanceMerchantFacadeImpl implements FinanceMerchantFacade {

    private static Logger logger = LoggerFactory.getLogger(FinanceMerchantFacadeImpl.class);

    @Resource
    private MerchantService merchantService;


    @Override
    public Result<FinanceMerchantRo> create(MerchantCreateRo merchantCreateRo) {

        FacadeLogUtils.info(logger, merchantCreateRo, "merchant_create");

        Result<FinanceMerchantRo> rs = merchantService.create(merchantCreateRo);

        FacadeLogUtils.info(logger, merchantCreateRo, rs, "merchant_create");

        return rs;
    }

    @Override
    public Result<FinanceMerchantRo> query(MerchantQueryRo merchantQueryRo) {

        FacadeLogUtils.info(logger, merchantQueryRo, "merchant_query");

        if (Objects.equals(merchantQueryRo.getSettleChannel(), "502")) {
            merchantQueryRo.setSettleChannel(FinanceChannel.PALM_SALE);
        }

        Result<FinanceMerchantRo> rs = merchantService.query(merchantQueryRo);

        FacadeLogUtils.info(logger, merchantQueryRo, rs, "merchant_query");

        return rs;
    }

    @Override
    public Result<FinaceMerchantAndEnterpriseRo> queryEnterprise(MerchantQueryRo merchantQueryRo) {
        FacadeLogUtils.info(logger, merchantQueryRo, "merchantenterprise_query");

        if (Objects.equals(merchantQueryRo.getSettleChannel(), "502")) {
            merchantQueryRo.setSettleChannel(FinanceChannel.PALM_SALE);
        }

        Result<FinaceMerchantAndEnterpriseRo> rs = merchantService.queryEnterprise(merchantQueryRo);

        FacadeLogUtils.info(logger, merchantQueryRo, rs, "merchantenterprise_query");

        return rs;
    }

}
