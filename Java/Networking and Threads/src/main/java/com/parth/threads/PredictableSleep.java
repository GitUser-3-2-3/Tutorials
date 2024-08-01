package com.parth.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PredictableSleep {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(PredictableSleep::sleepThenPrint);
        System.out.println("back in the main");
        executor.shutdown();
    }

    private static void sleepThenPrint() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException itrException) {
            System.out.println("Exception: " + itrException);
        }
        System.out.println("top o' the stack");
    }
}
