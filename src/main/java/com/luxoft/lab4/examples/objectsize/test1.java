package com.luxoft.lab4.examples.objectsize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class test1 {
    public static void main(String[] args) {

        List<String> lst = new ArrayList<>();
        lst.addAll(Arrays.asList("marek","basia","kotek","paw"));
        for (Iterator<String> iterator = lst.iterator(); iterator.hasNext(); ) {
            String l = iterator.next();
            if (l.contains("a")) {
                iterator.remove();
            }
        }
    }
}
