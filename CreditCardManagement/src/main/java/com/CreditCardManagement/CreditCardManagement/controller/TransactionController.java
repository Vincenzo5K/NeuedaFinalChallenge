package com.CreditCardManagement.CreditCardManagement.controller;

import com.CreditCardManagement.CreditCardManagement.dto.CategorySpend;
import com.CreditCardManagement.CreditCardManagement.dto.LowHigh;
import com.CreditCardManagement.CreditCardManagement.dto.StateSales;
import com.CreditCardManagement.CreditCardManagement.exception.CustomerNotFoundException;
import com.CreditCardManagement.CreditCardManagement.exception.TransactionNotFoundException;
import com.CreditCardManagement.CreditCardManagement.model.Customer;
import com.CreditCardManagement.CreditCardManagement.model.Transaction;
import com.CreditCardManagement.CreditCardManagement.service.CustomerService;
import com.CreditCardManagement.CreditCardManagement.service.TransactionService;
import com.CreditCardManagement.CreditCardManagement.utility.StatusMessages;
import com.CreditCardManagement.CreditCardManagement.utility.TransactionAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {
    private TransactionService transactionService;
    private CustomerService customerService;
    @Autowired
    public TransactionController(TransactionService transactionService, CustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransactions() {
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();
            map.put(StatusMessages.SUCCESS, "All Transactions retrieved successfully");
            TransactionAPIResponse response = new TransactionAPIResponse(map, transactions);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            System.out.println("Error retrieving Transactions: " + e.getMessage());
            map.put(StatusMessages.FAILURE, "Error retrieving Transactions: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/transactions/{num}")
    public ResponseEntity<?> getTransactionsById(@PathVariable Long num) {
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            Transaction requiredTransaction = this.transactionService.getTransactionByNum(num);
            List<Transaction> requiredTransactionList = new ArrayList<>();
            requiredTransactionList.add(requiredTransaction);
            map.put(StatusMessages.FOUND, "Transaction " + num + " Found successfully");
            TransactionAPIResponse response = new TransactionAPIResponse(map, requiredTransactionList);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (TransactionNotFoundException e) {
            System.out.println("Error: Transaction not found with Num: " + num + " and " + e.getMessage());
            map.put(StatusMessages.NOT_FOUND, "Error: Transaction not found with Num: " + num);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            // Check if a transaction with the same transaction_id (or any unique identifier) already exists
            // You may use a similar method as customerService.getCustomerById() for transactions
            try {
                Transaction newTransaction = transactionService.getTransactionByNum(transaction.getTransNum());
            } catch (TransactionNotFoundException e) {
                // Create the new transaction
                Transaction createdTransaction = this.transactionService.createTransaction(transaction);
                // Need to check if this transaction is made by a new Customer => If Yes, we also have to create a new
                // Customer = SO here we have already used the "updateCustomerFromTransaction" from Customer Service
                // in Transaction Service class

                List<Transaction> newTransactionList = new ArrayList<>();
                newTransactionList.add(createdTransaction);
                map.put(StatusMessages.CREATED, "Transaction created successfully");
                TransactionAPIResponse response = new TransactionAPIResponse(map, newTransactionList);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            // Transaction with the same identifier already exists, return a conflict response
            map.put(StatusMessages.CONFLICT, "Transaction already exists - Can't create!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
        } catch (Exception e) {
            System.out.println("Error creating transaction: " + e.getMessage());
            map.put(StatusMessages.NOT_CREATED, "Error creating transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PutMapping("/transactions")
    public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction)
    {
        Map<StatusMessages , String> map = new HashMap<>();
        try {
            this.transactionService.updateTransaction(transaction);
            map.put(StatusMessages.SUCCESS, "Transaction updated successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
        } catch (TransactionNotFoundException e) {
            map.put(StatusMessages.FAILURE, "Error updating Transaction details. Enter correct Transaction Num." );
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }

    @DeleteMapping("/transactions/{num}")
    public ResponseEntity<Map<StatusMessages, String>> deleteTransactionByNum(@PathVariable Long num) {
        System.out.println("Delete Transaction: " + num);
        Map<StatusMessages, String> map = new HashMap<>();
        try {
            this.transactionService.deleteTransactionByNum(num);
            map.put(StatusMessages.SUCCESS, "Transaction " + num + " deleted successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
        } catch (TransactionNotFoundException e) {
            System.out.println("Error: Transaction not found with Num: " + num + " and " + e.getMessage());
            map.put(StatusMessages.FAILURE, "Error: Transaction not found with Num: " + num);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
        }
    }

    /// Aggregations
    @GetMapping("/transactions/gender/{gender}")
    public List<Transaction> getTransactionsByGender(@PathVariable String gender) {
        return transactionService.getTransactionsByGender(gender);
    }

    @GetMapping("/transactions/category/{category}")
    public List<Transaction> getTransactionsByCategory(@PathVariable String category) {
        return transactionService.getTransactionsByCategory(category);
    }

    @GetMapping("/transactions/merchant/{merchant}")
    public List<Transaction> getTransactionsByMerchant(@PathVariable String merchant) {
        return transactionService.getTransactionsByMerchant(merchant);
    }

    @GetMapping("/transactions/city/{city}")
    public List<Transaction> getTransactionsByCity(@PathVariable String city) {
        return transactionService.getTransactionsByCity(city);
    }

    @GetMapping("/transactions/state/{state}")
    public List<Transaction> getTransactionsByState(@PathVariable String state) {
        return transactionService.getTransactionsByState(state);
    }

    @GetMapping("/transactions/profession/{profession}")
    public List<Transaction> getTransactionsByProfession(@PathVariable String profession) {
        return transactionService.getTransactionsByProfession(profession);
    }

    @GetMapping("/transactions/amount-range/{minAmount}/{maxAmount}")
    public List<Transaction> getTransactionsByAmountRange(
            @PathVariable double minAmount,
            @PathVariable double maxAmount) {
        return transactionService.getTransactionsByAmountRange(minAmount, maxAmount);
    }

    /////
    @GetMapping("/transactions/transactionTotalsByState")
    public List<StateSales> getTransactionTotalsByState() {
        return transactionService.getTransactionTotalsByState();
    }

    @GetMapping("/transactions/CategorySpendByState/{state}")
    public List<CategorySpend> getCategorySpendByState(@PathVariable String state) {
        return transactionService.getCategorySpendByState(state);
    }

    @GetMapping("/transactions/LowHighTransactionTotals/{amount}")
    public List<LowHigh> getLowHighTransactionTotals(@PathVariable double amount) {
        return transactionService.getLowHighTransactionTotals(amount);
    }
}
