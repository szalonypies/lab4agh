package com.luxoft.lab4.t1;

import com.luxoft.lab4.util.Nth;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO Please implement the LIFOQueueImpl using any java collection as a composite, for good time efficiency.
 */
public class LIFOQueueImplTest {

    @Test
    public void lifoQueueTimeTest() throws Exception {
        // given lifo
        LIFOQueue<Integer> lifo = new LIFOQueueImpl<>();
        for (int i = 0; i < 10; i++) {
            lifo.put(i);
        }

        // expect opposite order
        int orderOfPolledElement = 1;
        for (int i = 9; i >= 0; i--) {
            Integer pollResult = lifo.poll();
            assertEquals("Expected " + i + " for " + Nth.of(orderOfPolledElement) + " polled element", Integer.valueOf(i), pollResult);
            orderOfPolledElement++;
        }
        assertEquals(null, lifo.peek());
    }

    @Test
    public void nullOnEmptyTest() {
        // given fifo
        LIFOQueue<Integer> lifo = new LIFOQueueImpl<>();

        // expect null
        assertEquals(null, lifo.peek());
    }
}
