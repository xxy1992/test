package com.qccr.fcustomer.dal.mapper;

import com.qccr.fcustomer.dal.model.EnterpriseContacts;

import java.util.List;

public interface EnterpriseContactsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseContacts record);

    int insertSelective(EnterpriseContacts record);

    EnterpriseContacts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseContacts record);

    int updateByPrimaryKey(EnterpriseContacts record);

    List<EnterpriseContacts> selectByEnterpriseInfoId(Long enterpriseId);
}