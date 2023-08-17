package com.CreditCardManagement.CreditCardManagement.controller;

import com.CreditCardManagement.CreditCardManagement.exception.CustomerNotFoundException;
import com.CreditCardManagement.CreditCardManagement.model.Customer;
import com.CreditCardManagement.CreditCardManagement.utility.CustomerAPIResponse;
import com.CreditCardManagement.CreditCardManagement.utility.StatusMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CreditCardManagement.CreditCardManagement.service.CustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity <?> getAllCustomer() {
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            List<Customer> customers = customerService.getAllCustomers();
            map.put(StatusMessages.SUCCESS, "All customers retrieved successfully");
            CustomerAPIResponse response = new CustomerAPIResponse(map, customers);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            System.out.println("Error retrieving customers: " + e.getMessage());
            map.put(StatusMessages.FAILURE, "Error retrieving customers: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            Customer requiredCustomer = this.customerService.getCustomerById(id);
            List<Customer> requiredCustomerList = new ArrayList<>();
            requiredCustomerList.add(requiredCustomer);
            map.put(StatusMessages.FOUND, "Customer " + id + " Found successfully");
            CustomerAPIResponse response = new CustomerAPIResponse(map, requiredCustomerList);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (CustomerNotFoundException e) {
            System.out.println("Error: Customer not found with id: " + id + " and " + e.getMessage());
            map.put(StatusMessages.NOT_FOUND, "Error: Customer not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity < ? > createCustomer(@RequestBody Customer customer) {
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            try {
                Customer newCustomer = customerService.getCustomerById(customer.getCustomerId());
            } catch (CustomerNotFoundException e) {
                Customer createdCustomer = this.customerService.createCustomer(customer);
                List<Customer> newCustomerList = new ArrayList<>();
                newCustomerList.add(createdCustomer);
                map.put(StatusMessages.CREATED, "Customer created successfully");
                CustomerAPIResponse response = new CustomerAPIResponse(map, newCustomerList);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            // Customer with the same customer_id already exists, return a conflict response
            map.put(StatusMessages.CONFLICT, "Customer already exists - Can't create!");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(map);
        } catch (Exception e) {
            System.out.println("Error creating customer: " + e.getMessage());
            map.put(StatusMessages.NOT_CREATED, "Error creating customer: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PutMapping("/customers")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer)
    {
        Map<StatusMessages , String> map = new HashMap<>();
        try {
            this.customerService.updateCustomer(customer);
            map.put(StatusMessages.SUCCESS, "Customer updated successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
        } catch (CustomerNotFoundException e) {
            map.put(StatusMessages.FAILURE, "Error updating Customer details. Enter correct Customer Id." );
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Map<StatusMessages, String>> deleteCustomerById(@PathVariable Long id) {
        System.out.println("Delete Customer: " + id);
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            this.customerService.deleteCustomerById(id);
            map.put(StatusMessages.SUCCESS, "Customer " + id + " deleted successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
        } catch (CustomerNotFoundException e) {
            System.out.println("Error: Customer not found with id: " + id + " and " + e.getMessage());
            map.put(StatusMessages.FAILURE, "Error: Customer not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }
}
