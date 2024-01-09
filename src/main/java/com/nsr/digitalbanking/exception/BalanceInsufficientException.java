package com.nsr.digitalbanking.exception;

public class BalanceInsufficientException extends Exception {
    public BalanceInsufficientException(String message) {
        super(message);
    }
}
