package com.luxoft.lab4.examples.type_erasure;

public class Main {
    public static void main(String[] args) {
        Node<Integer> node = new Node<>(10, null);
        Integer i = node.getData();
        node.setData(20);
    }
}
