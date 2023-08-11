package com.finalChallenge.CreditCardSpringAPI.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id) {
        super("Could not find customer item " + id);
    }
}
