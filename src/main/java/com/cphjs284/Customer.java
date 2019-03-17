package com.cphjs284;

/**
 * Customer
 */
public class Customer {

    private Account account;
    private CreditCard creditCard;
    private boolean newCustomer, hasLoyaltyCard, hasCoupon;
    
/*
    public Customer(boolean _newCustomer, boolean _hasLoyaltyCard ) {
        newCustomer = _newCustomer;
        hasLoyaltyCard = _hasLoyaltyCard;
        
    }
*/
    public Customer() {
        
    }

    public void setNewCustomer(boolean val){
        newCustomer=val;
    }
    public void setHasLoyaltyCard(boolean val){
        hasLoyaltyCard=val;
    }
    public void setHasCoupon(boolean val){
        hasCoupon=val;
    }

    public boolean CreateNewAccount(){
        if (account==null){
            account = new Account();
            return true;
        }else{
            return false;
        }
    }

    public boolean CloseAccount(){
        double amount = account.GetBalance();
        if(amount >= 0){
            account.Withdraw(amount);
            account = null;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean AssignCreditCard(){
        if(creditCard == null){
            creditCard = new CreditCard(account);
            //i dont like this
            creditCard.CalculateDiscount(newCustomer, hasLoyaltyCard, hasCoupon);
            return true;
        }else{
            return false;
        }
    }
}