package com.CreditCardManagement.CreditCardManagement.service;

import com.CreditCardManagement.CreditCardManagement.dto.CategorySpend;
import com.CreditCardManagement.CreditCardManagement.dto.LowHigh;
import com.CreditCardManagement.CreditCardManagement.dto.StateSales;
import com.CreditCardManagement.CreditCardManagement.exception.TransactionNotFoundException;
import com.CreditCardManagement.CreditCardManagement.model.Transaction;
import com.CreditCardManagement.CreditCardManagement.repo.TransactionDALMongoTemplate;
import com.CreditCardManagement.CreditCardManagement.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionDALMongoTemplate transactionDALMongoTemplate;

//    private CustomerService customerService;

//    @Autowired
//    public TransactionService(TransactionRepo transactionRepo, CustomerService customerService) {
//        this.transactionRepo = transactionRepo;
//        this.customerService = customerService;
//    }
    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setId(null);
        // Save the new transaction
//        Transaction createdTransaction = transactionRepo.save(transaction);
//
//        // Update customer information based on the new transaction
//        customerService.updateCustomerFromTransaction(transaction);
//
//        return createdTransaction;
        return transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return this.transactionRepo.findAll();
    }

    @Override
    public Transaction getTransactionByNum(Long transactionNum) throws TransactionNotFoundException {
        Optional<Transaction> transactionDb = this.transactionRepo.findByTrans_num(transactionNum);

        if (transactionDb.isPresent()) {
            return transactionDb.get();
        } else {
            throw new TransactionNotFoundException("Transaction not found with Num : " + transactionNum);
        }
    }

    @Override
    public void deleteTransactionByNum(Long TransactionNum) throws TransactionNotFoundException {
        Optional < Transaction > transactionDb = this.transactionRepo.findByTrans_num(TransactionNum);

        if (transactionDb.isPresent()) {
            this.transactionRepo.delete(transactionDb.get());
        } else {
            throw new TransactionNotFoundException("Transaction not found with Num : " + TransactionNum);
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) throws TransactionNotFoundException {
        Optional < Transaction > transactionDb = this.transactionRepo.findByTrans_num(transaction.getTransNum());

        if (transactionDb.isPresent()) {
            // Update the fields of the retrieved transaction entity with the new values
            Transaction existingTransaction = transactionDb.get();
            existingTransaction.setTrans_date_trans_time(transaction.getTrans_date_trans_time());
            existingTransaction.setAmt(transaction.getAmt());
            existingTransaction.setTransNum(transaction.getTransNum());
            existingTransaction.setCustomerId(transaction.getCustomerId());
            existingTransaction.setCity(transaction.getCity());
            existingTransaction.setState(transaction.getState());
            existingTransaction.setCity_population(transaction.getCity_population());
            existingTransaction.setMerchant(transaction.getMerchant());
            existingTransaction.setCategory(transaction.getCategory());
            existingTransaction.setFirst(transaction.getFirst());
            existingTransaction.setLast(transaction.getLast());
            existingTransaction.setGender(transaction.getGender());
            existingTransaction.setJob(transaction.getJob());
            existingTransaction.setDob(transaction.getDob());

            // Save the updated transaction entity
            this.transactionRepo.save(existingTransaction);
        } else {
            throw new TransactionNotFoundException("Transaction not found with Num: " + transaction.getTransNum());
        }
    }

    // Aggregations

    @Override
    public List<Transaction> getTransactionsByGender(String gender) {
        return transactionRepo.findByGender(gender);
    }

    @Override
    public List<Transaction> getTransactionsByCategory(String category) {
        return transactionRepo.findByCategory(category);
    }

    @Override
    public List<Transaction> getTransactionsByMerchant(String merchant) {
        return transactionRepo.findByMerchant(merchant);
    }

    @Override
    public List<Transaction> getTransactionsByCity(String city) {
        return transactionRepo.findByCity(city);
    }

    @Override
    public List<Transaction> getTransactionsByState(String state) {
        return transactionRepo.findByState(state);
    }

    @Override
    public List<Transaction> getTransactionsByProfession(String profession) {
        return transactionRepo.findByProfession(profession);
    }

    @Override
    public List<Transaction> getTransactionsByAmountRange(double minAmount, double maxAmount) {
        return transactionRepo.findByAmountRange(minAmount, maxAmount);
    }

    ///
    public List<StateSales> getTransactionTotalsByState() {
        return transactionDALMongoTemplate.getTransactionTotalsByState();
    }

    public List<CategorySpend> getCategorySpendByState(String state) {
        return transactionDALMongoTemplate.getCategorySpendByState(state);
    }

    public List<LowHigh> getLowHighTransactionTotals(double amount) {
        return transactionDALMongoTemplate.getLowHighTransactionTotals(amount);
    }
}
