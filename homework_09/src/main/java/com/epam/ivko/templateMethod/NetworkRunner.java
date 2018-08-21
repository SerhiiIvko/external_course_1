package com.epam.ivko.templateMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class NetworkRunner {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Network network = null;
        System.out.println("Input user name");
        String userName = reader.readLine();
        System.out.println("Input password");
        String password = reader.readLine();
        System.out.println("Input message:");
        String message = reader.readLine();
        System.out.println("Choose social network:\n" +
                "1. Facebook\n" +
                "2. Twitter\n" +
                "3. Telegram");
        Scanner scanner = new Scanner(System.in);
        int choice = readNumber(scanner, "Your choice:");
        switch (choice) {
            case 1:
                network = new Facebook(userName, password);
                break;
            case 2:
                network = new Twitter(userName, password);
                break;
            case 3:
                network = new Telegram(userName, password);
                break;
            default:
                System.out.println("Choose correct number");
        }
        Objects.requireNonNull(network).post(message);
        scanner.close();
        reader.close();
    }

    private static int readNumber(Scanner scanner, String inviteText) {
        int number = -1;
        System.out.println(inviteText);
        while (number < 0 || number > 3) {
            if (!scanner.hasNextInt()) {
                System.out.println("Input only positive digits!");
                scanner.next();
            } else {
                number = scanner.nextInt();
                if (number < 0) {
                    System.out.println("Number must be correct!");
                }
            }
        }
        return number;
    }
}