package com.epam.ivko.task2;

public class Singleton {
    private static Singleton Instance = new Singleton();

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (Instance == null) {
            synchronized (Singleton.class) {
                if (Instance == null) {
                    Instance = new Singleton();
                }
            }
        }
        return Instance;
    }
}