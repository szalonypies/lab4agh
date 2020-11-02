package com.luxoft.lab4.t1;

import com.luxoft.lab4.util.Nth;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO Please implement the FIFOQueueImpl using any java collection as a composite, for good time efficiency.
 */
public class FIFOQueueImplTest {

    @Test
    public void exactOrderTest() throws Exception {
        // given fifo
        FIFOQueue<Integer> fifo = new FIFOQueueImpl<>();
        for (int i = 0; i < 10; i++) {
            fifo.put(i);
        }

        // expect exact order
        for (int i = 0; i < 10; i++) {
            Integer pollResult = fifo.poll();
            int orderOfPolledElement = i + 1;
            assertEquals("Expected " + i + " for fifo " + Nth.of(orderOfPolledElement) + " polled result", Integer.valueOf(i), pollResult);
        }
    }

    @Test
    public void nullOnEmptyTest() {
        // given fifo
        FIFOQueue<Integer> fifo = new FIFOQueueImpl<>();

        // expect null
        assertEquals(null, fifo.peek());
    }

}
