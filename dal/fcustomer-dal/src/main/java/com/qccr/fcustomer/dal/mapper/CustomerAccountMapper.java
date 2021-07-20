package com.qccr.fcustomer.dal.mapper;

import com.qccr.fcustomer.dal.model.*;

import java.util.List;

public interface CustomerAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerAccount record);

    int insertSelective(CustomerAccount record);

    CustomerAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerAccount record);

    int updateByPrimaryKey(CustomerAccount record);

    List<CustomerAccount> selectByParams(CustomerAccountQuery customerAccountQuery);

    CustomerAccount selectUniqByParams(CustomerAccountQuery customerAccountQuery);

    List<EnterpriseCustomerAccount> selectEnterpriseCustomerAccountByQuery(CustomerQuery customerQuery);

    Long countEnterpriseCustomerAccountByQuery(CustomerQuery customerQuery);

}