package com.epam.ivko.templateMethod;

import java.util.stream.IntStream;

public class Telegram extends Network {
    Telegram(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    @Override
    boolean logIn(String username, String password) {
        System.out.println("\nChecking user's parameters");
        if (isDataValid(username, password)) {
            return false;
        }
        System.out.println("Name: " + this.username);
        System.out.println("Password: ");
        IntStream.range(0, this.password.length()).mapToObj(i -> "*").forEachOrdered(System.out::print);
        simulateNetworkLatency();
        System.out.println("\nLogin success on Telegram");
        return true;
    }

    @Override
    boolean sendData(byte[] data) {
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Telegram");
            return true;
        } else {
            return false;
        }
    }

    @Override
    String logOut() {
        return "User: '" + username + "' was logged out from Telegram";
    }

    private void simulateNetworkLatency() {
        try {
            int i = 0;
            System.out.println();
            while (i < 10) {
                System.out.print(".");
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}