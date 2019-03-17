package com.cphjs284;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
/**
 * AccountTest
 */
@RunWith(JUnitPlatform.class)
public class AccountTest {

    public Account account;
    public double amount;
    

    @BeforeEach
    public void SetupBeforeEachTest(){
        account = new Account();
        amount = 0.0;
    }

    @AfterEach
    public void TearDownAfterEachTest(){
        account= null;
        amount = 0.0;
    }


    @Test
    public void DepositWithDraw_shouldReturnBalanceSet(){
        amount = 100.0;
        account.Deposit(amount);
        assertThat(account.GetBalance(),equalTo(amount));
    }

    @Test
    public void REFLECTION_DepositWithDraw_shouldReturnBalanceSet()throws NoSuchFieldException, IllegalAccessException{
        amount = 100.0;
        Field balance = Account.class.getDeclaredField("balance");
        balance.setAccessible(true);
        balance.set(account, amount);
        assertThat(account.GetBalance(), equalTo(amount));
    }

    /*ECP tests */

    @Test
    public void GetMonthlyInterestNegNum_ShouldReturn0() throws IllegalAccessException, NoSuchFieldException{
        amount = -1.0;
        double expected =0.0;
        Field balance = Account.class.getDeclaredField("balance");
        balance.setAccessible(true);
        balance.set(account, amount);

        assertThat(account.GetMonthlyInterest(), equalTo(expected));
    }


    @Test
    public void GetMonthlyInterestNumBelow100_ShouldReturn003() throws IllegalAccessException, NoSuchFieldException{
        amount = 10.0;
        double expected =0.03;
        Field balance = Account.class.getDeclaredField("balance");
        balance.setAccessible(true);
        balance.set(account, amount);

        assertThat(account.GetMonthlyInterest(), equalTo(expected));
    }



    @Test
    public void GetMonthlyInterestNumBelow1000_ShouldReturn005() throws IllegalAccessException, NoSuchFieldException{
        amount = 500.0;
        double expected =0.05;
        Field balance = Account.class.getDeclaredField("balance");
        balance.setAccessible(true);
        balance.set(account, amount);

        assertThat(account.GetMonthlyInterest(), equalTo(expected));
    }

    @Test
    public void GetMonthlyInterestNumAbove1000_ShouldReturn007() throws IllegalAccessException, NoSuchFieldException{
        amount = 1500.0;
        double expected =0.07;
        Field balance = Account.class.getDeclaredField("balance");
        balance.setAccessible(true);
        balance.set(account, amount);

        assertThat(account.GetMonthlyInterest(), equalTo(expected));
    }

    /*BV tests */
    @Test
    public void GetMonthlyInterestNumExactly0_ShouldReturn0() throws IllegalAccessException, NoSuchFieldException{
        amount = 0.0;
        double expected =0.0;
        Field balance = Account.class.getDeclaredField("balance");
        balance.setAccessible(true);
        balance.set(account, amount);

        assertThat(account.GetMonthlyInterest(), equalTo(expected));
    }



    @Test
    public void GetMonthlyInterestNumExactly100_ShouldReturn003() throws IllegalAccessException, NoSuchFieldException{
        amount = 100.0;
        double expected =0.03;
        Field balance = Account.class.getDeclaredField("balance");
        balance.setAccessible(true);
        balance.set(account, amount);

        assertThat(account.GetMonthlyInterest(), equalTo(expected));
    }

    @Test
    public void GetMonthlyInterestNumExactly1000_ShouldReturn007() throws IllegalAccessException, NoSuchFieldException{
        amount = 1000.0;
        double expected =0.07;
        Field balance = Account.class.getDeclaredField("balance");
        balance.setAccessible(true);
        balance.set(account, amount);

        assertThat(account.GetMonthlyInterest(), equalTo(expected));
    }

}