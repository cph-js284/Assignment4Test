package com.cphjs284;

/**
 * CreditCard
 */
public class CreditCard {

    private Account account;
    private double discount;

    public CreditCard(Account forAccount) {
        account = forAccount;
    }

    public Account GetAccount(){
        return account;
    }
	public double GetDiscount() {
		return discount;
    }
    
    //what if a customer has both a loyaltycard and a coupon
    public void CalculateDiscount(boolean newCustomer, boolean hasLoyaltyCard, boolean hasCoupon){
        if (newCustomer){
            discount = 0.15;
            return;
        }

        //coupon outweighs loyaltycard (by design)
        if(!newCustomer && hasCoupon){
            discount=0.20;
            return;
        }

        if(!newCustomer && hasLoyaltyCard){
            discount=0.10;
            return;
        }


    }
}