package com.finalChallenge.CreditCardSpringAPI.exceptions;

public class TransactionNotFoundException extends RuntimeException{
    TransactionNotFoundException(Long id) {
        super("Could not find Transaction item " + id);
    }

}
