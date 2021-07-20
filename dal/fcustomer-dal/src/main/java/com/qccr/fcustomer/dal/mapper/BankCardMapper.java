package com.qccr.fcustomer.dal.mapper;

import com.qccr.fcustomer.dal.model.BankCard;
import org.apache.ibatis.annotations.Param;

public interface BankCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    BankCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);

    BankCard selectByCardNoAndBankNo(@Param("cardNo") String cardNo, @Param("bankNo") String bankNo);
}