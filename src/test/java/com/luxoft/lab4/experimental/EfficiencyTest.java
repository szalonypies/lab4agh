package com.luxoft.lab4.experimental;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EfficiencyTest {
    public int heapMaxMb() default 0;
    public int runningTimeLimit() default 10000;
}