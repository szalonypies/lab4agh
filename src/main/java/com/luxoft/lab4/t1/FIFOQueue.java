package com.luxoft.lab4.t1;

/**
 * Created by aniamamam on 2014-04-24.
 */
public interface FIFOQueue<T> {

    T poll();

    T peek();

    void put(T t);
}
