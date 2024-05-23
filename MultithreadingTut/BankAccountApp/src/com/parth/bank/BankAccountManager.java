package com.parth.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountManager {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println();

        BankAccount account = new BankAccount(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        AccountLogger logger = new AccountLogger();

        Callable<Void> depositTask = () -> {
            account.deposit(300);
            logger.log("Deposit of 300");
            return null;
        };

        Callable<Void> withdrawTask = () -> {
            account.withdraw(200);
            logger.log("Withdrawal of 200");
            return null;
        };

        Callable<Void> inquiryTask = () -> {
            int balance = account.getBalance();
            logger.log("Balance inquiry: " + balance);
            return null;
        };

        List<Future<Void>> futures = new ArrayList<>();
        futures.add(executorService.submit(depositTask));
        futures.add(executorService.submit(withdrawTask));
        futures.add(executorService.submit(inquiryTask));

        for (Future<Void> future : futures) {
            future.get();
        }

        executorService.shutdown();

        // Demonstrating wait and notify
        Thread notifierThread = new Thread(new Notifier(account));
        Thread waiterThread = new Thread(new Waiter(account));

        waiterThread.start();
        Thread.sleep(500); // Ensure waiter is waiting before notifier runs
        notifierThread.start();

        waiterThread.join();
        notifierThread.join();

        System.out.println("Final Account Balance: " + account.getBalance());
        System.out.println("Log Entries:");
        logger.printLogs();
    }
}

class BankAccount {
    private int balance;
    private final Lock lock = new ReentrantLock();
    private final Condition sufficientFundsCondition = lock.newCondition();

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
            sufficientFundsCondition.signalAll(); // Notify waiting threads that balance has been updated
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            while (amount > balance) {
                System.out.println("Waiting for sufficient funds...");
                sufficientFundsCondition.await();
            }
            balance -= amount;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}

class AccountLogger {
    private final List<String> logEntries = new ArrayList<>();

    // Method to log messages
    public synchronized void log(String message) {
        logEntries.add(message);
    }

    // Method to print the logs
    public synchronized void printLogs() {
        for (String entry : logEntries) {
            System.out.println(entry);
        }
    }
}

class Notifier implements Runnable {
    private final BankAccount account;

    public Notifier(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        System.out.println("Notifier: Depositing 500");
        account.deposit(500);
    }
}

class Waiter implements Runnable {
    private final BankAccount account;

    public Waiter(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        System.out.println("Waiter: Attempting to withdraw 700");
        account.withdraw(700);
        System.out.println("Waiter: Withdrawal of 700 successful");
    }
}