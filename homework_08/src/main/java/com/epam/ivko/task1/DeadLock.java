package com.epam.ivko.task1;

import static java.lang.Thread.sleep;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new SynchronizedThread("obj1", "obj2"), "thread1");
        Thread thread2 = new Thread(new SynchronizedThread("obj2", "obj3"), "thread2");
        Thread thread3 = new Thread(new SynchronizedThread("obj3", "obj1"), "thread3");
        thread1.start();
        sleep(3000);
        thread2.start();
        sleep(3000);
        thread3.start();
    }
}