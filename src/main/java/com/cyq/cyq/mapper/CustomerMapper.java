package com.cyq.cyq.mapper;

import com.cyq.cyq.model.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerInfo selectCustomerByName(String username);

    int insertCustomerInfo(CustomerInfo customerInfox);

    int selectMaxUserId();

    List<CustomerInfo> getCustomer();

    void deleteCustomer(String userid);

    int updateCustomerById(CustomerInfo customerInfo);
}
