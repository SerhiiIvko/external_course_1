package com.epam.ivko.task3;

public class CustomQueue {

    public static void main(String[] args) throws InterruptedException {
        MyQueue queue = new MyQueue();
        Consumer consumer = new Consumer(queue);
        consumer.start();
        Producer producer = new Producer(queue);
        producer.getThread().join();
        consumer.interrupt();
    }
}