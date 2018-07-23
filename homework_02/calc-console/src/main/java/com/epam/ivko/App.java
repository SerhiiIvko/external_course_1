package com.epam.ivko;

import java.util.stream.Stream;

public class App {
    private double number1;
    private double number2;
    private char operator;

    public static void main(String[] args) {
        App app = new App();
        try {
            app.validate(args);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect data for executing operation!");
        }
        double result = app.calculate(args);
        app.printResult(result);
    }

    private boolean validate(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException();
        }
        if (!(isNumber(args[0]) && isNumber(args[1]) && isValidOperator(args[2]))) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    private boolean isNumber(String value) {
        return value.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isValidOperator(String value) {
        return Stream.of("+", "-", "*", "/").anyMatch(op -> op.equals(value));
    }

    private double calculate(String[] args) {
        number1 = Double.parseDouble(args[0]);
        number2 = Double.parseDouble(args[1]);
        operator = args[2].charAt(0);
        Calc calc = new CalcImpl();
        double result = 0;
        switch (operator) {
            case '+':
                result = calc.addition(number1, number2);
                break;
            case '-':
                result = calc.subtraction(number1, number2);
                break;
            case '*':
                result = calc.multiplication(number1, number2);
                break;
            case '/':
                try {
                    result = calc.division(number1, number2);
                } catch (IllegalArgumentException e) {
                    System.out.println("Division by zero!");
                }
                break;
        }
        return result;
    }

    private void printResult(double result) {
        System.out.println(String.format("number1=%s number2=%s operator=%s result=%s",
                number1, number2, operator, result));
    }
}