package com.luxoft.lab4.t6;

import com.luxoft.lab4.experimental.EfficiencyTest;
import com.luxoft.lab4.experimental.MemEfficiencyRunner;
import com.luxoft.lab4.utils.EfficiencyTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

/**
 * Created by aniamamam on 2014-04-23.
 */

@RunWith(MemEfficiencyRunner.class)
public class MessageCleanerTest extends EfficiencyTestUtil {
    private static Random random = new Random(123456L);

    /** Why does this code execute so slow? */
    @EfficiencyTest(runningTimeLimit = 1000)
    @Test
    public void testRunningTimeEasy() throws Exception {
        createIntegersAndPutThemInStore(100_000);
    }

    /** Why the code requires more than 56M to run? How can we get lower on memory consumption?
     * Which list uses more memory than others? */
    @EfficiencyTest(heapMaxMb = 56)
    @Test
    public void testMemEfficiencyEasy() throws Exception {
        createIntegersAndPutThemInStore(1_000_000);
    }

    /** Why the code requires more than 32M to run? How can we get lower on memory consumption? */
    @EfficiencyTest(heapMaxMb = 32)
    @Test
    public void testMemEfficiencyIntermediate() throws Exception {
        createIntegersAndPutThemInStore(1_000_000);
    }

    /** Why the code requires more than 29M to run? To get so low we can go with Java's best suited collection */
    @EfficiencyTest(heapMaxMb = 29)
    @Test
    public void testMemEfficiencyExpert() throws Exception {
        createIntegersAndPutThemInStore(1_000_000);
    }

    /** To go as low as 12M we need to craft a custom solution (we have already written it before) */
    @EfficiencyTest(heapMaxMb = 12)
    @Test
    public void testMemEfficiencyMaster() throws Exception {
        createIntegersAndPutThemInStore(1_000_000);
    }

    private void expectMaxTimeWithNumberOfMessages(int timeInMsec, int numberOfMessages) throws Exception {
        runTestTimeConstraint(timeInMsec, String.valueOf(numberOfMessages));
    }

    private void expectMaxMemoryWithNumberOfMessages(int memory, int numberOfMessages) throws Exception {
        runTestMemConstraint(memory, String.valueOf(numberOfMessages));
    }


    private void createIntegersAndPutThemInStore(int howManyIntegers) {
        int[] testIntegers = createTestIntegers(howManyIntegers);

        MessagesIds messageIds = new MessageIdsListImpl();

        for (int i : testIntegers) {
            messageIds.addId(i);
        }

        MessageCleaner cleaner = new MessageCleaner();
        cleaner.setMessageIds(messageIds);
        cleaner.setMessageDbStore(new TestDbStore(testIntegers));
        cleaner.cleanup();

    }


    private static int[] createTestIntegers(int howManyIntegers) {
        int[] integers = new int[howManyIntegers];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt();
        }
        return integers;
    }

    private static class TestDbStore implements MessageDbStore {
        private final int[] testIntegers;
        private int pos = 0;

        TestDbStore(int[] testIntegers) {
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
}
