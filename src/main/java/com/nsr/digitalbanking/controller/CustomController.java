package com.nsr.digitalbanking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsr.digitalbanking.dto.CustomerDTO;
import com.nsr.digitalbanking.exception.CustomerNotFound;
import com.nsr.digitalbanking.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomController {

    private CustomerService customerService;

    @GetMapping("/")
    public List<CustomerDTO> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) throws CustomerNotFound {
        return customerService.getCustomer(id);
    }

    @PostMapping("/save")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/update/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customer)
            throws CustomerNotFound {
        customer.setId(id);
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId) throws CustomerNotFound {
        customerService.deleteCustomer(customerId);
        return "Customer deleted successfully";
    }
}