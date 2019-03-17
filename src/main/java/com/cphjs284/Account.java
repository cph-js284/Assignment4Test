package com.cphjs284;


/**
 * Account
 */
public class Account {

    private double balance;
    //private int accountNumber;

    public Account(/*int _newAccountNumber*/) {
        //accountNumber=_newAccountNumber;
        balance = 0.0;
    }

    public boolean Deposit(double money){
        if(money > 0){
            balance += money;
            return true;
        }else{
            return false;
        }
    }

    public double GetBalance(){
        return balance;
    }

    public boolean Withdraw(double money){
        if(balance - money < 0.0){
            return false;

        }else{
            balance -= money;
            return true;
        }
    }
    public double GetMonthlyInterest(){
        if(balance >= 1000.0)
            return 0.07;
        if(balance > 100.0)
            return 0.05;
        if(balance > 0.0) //should this be >= 0.0 ? wording is not precise in assignment
            return 0.03;

        return 0.0; // if balance is negative no interest is given
    }
}