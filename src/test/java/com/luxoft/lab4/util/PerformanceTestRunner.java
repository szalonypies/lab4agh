package com.luxoft.lab4.util;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * Created by aniamamam on 2014-04-24.
 */

public class PerformanceTestRunner extends Runner implements Filterable {
    private final Class<?> testClass;
    private Description testSuiteDescription;
    private ArrayList<Filter> filters = new ArrayList<>();
    private boolean initialized = false;

    class TestInfo {
        PerformanceTest et;
        boolean ignore = false;
        Description description;

    }

    List<TestInfo> testParameters = new ArrayList<>();

    private static long createBenchmarkTime() {
        long benchmarkTime = Long.MAX_VALUE;
        int[] items = new int[1000];
        Random rnd = new Random(1337L);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            doBenchmarkOperations(items, rnd);
        }
        long elapsed = System.currentTimeMillis() - start;
        if (elapsed < benchmarkTime) {
            benchmarkTime = elapsed;
        }
        System.err.println("Benchmark time = " + benchmarkTime);
        return benchmarkTime;
    }

    private static void doBenchmarkOperations(int[] items, Random rnd) {
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            lst.add(rnd.nextInt());
        }
        int j = 0;
        for (Integer i : lst) {
            items[j++] = i;
        }
    }

    public PerformanceTestRunner(Class<?> testClass) {
        super();
        testSuiteDescription = Description.createSuiteDescription(testClass.getName());
        this.testClass = testClass;
        for (Method m : testClass.getMethods()) {
            PerformanceTest testParameter = m.getAnnotation(PerformanceTest.class);
            Ignore ignore = m.getAnnotation(Ignore.class);
            if (testParameter != null) {
                TestInfo testInfo = new TestInfo();
                testParameters.add(testInfo);
                testInfo.description = Description.createTestDescription(testClass, m.getName());
                if (testParameter != null) {
                    testInfo.et = testParameter;
                }
                if (ignore != null) {
                    testInfo.ignore = true;
                }
            }
        }

    }

    boolean filtersShouldRun(TestInfo ti) {
        for (Filter f : filters) {
            if (!f.shouldRun(ti.description)) {
                return false;
            }
        }
        return true;
    }

    int count = 0;

    @Override
    public Description getDescription() {

        if (count++ == 0) {
            testParameters = testParameters.stream().filter(x -> filtersShouldRun(x)).collect(Collectors.toList());
            for (TestInfo testInfo : testParameters) {
                testSuiteDescription.addChild(testInfo.description);
            }

            initialized = true;
        }

        return testSuiteDescription;

    }

    @Override
    public void run(RunNotifier notifier) {
        long benchmarkTime = createBenchmarkTime();
        notifier.fireTestRunStarted(testSuiteDescription);

        Result result = new Result();
        RunListener listener = result.createListener();
        for (int i = 0; i < testSuiteDescription.getChildren().size(); i++) {
            Description description = testParameters.get(i).description;
            try {
                System.err.println("Running test for method " + description.getMethodName());
                notifier.fireTestStarted(description);
                runJUnit(testParameters.get(i), description.getMethodName(), benchmarkTime);
                System.err.println("Finished test for method " + description.getMethodName());
                if (testParameters.get(i).ignore) {
                    notifier.fireTestIgnored(description);
                    listener.testIgnored(description);
                } else {
                    notifier.fireTestFinished(description);
                    listener.testFinished(description);
                }
            } catch (Throwable e) {
                Failure failure = new Failure(description, e);
                try {
                    listener.testFailure(failure);
                    notifier.fireTestFailure(failure);
                    notifier.fireTestFinished(description);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
        notifier.fireTestRunFinished(result);
    }

    private void runJUnit(TestInfo testInfo, String methodName, long benchmarkTime) throws Exception {
        String cp = System.getProperty("java.class.path");
        System.err.println(cp);
        String memParam = "";
        if (testInfo.et.heapMaxMb() != 0) {
            memParam = "-Xmx" + testInfo.et.heapMaxMb() + "M";
        }
        Process p = new ProcessBuilder()
                .command("java", "-cp", cp, memParam, getClass().getCanonicalName(), testClass.getCanonicalName(), methodName)
                .redirectErrorStream(true)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .start();
        final long runningTimeLimit = testInfo.et.runningTimeLimit() * benchmarkTime;
        final long start = System.currentTimeMillis();
        try {
            final int result;
            if (runningTimeLimit == 0) {
                result = p.waitFor();
            } else {
                result = waitFor(p, runningTimeLimit, TimeUnit.MILLISECONDS);
                long elapsed = System.currentTimeMillis() - start;
                System.out.println("Test finished in " + elapsed + " (" + (elapsed / benchmarkTime)
                        + " benchmark units). Limit = " + runningTimeLimit + " (" + testInfo.et.runningTimeLimit()
                        + " benchmark units)");
            }
            if (result != 0) {
                throw new AssertionError("Test returned non-zero value.");
            }
        } catch (TimeoutException e) {
            p.destroy();
            long elapsed = System.currentTimeMillis() - start;
            throw new AssertionError("Test didn't finish in given time (time = " + elapsed + " limit = " + runningTimeLimit + "ms).");

        }
    }

    public int waitFor(Process process, long timeout, TimeUnit unit)
            throws InterruptedException, TimeoutException {
        long startTime = System.nanoTime();
        long rem = unit.toNanos(timeout);

        do {
            try {
                return process.exitValue();
            } catch (IllegalThreadStateException ex) {
                if (rem > 0)
                    Thread.sleep(
                            Math.min(TimeUnit.NANOSECONDS.toMillis(rem) + 1, 100));
            }
            rem = unit.toNanos(timeout) - (System.nanoTime() - startTime);
        } while (rem > 0);
        throw new TimeoutException();
    }


    private static Object reflectMeATestContainingInstance(Class<?> testClass) {
        try {
            return testClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Description createTestDescription(String description) {
        return Description.createTestDescription(testClass, description);
    }

    public static void main(String[] args) throws Exception {
        String testClassName = args[0];
        String methodName = args[1];
        Class<?> testClass = Class.forName(testClassName);
        Method method = testClass.getMethod(methodName);
        Object testContainingInstance = reflectMeATestContainingInstance(testClass);
        method.invoke(testContainingInstance);
    }

    @Override
    public void filter(Filter filter) throws NoTestsRemainException {
        filters.add(filter);
    }
}