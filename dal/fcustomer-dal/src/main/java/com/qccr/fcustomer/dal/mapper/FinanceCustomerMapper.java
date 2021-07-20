package com.qccr.fcustomer.dal.mapper;

import com.qccr.fcustomer.dal.model.AccountAndEnterpriseInfo;
import com.qccr.fcustomer.dal.model.CustomerQuery;
import com.qccr.fcustomer.dal.model.FinanceCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceCustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceCustomer record);

    int insertSelective(FinanceCustomer record);

    FinanceCustomer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceCustomer record);

    int updateByPrimaryKey(FinanceCustomer record);

    FinanceCustomer selectUniqueByQuery(CustomerQuery customerQuery);

    List<FinanceCustomer> selectByQuery(CustomerQuery customerQuery);

    int countByQuery(CustomerQuery customerQuery);

    List<FinanceCustomer> selectEnterpriseCustomerByQuery(CustomerQuery customerQuery);

    int countEnterpriseCustomerByQuery(CustomerQuery customerQuery);

    FinanceCustomer selectByCustomerTypeAndInfoId(@Param("customerType") Integer customerType, @Param("customerInfoId") Long customerInfoId);

    List<AccountAndEnterpriseInfo> queryAccountAndEnterpriseInfo(CustomerQuery customerQuery);
}