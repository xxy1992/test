package com.qccr.fcustomer.dal.mapper;

import com.qccr.fcustomer.dal.model.EnterpriseInfo;
import com.qccr.fcustomer.dal.model.EnterpriseQuery;

public interface EnterpriseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseInfo record);

    int insertSelective(EnterpriseInfo record);

    EnterpriseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseInfo record);

    int updateByPrimaryKey(EnterpriseInfo record);

    EnterpriseInfo queryByParams(EnterpriseQuery enterpriseQuery);
}