package com.parth.concurrency;

import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class RyanAndMonicaTest {
    public static void main(String[] args) {
        System.out.println();
        BankAccount account = new BankAccount();

        RyanAndMonicaJob ryan = new RyanAndMonicaJob("Ryan", account, 50);
        RyanAndMonicaJob monica = new RyanAndMonicaJob("Monica", account, 100);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(ryan);
        executor.execute(monica);

        executor.shutdown();
    }
}

class RyanAndMonicaJob implements Runnable {
    private final String name;
    private final BankAccount account;
    private final int amountToSpend;

    RyanAndMonicaJob(String name, BankAccount account, int amountToSpend) {
        this.name = name;
        this.account = account;
        this.amountToSpend = amountToSpend;
    }

    @Override
    public void run() {
        goShopping(amountToSpend);
    }

    private void goShopping(final int amountToSpend) {
        System.out.println(name + " is about to spend");
        account.spent(name, amountToSpend);
        System.out.println(name + " finished spending");
    }
}

@Getter
class BankAccount {
    private final AtomicInteger balance = new AtomicInteger(100);

    public void spent(final String name, final int amountToSpend) {
        int initialBalance = balance.get();

        if (initialBalance >= amountToSpend) {
            boolean success = balance.compareAndSet(
                initialBalance, initialBalance - amountToSpend
            );
            if (!success) {
                System.out.println(name + " didn't spend the money");
            }
        } else {
            System.out.println(name + "! You poor, you lil bitch!");
        }
    }
}









