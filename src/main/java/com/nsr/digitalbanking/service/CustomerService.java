package com.nsr.digitalbanking.service;

import java.util.List;

import com.nsr.digitalbanking.dto.CustomerDTO;
import com.nsr.digitalbanking.exception.CustomerNotFoundException;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomer(Long customerID) throws CustomerNotFoundException;

    CustomerDTO saveCustomer(CustomerDTO customer);

    CustomerDTO updateCustomer(CustomerDTO customer) throws CustomerNotFoundException;

    void deleteCustomer(Long customerID) throws CustomerNotFoundException;
}