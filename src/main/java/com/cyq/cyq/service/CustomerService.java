package com.cyq.cyq.service;

import com.cyq.cyq.model.CustomerInfo;

import java.util.List;

public interface CustomerService {

    CustomerInfo selectCustomerByName(String username);

    int insertCustomerInfo(CustomerInfo customerInfox);

    int selectMaxUserId();

    List<CustomerInfo> getCustomer();

    void deleteCustomer(String userid);

    int updateCustomerById(CustomerInfo customerInfo1);
}
