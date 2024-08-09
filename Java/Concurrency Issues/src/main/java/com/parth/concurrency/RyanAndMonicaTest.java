package com.parth.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        if (account.getBalance() > amountToSpend) {
            System.out.println(name + " is about to spend");
            account.spent(amountToSpend);
            System.out.println(name + " finished spending");
        } else {
            System.out.println("You're poor you lil bitch!");
        }
    }
}

class BankAccount {
    private int balance = 100;

    public int getBalance() {
        return balance;
    }

    public void spent(final int amountToSpend) {
        if (amountToSpend > balance) {
            balance = balance - amountToSpend;
        } else {
            System.out.println("Overdrawn!");
        }
    }
}









