package com.epam.ivko.decorator;

public class Rectangle implements Shape {
    private String name;

    Rectangle() {
        this.name = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.print("Shape: " + name);
    }
}