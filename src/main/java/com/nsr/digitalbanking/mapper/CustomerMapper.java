package com.nsr.digitalbanking.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nsr.digitalbanking.dto.CustomerDTO;
import com.nsr.digitalbanking.model.Customer;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public CustomerDTO toCustomerDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
}
