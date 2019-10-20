package com.luxoft.lab4.t1;

import com.luxoft.lab4.experimental.PerformanceTest;
import com.luxoft.lab4.experimental.PerformanceTestRunner;
import com.luxoft.lab4.utils.EfficiencyTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * TODO Please implement the FIFOQueueImpl using any java collection as a composite, for good time efficiency.
 */
@RunWith(PerformanceTestRunner.class)
public class FIFOQueueImplTest {

    /** if the performance test does not pass and you are sure you fixed the problem, please rerun */
    @PerformanceTest(runningTimeLimit = 1000)
    public void runEfficiencyTest() throws Exception {
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
