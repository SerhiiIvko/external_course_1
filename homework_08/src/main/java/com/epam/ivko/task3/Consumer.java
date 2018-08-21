package com.epam.ivko.task3;

class Consumer extends Thread {
    private MyQueue queue;

    public Consumer(MyQueue queue) {
        this.queue = queue;
        this.setName("Consumer");
    }

    @Override
    public void run() {
        while (true) {
            queue.get();
            if (isInterrupted()) {
                return;
            }
        }
    }
}