package com.luxoft.lab4.t1;

import com.luxoft.lab4.utils.EfficiencyTestUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO Please implement the FIFOQueueImpl using any java collection as a composite, for good time efficiency.
 */
public class FIFOQueueImplTest extends EfficiencyTestUtil {

    @Test
    public void runEfficiencyTest() throws Exception {
        runTestTimeConstraint(1000);
    }

    public static void main(String[] args) {
        FIFOQueue<Integer> fifo = new FIFOQueueImpl<>();
        for (int i=0; i<1000000; i++) {
            fifo.put(i);
        }
        for (int i=0; i<1000000; i++) {
            assertEquals(Integer.valueOf(i), fifo.poll());
        }
        assertEquals(null, fifo.peek());
    }
}
