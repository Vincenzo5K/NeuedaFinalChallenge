package com.CreditCardManagement.CreditCardManagement.repo;

import com.CreditCardManagement.CreditCardManagement.model.Transaction;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends MongoRepository<Transaction, Long> {
    @Query("{ trans_num: ?0 }")
    Optional<Transaction> findByTrans_num(Long trans_num);

    /// Aggregations
    @Aggregation(pipeline = {
            "{$match: {gender: ?0}}",
            // Sort the results in ascending order
            "{$sort: {trans_num: 1}}",
            "{$limit: 10}"
    })
    List<Transaction> findByGender(String gender);

    // Aggregation to filter transactions by spending category
    @Aggregation(pipeline = {
            "{$match: {category: ?0}}",
            "{$sort: {trans_num: 1}}",
            "{$limit: 10}"
    })
    List<Transaction> findByCategory(String category);

    // Aggregation to filter transactions by merchant
    @Aggregation(pipeline = {
            "{$match: {merchant: ?0}}",
            "{$sort: {trans_num: 1}}",
            "{$limit: 10}"
    })
    List<Transaction> findByMerchant(String merchant);

    // Aggregation to filter transactions by city
    @Aggregation(pipeline = {
            "{$match: {city: ?0}}",
            "{$sort: {trans_num: 1}}",
            "{$limit: 10}"
    })
    List<Transaction> findByCity(String city);

    // Aggregation to filter transactions by state
    @Aggregation(pipeline = {
            "{$match: {state: ?0}}",
            "{$sort: {trans_num: 1}}",
            "{$limit: 10}"
    })
    List<Transaction> findByState(String state);

    // Aggregation to filter transactions by profession (job)
    @Aggregation(pipeline = {
            // Lookup to join with the customer collection
            "{$lookup: {from: 'Customer', localField: 'customer_id', foreignField: 'customer_id', as: 'customer'}}",
            // Unwind the customer array
            "{$unwind: '$customer'}",
            // Match transactions by profession
            "{$match: {'customer.job': ?0}}",
            // Sort the results by trans_date_trans_time in ascending order
            "{$sort: {trans_num: 1}}",
            // Limit the number of results
            "{$limit: 10}"
    })
    List<Transaction> findByProfession(String profession);

    // Aggregation to filter transactions by amount range
    @Aggregation(pipeline = {
            // Match transactions by amount range
            "{$match: {amt: {$gte: ?0, $lte: ?1}}}",
            "{$sort: {trans_num: 1}}",
            "{$limit: 10}"
    })
    List<Transaction> findByAmountRange(double minAmount, double maxAmount);
}
