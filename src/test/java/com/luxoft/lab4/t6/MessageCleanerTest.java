package com.luxoft.lab4.t6;

import com.luxoft.lab4.util.PerformanceTest;
import com.luxoft.lab4.util.PerformanceTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.util.Random;

/**
 * Created by aniamamam on 2014-04-23.
 */

@RunWith(PerformanceTestRunner.class)
public class MessageCleanerTest {
    private static Random random = new Random(123456L);

    /** Why does this code execute so slow? */
    @PerformanceTest(runningTimeLimit = 100)
    @Test
    public void testRunningTimeEasy() throws Exception {
        createIntegersAndPutThemInStore(1_000_000);
    }

    /** Try to decrease the heap below 42. it seems that 42 is the boundary right?
     * Which list uses more memory than the other? */
    @PerformanceTest(heapMaxMb = 50)
    @Test
    public void testMemEfficiencyEasy() throws Exception {
        createIntegersAndPutThemInStore(1_000_000);
        printMemoryUsage();
    }

    /** Why the code requires more than 32M to run? How can we get lower on memory consumption? */
    @PerformanceTest(heapMaxMb = 32)
    @Test
    public void testMemEfficiencyIntermediate() throws Exception {
        createIntegersAndPutThemInStore(1_000_000);
    }

    /** Why the code requires more than 26M to run? To get so low we can go with Java's best suited collection */
    @PerformanceTest(heapMaxMb = 26)
    @Test
    public void testMemEfficiencyExpert() throws Exception {
        createIntegersAndPutThemInStore(1_000_000);
        printMemoryUsage();
    }

    /** To go as low as 12M we need to craft a custom solution (we have already written it before) */
    @PerformanceTest(heapMaxMb = 12)
    @Test
    public void testMemEfficiencyMaster() throws Exception {
        createIntegersAndPutThemInStore(1_000_000);
        printMemoryUsage();
    }

    private void createIntegersAndPutThemInStore(int howManyIntegers) {
        int[] testIntegers = createTestIntegers(howManyIntegers);

        MessagesIds messageIds = new MessageIdsListImpl();

        for (int i : testIntegers) {
            messageIds.addId(i);
        }

        MessageCleaner cleaner = new MessageCleaner();
        cleaner.setMessageIds(messageIds);
        cleaner.setMessageDbStore(new MockedDbStore(testIntegers));
        cleaner.cleanup();

    }


    private static int[] createTestIntegers(int howManyIntegers) {
        int[] integers = new int[howManyIntegers];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt();
        }
        return integers;
    }

    private static class MockedDbStore implements MessageDbStore {
        private final int[] testIntegers;
        private int pos = 0;

        MockedDbStore(int[] testIntegers) {
            this.testIntegers = testIntegers;
        }

        @Override
        public void cleanupMessage(int messageId) {
            int expectedInteger = testIntegers[pos++];
            if (expectedInteger != messageId) {
                throw new AssertionError("Expected to cleanup messageId=" + expectedInteger + " but " + messageId + " found.");
            }
        }
    }

    private void printMemoryUsage() {
        for (MemoryPoolMXBean mpBean: ManagementFactory.getMemoryPoolMXBeans()) {
            if (mpBean.getType() == MemoryType.HEAP) {
                System.out.printf(
                        "Name: %s: %s\n",
                        mpBean.getName(), mpBean.getUsage()
                );
            }
        }
    }

}
