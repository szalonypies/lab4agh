package com.luxoft.lab4.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aniamamam on 2014-04-24.
 */
public class EfficiencyTestUtil {
    protected void runTestMemConstraint(int megabytesHeap, String... args) throws Exception {
        String[] argsList = prepareArguments(megabytesHeap, args);
        Process p = startProcess(argsList);
        int result = p.waitFor();
        if (result != 0) {
            throw new AssertionError("Test's subprocess didn't finish correctly. ?Memory limit hit?");
        }
    }

    protected void runTestTimeConstraint(int timeout, String... args) throws Exception {
        String[] argsList = prepareArguments(0, args);
        Process p = startProcess(argsList);
        boolean result = waitFor(p, timeout, TimeUnit.MILLISECONDS);
        if (!result) {
            p.destroy();
            throw new AssertionError("Test's subprocess didn't finish in time.");
        }
        if (p.exitValue() != 0) {
            throw new AssertionError("Test's subprocess failed with exception");
        }

    }

    public boolean waitFor(Process process, long timeout, TimeUnit unit)
            throws InterruptedException
    {
        long startTime = System.nanoTime();
        long rem = unit.toNanos(timeout);

        do {
            try {
                process.exitValue();
                return true;
            } catch(IllegalThreadStateException ex) {
                if (rem > 0)
                    Thread.sleep(
                            Math.min(TimeUnit.NANOSECONDS.toMillis(rem) + 1, 100));
            }
            rem = unit.toNanos(timeout) - (System.nanoTime() - startTime);
        } while (rem > 0);
        return false;
    }


    private Process startProcess(String[] argsList) throws IOException {
        return new ProcessBuilder()
                .command(argsList)
                .redirectErrorStream(true)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .start();
    }

    private String[] prepareArguments(int megabytesHeap, String[] args) {
        List<String> argsList = new ArrayList<>();
        String cp = System.getProperty("java.class.path");
        System.err.println(cp);
        argsList.addAll(Arrays.asList("java", "-cp", cp));
        if (megabytesHeap > 0) {
            argsList.addAll(Arrays.asList("-Xmx" + megabytesHeap + "M"));
        }
        argsList.add(getClass().getCanonicalName());
        argsList.addAll(Arrays.asList(args));
        return argsList.toArray(new String[argsList.size()]);
    }


}
