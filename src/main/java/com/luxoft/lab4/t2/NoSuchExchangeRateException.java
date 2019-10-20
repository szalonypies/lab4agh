package com.luxoft.lab4.t2;

/**
 * Created by aniamamam on 2014-05-30.
 */
public class NoSuchExchangeRateException extends Exception {
    public NoSuchExchangeRateException(String currencyFrom, String currencyTo) {
        super("The bank does not provide currency conversion from " + currencyFrom + " to " + currencyTo + ". Please try with an intermediate currency");
    }
}
