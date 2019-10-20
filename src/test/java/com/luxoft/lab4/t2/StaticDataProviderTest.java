package com.luxoft.lab4.t2;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by aniamamam on 2014-05-04.
 */
public class StaticDataProviderTest {


    private ExchangeRateCalculator calculator;

    /** TODO right click on the test and select 'run test' **/
    @Test
    public void first_exercise_testGBPtoPLN() throws Exception {
        assertEquals(new BigDecimal("0.5715"), calculator.convertMoney("3", "GBP", "PLN"));

    }

    @Test
    public void second_exercise_testPLNtoGBP() throws Exception {
        assertEquals(new BigDecimal("148.293"), calculator.convertMoney("30", "PLN", "GBP"));

    }

    @Before
    public void prepareTest() {
        StaticDataProvider staticDataProvider = initStatic();

        calculator = new ExchangeRateCalculator();
        calculator.staticDataProvider = staticDataProvider;

    }

    private StaticDataProvider initStatic() {
        StaticDataProvider staticDataProvider = new StaticDataProvider();
        staticDataProvider.exchangeRatesMap.put(new CurrencyExchangePair("PLN", "GBP"), new BigDecimal("4.9431"));
        staticDataProvider.exchangeRatesMap.put(new CurrencyExchangePair("GBP", "PLN"), new BigDecimal("0.1905"));
        staticDataProvider.exchangeRatesMap.put(new CurrencyExchangePair("PLN", "USD"), new BigDecimal("2.9540"));
        staticDataProvider.exchangeRatesMap.put(new CurrencyExchangePair("USD", "PLN"), new BigDecimal("0.3187"));
        return staticDataProvider;
    }
}
