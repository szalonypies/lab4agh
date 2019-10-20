package com.luxoft.lab4.t1;

import com.luxoft.lab4.util.PerformanceTest;
import com.luxoft.lab4.util.PerformanceTestRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * TODO Please implement the LIFOQueueImpl using any java collection as a composite, for good time efficiency.
 */
@RunWith(PerformanceTestRunner.class)
public class LIFOQueueImplTest {

    /* if the performance test does not pass and you are sure you fixed the problem, please rerun */
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
