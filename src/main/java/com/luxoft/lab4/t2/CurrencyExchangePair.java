package com.luxoft.lab4.t2;

/**
 * Created by aniamamam on 2014-05-30.
 */
public class CurrencyExchangePair {

    private final String currencyFrom;
    private final String currencyTo;

    public CurrencyExchangePair(String currencyFrom, String currencyTo) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    @Override
    public String toString() {
        return "(" + currencyFrom + "->" + currencyTo + ")";
    }


}
