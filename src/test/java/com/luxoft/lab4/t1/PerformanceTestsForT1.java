package com.luxoft.lab4.t1;

import com.luxoft.lab4.util.PerformanceTest;
import com.luxoft.lab4.util.PerformanceTestRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(PerformanceTestRunner.class)
public class PerformanceTestsForT1 {

    /** if the performance test does not pass, you might have chosen incorrect collection */
    @PerformanceTest(runningTimeLimit = 50)
    public void fifoQueueTimeTest() throws Exception {
        FIFOQueue<Integer> fifo = new FIFOQueueImpl<>();
        for (int i=0; i<1000000; i++) {
            fifo.put(i);
        }
        for (int i=0; i<1000000; i++) {
            assertEquals(Integer.valueOf(i), fifo.poll());
        }
        assertEquals(null, fifo.peek());
    }

    /** if the performance test does not pass, you might have chosen incorrect collection */
    @PerformanceTest(runningTimeLimit = 1000)
    public void lifoQueueTimeTest() throws Exception {
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
