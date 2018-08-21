package com.epam.ivko.task1;

class SynchronizedThread implements Runnable {
    private final Object object1;
    private final Object object2;

    SynchronizedThread(String string1, String string2) {
        this.object1 = string1;
        this.object2 = string2;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " acquiring lock on " + object1);
        synchronized (object1) {
            System.out.println(currentThreadName + " acquired lock on " + object1);
            work();
            System.out.println(currentThreadName + " acquiring lock on " + object2);
            synchronized (object2) {
                System.out.println(currentThreadName + " acquired lock on " + object2);
                work();
            }
        }
    }

    private void work() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}