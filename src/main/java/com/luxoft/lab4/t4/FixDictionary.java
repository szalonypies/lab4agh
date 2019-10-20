package com.luxoft.lab4.t4;

import java.util.HashMap;

/**
 * Created by aniamamam on 2014-06-05.
 */
public class FixDictionary {
    HashMap<String, Integer> values = new HashMap<>();

    public int getValueForKey(String key) {
        return values.get(key);
    }
}
