package com.jmpjva.week4.exceptionhandling;

import java.util.Scanner;

/**
 * @author jazeel
 * @since 2026-10-03
 *
 * What this code does:
 * This class is the main driver for the banking application.
 * It uses Scanner to accept account information and transaction amounts.
 * The program creates three BankAccount objects and performs at least one deposit and one withdrawal for each account.
 * It demonstrates try, catch, finally, throw, throws, input validation, and a custom exception. The program continues running after an exception is handled.
 */

public class MainDriver {
	
    private static final int NUMBER_OF_ACCOUNTS = 3;

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        BankAccount[] accounts = new BankAccount[NUMBER_OF_ACCOUNTS];
        System.out.println("----------------------------------------------");
        System.out.println("       JAVA BANKING EXCEPTION HANDLING");
        System.out.println("----------------------------------------------");

        for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
        	
            accounts[i] = createAccount(scanner, i + 1);
            System.out.println();
        }

        for (int i = 0; i < accounts.length; i++) {
        	
            System.out.println("----------------------------------------------");
            System.out.println("Transactions for Account " + (i + 1));
            System.out.println(accounts[i]);
            System.out.println("----------------------------------------------");
            processDeposit(scanner, accounts[i]);
            processWithdrawal(scanner, accounts[i]);
            System.out.println("Current account information:");
            System.out.println(accounts[i]);
            System.out.println();
            
        }

        System.out.println("----------------------------------------------");
        System.out.println("             FINAL ACCOUNT SUMMARY");
        System.out.println("----------------------------------------------"); 
        for (BankAccount account : accounts)
            System.out.println(account);
        scanner.close();
        System.out.println("Program completed successfully.");
    }

    private static BankAccount createAccount(Scanner scanner, int index) {
    	
        while (true) {
        	
            try {
            	
                System.out.println("Enter information for Account " + index);
                String accountNumber = readAccountNumber(scanner);
                String name = readName(scanner);
                double balance = readOpeningBalance(scanner);
                return new BankAccount(accountNumber, name, balance);
            } 
            
            catch (IllegalArgumentException e) {
                
            	System.out.println("Account creation error: " + e.getMessage());
            }
        }
    }

    private static String readAccountNumber(Scanner scanner) {
        
    	while (true) {
            
    		System.out.print("Enter 9-digit account number: ");
            String value = scanner.nextLine().trim();
            if (value.matches("\\d{9}"))
                return value;
            System.out.println("Invalid account number. It must contain exactly 9 digits.");
        }
    }

    private static String readName(Scanner scanner) {
    	
        while (true) {
        	
            System.out.print("Enter account holder name: ");
            String value = scanner.nextLine().trim();
            if (!value.isEmpty())
                return value;
            System.out.println("Name cannot be blank.");
        }
    }

    private static double readOpeningBalance(Scanner scanner) {
    	
        while (true) {
        	
            System.out.print("Enter opening balance: $");
            
            try {
            	
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (Double.isNaN(value) || Double.isInfinite(value))
                    throw new NumberFormatException();
                if (value < 0) {
                    System.out.println("Opening balance cannot be negative.");
                    continue;
                }
                
                return round(value);
            } 
            
            catch (NumberFormatException e) {
            	
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
    }

    private static void processDeposit(Scanner scanner, BankAccount account) {
        boolean successful = false;
        
        while (!successful) {
        	
            System.out.print("Enter deposit amount: $");
            
            try {
            	
                double amount = Double.parseDouble(scanner.nextLine().trim());
                account.deposit(amount);
                successful = true;
            } 
            
            catch (NumberFormatException e) {
            	
                System.out.println("Input error: Please enter a valid numeric amount.");
            } 
            
            catch (IllegalArgumentException e) {
            	
                System.out.println("Deposit error: " + e.getMessage());
            } 
            
            finally {
            	
                System.out.println("Transaction has been processed.");
            }
        }
    }

    private static void processWithdrawal(Scanner scanner, BankAccount account) {
        boolean successful = false;
        
        while (!successful) {
        	
            System.out.print("Enter withdrawal amount: $");
            
            try {
            	
                double amount = Double.parseDouble(scanner.nextLine().trim());
                account.withdraw(amount);
                successful = true;
            } 
            
            catch (NumberFormatException e) {
                
            	System.out.println("Input error: Please enter a valid numeric amount.");
            } 
            
            catch (IllegalArgumentException e) {
                
            	System.out.println("Withdrawal error: " + e.getMessage());
            } 
            
            catch (InsufficientFundsException e) {
               
            	System.out.println("Withdrawal error: " + e.getMessage());
            } finally {
                
            	System.out.println("Transaction has been processed.");
            }
        }
    }

    private static double round(double value) {
    	
        return Math.round(value * 100.0) / 100.0;
    }
}
