package com.epam.ivko.decorator;

public class Circle implements Shape {
    private String name;

    Circle() {
        this.name = "Circle";
    }

    @Override
    public void draw() {
        System.out.print("Shape: " + name);
    }
}