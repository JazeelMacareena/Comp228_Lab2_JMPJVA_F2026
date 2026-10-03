package com.jmpjva.week4.exceptionhandling;

/**
 * @author jazeel
 * @since 2026-10-03
 *
 * What this code does:
 * This class creates a custom exception for the banking system.
 * It is used when a customer tries to withdraw an amount that is greater than the available account balance.
 * This custom exception extends the Exception class.
 */

public class InsufficientFundsException extends Exception {
	
    public InsufficientFundsException(String message) {
    	
        super(message);
    }
}
