package com.nsr.digitalbanking.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nsr.digitalbanking.dto.CustomerDTO;
import com.nsr.digitalbanking.exception.CustomerNotFound;
import com.nsr.digitalbanking.mapper.CustomerMapper;
import com.nsr.digitalbanking.model.Customer;
import com.nsr.digitalbanking.repository.CustomerRepository;
import com.nsr.digitalbanking.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private CustomerMapper mapper;
    private CustomerRepository repoCustomer;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = repoCustomer.findAll();
        List<CustomerDTO> customersDTO = customers.stream().map(Cus -> mapper.toCustomerDto(Cus))
                .collect(Collectors.toList());
        return customersDTO;
    }

    @Override
    public CustomerDTO getCustomer(Long customerID) throws CustomerNotFound {
        Customer customer = repoCustomer.findById(customerID)
                .orElseThrow(() -> new CustomerNotFound("Customer not found "));
        CustomerDTO customerDTO = mapper.toCustomerDto(customer);
        return customerDTO;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDto) {
        Customer customer = mapper.toCustomer(customerDto);
        if (customer == null)
            new CustomerNotFound("Customer not found");
        repoCustomer.save(customer);
        CustomerDTO result = mapper.toCustomerDto(customer);
        return result;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customer) throws CustomerNotFound {
        getCustomer(customer.getId());
        return saveCustomer(customer);
    }

    @Override
    public void deleteCustomer(Long customerID) throws CustomerNotFound {
        getCustomer(customerID);
        repoCustomer.deleteById(customerID);
    }
}
