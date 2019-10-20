package com.luxoft.lab4.t3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by aniamamam on 2014-04-24.
 */
public class MyArrayListImplTest {
    /** List must store all 10 integers [30,29,28,...,21]*/
    @Test
    public void testIt() {
        MyArrayList<Integer> list = new MyArrayListImpl<>(10);
        for (int i=0; i<10; i++) {
            list.add(30-i);
        }

        for (int i=0; i<10; i++) {
            assertEquals(Integer.valueOf(30 - i), list.get(i));
        }
    }

    /** List must not grow above size given in constructor.
     * In such case it must throw IndexOutOfBoundsException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testDoesNotGrow() {
        MyArrayList<Integer> list = new MyArrayListImpl<>(10);
        for (int i=0; i<11; i++) {
            list.add(i);
        }
    }

    /** List must throw IndexOutOfBoundsException when get() parameter exceeds the number of elements */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOverflow() {
        MyArrayList<Integer> list = new MyArrayListImpl<>(10);
        list.add(0);
        list.add(1);
        list.get(2);
    }

}
