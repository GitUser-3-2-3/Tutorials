package com.parth.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PredictableLatch {
    public static void main(String[] args) {
        System.out.println();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(1);

        executor.execute(() -> waitForLatchThenPrint(latch));

        System.out.println("back in the main");
        latch.countDown();

        executor.shutdown();
    }

    private static void waitForLatchThenPrint(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException itrException) {
            System.out.println("Exception: " + itrException);
        }
        System.out.println("top o' the stack");
    }
}
