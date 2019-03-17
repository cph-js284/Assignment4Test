package com.cphjs284;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import org.junit.jupiter.api.function.Executable;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
/**
 * CreditCardTest
 */

@RunWith(JUnitPlatform.class)
public class CreditCardTest {

    /*part 2 and part 3 for creditcard.getDiscount is rolled into one -
    that is in order to test getDiscount surfficiently repeated and parameterized tests
    will be conducted */
    public Account account;
    public double amount;
    public CreditCard creditCard;
    public boolean newCustomer;
    public boolean hasLoyalltyCard;
    public boolean hasCoupon;
    

    @BeforeEach
    public void SetupBeforeEachTest(){
        account = new Account();
        creditCard = new CreditCard(account);
        amount = 0.0;
    }

    @AfterEach
    public void TearDownAfterEachTest(){
        account= null;
        creditCard = null;
        amount = 0.0;
    }

    //list with testdata used both in repeated and dynamic tests
    List<TestData> testdatalist = new ArrayList<>(
        Arrays.asList(
            new TestData(true,true,true,0.15),
            new TestData(true,true,false,0.15),
            new TestData(true,false,true,0.15),
            new TestData(true,false,false,0.15),
            //customer has both a loyaltycard and coupon -> coupon outweighs loyaltycard
            new TestData(false,true,true,0.2),
            new TestData(false,true,false,0.1),
            new TestData(false,false,true,0.2),
            new TestData(false,false,false,0.0)
        )
    );
    
    //repeated test

    @RepeatedTest(value = 8)
    public void RepeatedTestCalculateDiscount_ShouldStoreDiscount(RepetitionInfo repetitionInfo)throws NoSuchFieldException, IllegalAccessException{
        Field discount = CreditCard.class.getDeclaredField("discount");
        discount.setAccessible(true);
        int index = repetitionInfo.getCurrentRepetition() -1;

        creditCard.CalculateDiscount(testdatalist.get(index).val1, testdatalist.get(index).val2, testdatalist.get(index).val3);
        
        assertThat(discount.get(creditCard), equalTo(testdatalist.get(index).interest));
    }


    //testdata used in parameterized test below
    public static Collection<Object[]> TestDataParams(){
        return Arrays.asList( new Object[][]{
            {true,true,true,0.15},
            {true,true,false,0.15},
            {true,false,true,0.15},
            {true,false,false,0.15},
            //customer has both a loyaltycard and coupon -> coupon outweighs loyaltycard
            {false,true,true,0.2},
            {false,true,false,0.1},
            {false,false,true,0.2},
            {false,false,false,0.0}
        });

    }

    //parameterized test
    @ParameterizedTest
    @MethodSource("TestDataParams")
    public void ParamsTestCalculateDiscount_ShouldStoreDiscount
    (boolean _newCustomer, boolean _hasLoyaltyCard, boolean _hasCoupon, double _expected)throws NoSuchFieldException, IllegalAccessException{

        Field discount = CreditCard.class.getDeclaredField("discount");
        discount.setAccessible(true);
        creditCard.CalculateDiscount(_newCustomer, _hasLoyaltyCard, _hasCoupon);
        assertThat(discount.get(creditCard), equalTo(_expected));

    }

    //dynamic test
    //all these test fail, the expected value never gets updated is stays 0.2, which leads to all the fails
    @TestFactory
    public List<DynamicTest> Dynamic_TestFactoryCalculateDiscount()throws NoSuchFieldException{
        List<DynamicTest> res = new ArrayList<>();
        int NameCounter = 0;
        Field discount = CreditCard.class.getDeclaredField("discount");
        discount.setAccessible(true);


        for (TestData item : testdatalist) {
            creditCard.CalculateDiscount(item.val1, item.val2, item.val3);

            Executable exec = ()->assertThat(discount.get(creditCard), equalTo(item.interest));
            String testName = "Dtest" + NameCounter;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(testName, exec);
            res.add(dynamicTest);
            NameCounter++;

        }

        return res;
    }

}