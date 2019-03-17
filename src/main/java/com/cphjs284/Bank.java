package com.cphjs284;

import java.awt.List;
import java.util.ArrayList;

/**
 * Bank
 */
public class Bank {

    ArrayList<Customer> customersList = new ArrayList<Customer>();

    public void CreateNewCustomer(){
        Customer customer = new Customer();
        customer.setNewCustomer(true);
        customer.setHasCoupon(false);
        customer.setHasLoyaltyCard(false);
        
        //yes only one account per customer in this bank
        if(!customer.CreateNewAccount())
            return;
        
        customersList.add(customer);
    }

    public void AssignCreditCard(int CustId){
        customersList.get(CustId).AssignCreditCard();
    }
}