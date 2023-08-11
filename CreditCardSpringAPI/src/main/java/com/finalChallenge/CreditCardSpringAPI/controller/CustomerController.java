package com.finalChallenge.CreditCardSpringAPI.controller;

import java.util.List;

import com.finalChallenge.CreditCardSpringAPI.exceptions.CustomerNotFoundException;
import com.finalChallenge.CreditCardSpringAPI.models.Customer;
import com.finalChallenge.CreditCardSpringAPI.repo.ICustomerRepo;
import com.finalChallenge.CreditCardSpringAPI.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping
    List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long customer_id)
    {
        try {
            System.out.println(customer_id + " Controller Class");
            Customer customer = this.customerService.getCustomerByID(customer_id);
            System.out.println(customer);
            return ResponseEntity.status(HttpStatus.FOUND).body(customer);
        } catch (CustomerNotFoundException e) {
            // throw new RuntimeException(e);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer)
    {
        try {
            Customer employee1 = this.customerService.insertEmployee(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee1);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer)
    {
        return null;
    }
    @DeleteMapping("/{customer_id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable Long customer_id)
    {
        return null;
    }
}
