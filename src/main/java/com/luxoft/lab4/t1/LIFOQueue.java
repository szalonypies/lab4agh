package com.luxoft.lab4.t1;

public interface LIFOQueue<T> {

    T poll();

    T peek();

    void put(T t);
}
