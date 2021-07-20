package com.qccr.fcustomer.dal.mapper;

import com.qccr.fcustomer.dal.model.FinanceMerchant;
import com.qccr.fcustomer.dal.model.FinanceMerchantQuery;

public interface FinanceMerchantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceMerchant record);

    int insertSelective(FinanceMerchant record);

    FinanceMerchant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceMerchant record);

    int updateByPrimaryKey(FinanceMerchant record);

    FinanceMerchant selectByUniqParams(FinanceMerchantQuery financeMerchantQuery);

}