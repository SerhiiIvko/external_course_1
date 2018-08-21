package com.epam.ivko.task3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class MyQueue {
    private ReentrantLock lock;
    private Condition condition;
    private int value;
    private boolean valueSet = false;

    public MyQueue() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public int get() {
        lock.lock();
        while (!valueSet) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                System.out.println("Get interruption was handled");
                Thread.currentThread().interrupt();
                return 0;
            }
        }
        System.out.println("Received: " + value);
        valueSet = false;
        condition.signal();
        lock.unlock();
        return value;
    }

    public void put(int value) {
        lock.lock();
        while (valueSet) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                System.out.println("Put interruption was handled");
            }
        }
        this.value = value;
        valueSet = true;
        System.out.println("Sent: " + value);
        condition.signal();
        lock.unlock();
    }
}