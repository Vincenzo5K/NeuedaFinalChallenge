package com.CreditCardManagement.CreditCardManagement.service;

import com.CreditCardManagement.CreditCardManagement.exception.CustomerNotFoundException;
import com.CreditCardManagement.CreditCardManagement.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer createCustomer(Customer customer);

    List< Customer > getAllCustomers();

    Customer getCustomerById(Long CustomerId) throws CustomerNotFoundException;

    void deleteCustomerById(Long id) throws CustomerNotFoundException;

    void updateCustomer(Customer customer) throws CustomerNotFoundException;
}
