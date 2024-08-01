package com.parth.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunThreads {
    public static void main(String[] args) {
        System.out.println();

        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        threadPool.execute(() -> runJob("Job1"));
        threadPool.execute(() -> runJob("Job2"));
        threadPool.execute(() -> runJob("Job3"));

        threadPool.shutdown();

        try {
            boolean finished = threadPool.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Finished? " + finished);
        } catch (InterruptedException itrException) {
            System.out.println("Exception: " + itrException);
        }

        threadPool.shutdownNow();
    }

    private static void runJob(String jobName) {
        for (int i = 0; i < 25; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(jobName + " is running on " + threadName);
        }
    }
}
