package com.jmpjva.week4.exceptionhandling;

/**
 * @author jazeel
 * @since 2026-10-03
 *
 * What this code does:
 * This class represents a bank account and manages account information and banking transactions.
 * The account number must contain exactly 9 digits, the account holder name cannot be blank, and the opening balance cannot be negative.
 * The class provides deposit() and withdraw() methods and validates transaction amounts using exception handling.
 */

public class BankAccount {
	
    private final String accountNumber;
    private final String name;
    private double balance;

    public BankAccount(String accountNumber, String name, double balance) {
    	
        if (accountNumber == null || !accountNumber.matches("\\d{9}"))
            throw new IllegalArgumentException("Account number must contain exactly 9 digits.");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Account holder name cannot be blank.");
        if (Double.isNaN(balance) || Double.isInfinite(balance) || balance < 0)
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        this.accountNumber = accountNumber;
        this.name = name.trim();
        this.balance = round(balance);
    }

    public void deposit(double amount) throws IllegalArgumentException {
    	
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        amount = round(amount);
        balance = round(balance + amount);
        System.out.printf("Deposit successful: $%.2f%n", amount);
    }

    public void withdraw(double amount)
    		throws IllegalArgumentException, InsufficientFundsException {
    	if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        amount = round(amount);
        if (amount > balance)
            throw new InsufficientFundsException(
                String.format("Insufficient funds. Available balance: $%.2f", balance));
        balance = round(balance - amount);
        System.out.printf("Withdrawal successful: $%.2f%n", amount);
    }

    public String getAccountNumber() { 
    	
    	return accountNumber; 
    }
    public String getName() { 
    	
    	return name; 
    }
    public double getBalance() { 
    	
    	return balance; 
    }

    private double round(double value) {
        
    	return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
    	
        return String.format("Account: %s | Name: %s | Balance: $%.2f",
                accountNumber, name, balance);
    }
}
