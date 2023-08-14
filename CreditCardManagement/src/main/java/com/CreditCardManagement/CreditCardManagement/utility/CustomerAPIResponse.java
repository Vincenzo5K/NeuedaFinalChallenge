package com.CreditCardManagement.CreditCardManagement.utility;

import com.CreditCardManagement.CreditCardManagement.model.Customer;

import java.util.List;
import java.util.Map;

public class CustomerAPIResponse {
    private Map<StatusMessages, String> status;
    private List<Customer> customers;

    public CustomerAPIResponse(Map<StatusMessages, String> status, List<Customer> customers) {
        this.status = status;
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Map<StatusMessages, String> getStatus() {
        return status;
    }
}
