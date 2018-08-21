package com.epam.ivko.task2;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SingletonMultithreadTest extends TestCase {
    private static Singleton singleton = null;

    public SingletonMultithreadTest(String name) {
        super(name);
    }

    public void setUp() {
        singleton = null;
    }

    public void testUnique() throws InterruptedException {
        Thread threadOne = new Thread(new SingletonTestRunnable());
        Thread threadTwo = new Thread(new SingletonTestRunnable());
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
    }

    private static class SingletonTestRunnable implements Runnable {
        public void run() {
            Singleton newInstance = Singleton.getInstance();
            synchronized (SingletonMultithreadTest.class) {
                if (singleton == null) {
                    singleton = newInstance;
                }
            }
            Assert.assertSame(newInstance, singleton);
        }
    }
}