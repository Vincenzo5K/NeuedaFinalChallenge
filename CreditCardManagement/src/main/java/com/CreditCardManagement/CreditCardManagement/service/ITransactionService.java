package com.CreditCardManagement.CreditCardManagement.service;

import com.CreditCardManagement.CreditCardManagement.exception.TransactionNotFoundException;
import com.CreditCardManagement.CreditCardManagement.model.Transaction;

import java.util.List;

public interface ITransactionService {
    Transaction createTransaction(Transaction transaction);

    List< Transaction > getAllTransactions();

    Transaction getTransactionByNum(Long TransactionId) throws TransactionNotFoundException;

    void deleteTransactionByNum(Long id) throws TransactionNotFoundException;

    void updateTransaction(Transaction transaction) throws TransactionNotFoundException;

    // Aggregations
    List< Transaction > getTransactionsByGender(String gender);
    List< Transaction > getTransactionsByCategory(String category);
    List< Transaction > getTransactionsByMerchant(String merchant);
    List< Transaction > getTransactionsByCity(String city);
    List< Transaction > getTransactionsByState(String state);
    List< Transaction > getTransactionsByProfession(String profession);
    List< Transaction > getTransactionsByAmountRange(double minAmount, double maxAmount);
}
