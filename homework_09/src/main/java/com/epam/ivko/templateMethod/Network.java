package com.epam.ivko.templateMethod;

abstract class Network {
    String username;
    String password;

    Network() {
    }

    boolean post(String message) {
        if (logIn(this.username, this.password)) {
            if (message != null && !message.isEmpty()) {
                boolean result = sendData(message.getBytes());
                logOut();
                return result;
            } else {
                System.out.println("Message can not be empty!");
            }
        }
        return false;
    }

    boolean isDataValid(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Data can not be null or empty!");
            return true;
        }
        return false;
    }

    abstract boolean logIn(String username, String password);

    abstract boolean sendData(byte[] data);

    abstract String logOut();
}