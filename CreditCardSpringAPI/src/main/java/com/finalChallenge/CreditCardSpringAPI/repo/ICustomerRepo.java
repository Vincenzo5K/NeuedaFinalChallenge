package com.finalChallenge.CreditCardSpringAPI.repo;

import com.finalChallenge.CreditCardSpringAPI.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICustomerRepo extends MongoRepository<Customer, Long> {
}
