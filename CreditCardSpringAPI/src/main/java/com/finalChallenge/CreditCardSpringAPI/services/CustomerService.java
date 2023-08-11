package com.finalChallenge.CreditCardSpringAPI.services;

import com.finalChallenge.CreditCardSpringAPI.exceptions.CustomerNotFoundException;
import com.finalChallenge.CreditCardSpringAPI.models.Customer;
import com.finalChallenge.CreditCardSpringAPI.repo.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepo repository;

    public CustomerService(ICustomerRepo repository) {
        this.repository = repository;
    }

    public List<Customer> getAllCustomers()
    {
        return this.repository.findAll();
    }

    public Customer getCustomerByID(Long id) throws CustomerNotFoundException {
        System.out.println(id + "From Service class.");
        Customer customer = repository.findByCustomerId(id)
                .orElseThrow(()->new CustomerNotFoundException(id));
        System.out.println(customer);
        return customer;
    }

    public Customer insertEmployee(Customer customer) {
        return customer;
    }
}
