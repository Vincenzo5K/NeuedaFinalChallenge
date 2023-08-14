package com.CreditCardManagement.CreditCardManagement.utility;

import com.CreditCardManagement.CreditCardManagement.model.Transaction;

import java.util.List;
import java.util.Map;

public class TransactionAPIResponse {
    private Map<StatusMessages, String> status;
    private List<Transaction> transactions;

    public TransactionAPIResponse(Map<StatusMessages, String> status, List<Transaction> transactions) {
        this.status = status;
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Map<StatusMessages, String> getStatus() {
        return status;
    }
}
