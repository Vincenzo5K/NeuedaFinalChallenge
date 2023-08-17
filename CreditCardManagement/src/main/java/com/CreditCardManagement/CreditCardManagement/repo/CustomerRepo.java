package com.CreditCardManagement.CreditCardManagement.repo;

import com.CreditCardManagement.CreditCardManagement.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends MongoRepository<Customer, Long> {
    @Query("{ customer_id: ?0 }")
    Optional<Customer> findByCustomer_Id(Long customer_id);

}
