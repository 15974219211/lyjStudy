package com.my.study.transefer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserAccount {
    private final String name;//账户名称
    private int money;//账户余额

    private final Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public UserAccount(String name, int amount) {
        this.name = name;
        this.money = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return money;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public void addMoney(int amount){
        money = money + amount;
    }

    public void flyMoney(int amount){
        money = money - amount;
    }
}
