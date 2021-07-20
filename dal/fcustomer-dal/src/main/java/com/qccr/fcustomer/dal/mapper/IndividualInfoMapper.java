package com.qccr.fcustomer.dal.mapper;

import com.qccr.fcustomer.dal.model.IndividualInfo;

public interface IndividualInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IndividualInfo record);

    int insertSelective(IndividualInfo record);

    IndividualInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndividualInfo record);

    int updateByPrimaryKey(IndividualInfo record);

    IndividualInfo selectByBizId(Long bizUserId);
}