package com.CreditCardManagement.CreditCardManagement.service;

import com.CreditCardManagement.CreditCardManagement.exception.CustomerNotFoundException;
import com.CreditCardManagement.CreditCardManagement.model.Transaction;
import com.CreditCardManagement.CreditCardManagement.repo.CustomerRepo;
import com.CreditCardManagement.CreditCardManagement.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setId(null);
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) throws CustomerNotFoundException {
        Optional< Customer > customerDb = this.customerRepo.findByCustomer_Id(customerId);

        if (customerDb.isPresent()) {
            return customerDb.get();
        } else {
            throw new CustomerNotFoundException("Customer not found with id : " + customerId);
        }
    }

    @Override
    public void deleteCustomerById(Long customerId) throws CustomerNotFoundException {
        Optional < Customer > customerDb = this.customerRepo.findByCustomer_Id(customerId);

        if (customerDb.isPresent()) {
            this.customerRepo.delete(customerDb.get());
        } else {
            throw new CustomerNotFoundException("Customer not found with id : " + customerId);
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomerNotFoundException {
        Optional<Customer> customerDb = this.customerRepo.findByCustomer_Id(customer.getCustomerId());

        if (customerDb.isPresent()) {
            // Update the fields of the retrieved customer entity with the new values
            Customer existingCustomer = customerDb.get();
            existingCustomer.setFirst(customer.getFirst());
            existingCustomer.setLast(customer.getLast());
            existingCustomer.setGender(customer.getGender());
            existingCustomer.setJob(customer.getJob());
            existingCustomer.setDob(customer.getDob());

            // Save the updated customer entity
            this.customerRepo.save(existingCustomer);
        } else {
            throw new CustomerNotFoundException("Customer not found with id: " + customer.getCustomerId());
        }
    }

    //////

    @Transactional
    public void updateCustomerFromTransaction(Transaction transaction) {
        Long customerId = transaction.getCustomerId();

        // Find the customer by customer_id
        Customer customer = customerRepo.findByCustomer_Id(customerId).orElse(null);

        if (customer == null) {
            // Create a new customer if not found
            customer = new Customer();
            customer.setCustomerId(customerId);
        }

        // Update the customer information based on the new transaction data
        customer.setFirst(transaction.getFirst());
        customer.setLast(transaction.getLast());
        customer.setGender(transaction.getGender());
        customer.setJob(transaction.getJob());
        // ... Update other fields as needed

        // Save the updated or new customer
        customerRepo.save(customer);
    }
}
