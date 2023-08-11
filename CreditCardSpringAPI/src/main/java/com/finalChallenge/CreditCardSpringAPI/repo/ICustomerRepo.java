package com.finalChallenge.CreditCardSpringAPI.repo;

import com.finalChallenge.CreditCardSpringAPI.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ICustomerRepo extends MongoRepository<Customer, Long> {
    @Query("{customerId: ?0}")
    Optional<Customer> findByCustomerId(Long id);
}
