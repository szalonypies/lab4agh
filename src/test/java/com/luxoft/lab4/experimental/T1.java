package com.luxoft.lab4.experimental;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

/**
 * Created by aniamamam on 2014-04-25.
 */
@RunWith(PerformanceTestRunner.class)
public class T1 {
    @PerformanceTest(heapMaxMb = 10)
    @Test
    public void test1() throws Exception {
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i=0; i<10_000_000; i++) {
            lst.add(i);
        }
        System.err.println("Running test 1");
        Thread.sleep(3000);
    }

    @PerformanceTest(heapMaxMb = 10)
    @Test
    public void test2() throws Exception {
        System.err.println("Running test 2");
        Thread.sleep(2000);
    }

}
