package com.cyq.cyq.service.impl;

import com.cyq.cyq.mapper.CustomerMapper;
import com.cyq.cyq.model.CustomerInfo;
import com.cyq.cyq.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerInfo selectCustomerByName(String username) {
        return customerMapper.selectCustomerByName(username);
    }

    @Override
    public int insertCustomerInfo(CustomerInfo customerInfox) {
        return customerMapper.insertCustomerInfo(customerInfox);
    }

    @Override
    public int selectMaxUserId() {
        return customerMapper.selectMaxUserId();
    }

    @Override
    public List<CustomerInfo> getCustomer() {
        return customerMapper.getCustomer();
    }

    @Override
    public void deleteCustomer(String userid) {
        customerMapper.deleteCustomer(userid);
    }

    @Override
    public int updateCustomerById(CustomerInfo customerInfo) {
        return customerMapper.updateCustomerById(customerInfo);
    }
}
