package com.luxoft.lab4.t1;

import com.luxoft.lab4.experimental.PerformanceTest;
import com.luxoft.lab4.experimental.PerformanceTestRunner;
import com.luxoft.lab4.utils.EfficiencyTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * TODO Please implement the LIFOQueueImpl using any java collection as a composite, for good time efficiency.
 */
@RunWith(PerformanceTestRunner.class)
public class LIFOQueueImplTest {

    @PerformanceTest(runningTimeLimit = 1000)
    public void runEfficiencyTest() throws Exception {
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
