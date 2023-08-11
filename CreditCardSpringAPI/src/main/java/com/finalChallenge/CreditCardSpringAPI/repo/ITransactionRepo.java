package com.finalChallenge.CreditCardSpringAPI.repo;

import com.finalChallenge.CreditCardSpringAPI.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITransactionRepo extends MongoRepository<Transaction, Long> {
}
