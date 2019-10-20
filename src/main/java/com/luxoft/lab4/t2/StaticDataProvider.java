package com.luxoft.lab4.t2;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by aniamamam on 2014-05-04.
 */
public class StaticDataProvider {
    HashMap<CurrencyExchangePair, BigDecimal> exchangeRatesMap = new HashMap<>();

    public BigDecimal getCurrencyExchangeRate(CurrencyExchangePair searchKey) {
        return exchangeRatesMap.get(searchKey);
    }
}
