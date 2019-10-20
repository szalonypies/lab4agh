package com.luxoft.lab4.t2;

import java.math.BigDecimal;

/**
 * Created by aniamamam on 2014-05-04.
 */
public class ExchangeRateCalculator {
    StaticDataProvider staticDataProvider;

    public BigDecimal convertMoney(String amount, String currencyFrom, String currencyTo) throws NoSuchExchangeRateException {
        BigDecimal exchangeRate = staticDataProvider.getCurrencyExchangeRate(new CurrencyExchangePair(currencyFrom, currencyTo));
        if (exchangeRate == null) {
            throw new NoSuchExchangeRateException(currencyFrom, currencyTo);
        }

        return new BigDecimal(amount).multiply(exchangeRate);


    }
}
