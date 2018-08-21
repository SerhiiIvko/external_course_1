package com.epam.ivko.decorator;

public class Runner {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape redBordersCircle = new ColoredBordersShapeDecorator(new Circle(), "red");
        circle.draw();
        System.out.println();
        redBordersCircle.draw();
        System.out.println();
        Shape rectangle = new Rectangle();
        Shape greenBorderRectangle = new ColoredBordersShapeDecorator(new Rectangle(), "green");
        rectangle.draw();
        System.out.println();
        greenBorderRectangle.draw();
    }
}