package com.epam.ivko.decorator;

public class ColoredBordersShapeDecorator extends ShapeDecorator {
    private String color;

    public ColoredBordersShapeDecorator(Shape decoratedShape, String color) {
        super(decoratedShape);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape) {
        System.out.print(", Border color: " + color + "\n");
    }
}