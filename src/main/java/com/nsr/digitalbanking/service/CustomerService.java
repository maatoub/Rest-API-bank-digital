package com.nsr.digitalbanking.service;

import java.util.List;

import com.nsr.digitalbanking.dto.CustomerDTO;
import com.nsr.digitalbanking.exception.CustomerNotFound;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomer(Long customerID) throws CustomerNotFound;

    CustomerDTO saveCustomer(CustomerDTO customer);

    CustomerDTO updateCustomer(CustomerDTO customer) throws CustomerNotFound;

    void deleteCustomer(Long customerID) throws CustomerNotFound;
}