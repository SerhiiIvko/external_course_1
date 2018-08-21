package com.epam.ivko.task3;

class Producer implements Runnable {
    private MyQueue queue;
    private Thread thread;

    public Producer(MyQueue queue) {
        this.queue = queue;
        thread = new Thread(this, "Producer");
        thread.start();
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.put(i);
        }
    }
}