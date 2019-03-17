package com.cphjs284;

/**
 * ATM
 */
public class ATM {

    private CreditCard creditCard;

    public ATM() {
        
    }

    public boolean InsertCC(CreditCard inCard){
        if(creditCard==null){
            creditCard = inCard;
            return true;
        }else{
            return false;
        }
    }

    public boolean ExtractCC(){
        if(creditCard!=null){
            creditCard=null;
            return true;
        }else{
            return false;
        }
    }


    public boolean DepositCC(CreditCard inCard, double amount){
        if(!(amount>0.0) || creditCard != null)
            return false;
        
        inCard.GetAccount().Deposit(amount);
        return true;
    }

    //public boolean PurchaseWithDiscount(CreditCard inCard, double amount){
        //nu skal creditkortet være istand til at slå data omkunden op??
        // jeg er ikke sikker på hvad meningen er her
    //}

    public double ShowBalance(CreditCard inCard){
        if(creditCard != null){
            return inCard.GetAccount().GetBalance();
        }else{
            return 0.0;
        }
    }

    public double ShowMonthlyRate(CreditCard inCard){
        if(creditCard!=null){
            return inCard.GetAccount().GetMonthlyInterest();
        }
        return 0.0;
    }

    public double ShowYearlyRate(CreditCard inCard){
        if(creditCard!=null){
            //yes this is not correctly calculated, am I suppose to return the actual interest rate, or 
            // the accoutn balance * interest-rate;
            return (inCard.GetAccount().GetMonthlyInterest() *12);
        }
        return 0.0;
    }


}