package com.luxoft.lab4.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PerformanceTest {
    public int heapMaxMb() default 0;
    public int runningTimeLimit() default 10000;
}