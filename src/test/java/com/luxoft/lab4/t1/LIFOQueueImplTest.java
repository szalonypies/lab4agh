package com.luxoft.lab4.t1;

import com.luxoft.lab4.experimental.MemEfficiencyRunner;
import com.luxoft.lab4.utils.EfficiencyTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * TODO Please implement the LIFOQueueImpl using any java collection as a composite, for good time efficiency.
 */
@RunWith(MemEfficiencyRunner.class)
public class LIFOQueueImplTest extends EfficiencyTestUtil {

    @Test
    public void runEfficiencyTest() throws Exception {
        runTestTimeConstraint(1000);
    }

    public static void main(String[] args) {
        LIFOQueue<Integer> lifo = new LIFOQueueImpl<>();
        for (int i=0; i<1000000; i++) {
            lifo.put(i);
        }
        for (int i=999999; i>=0; i--) {
            assertEquals(Integer.valueOf(i), lifo.poll());
        }
        assertEquals(null, lifo.peek());
    }
}
