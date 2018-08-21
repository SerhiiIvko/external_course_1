package com.epam.ivko.decorator;

import org.junit.Assert;
import org.junit.Test;

public class ShapeTest {

    @Test
    public void drawNewCircle() {
        //GIVEN:
        Shape circle = new Circle();

        //WHEN:
        circle.draw();

        //THEN:
        Assert.assertNotNull(circle);
    }

    @Test
    public void drawNewRectangle() {
        //GIVEN:
        Shape rectangle = new Rectangle();

        //WHEN:
        rectangle.draw();

        //THEN:
        Assert.assertNotNull(rectangle);
    }

    @Test
    public void drawNewColoredBorderCircle() {
        //GIVEN:
        String expectedColor = "yellow";
        ColoredBordersShapeDecorator coloredCircle = new ColoredBordersShapeDecorator(new Circle(), expectedColor);

        //WHEN:
        coloredCircle.draw();

        //THEN:
        Assert.assertNotNull(coloredCircle);
        Assert.assertEquals(expectedColor, coloredCircle.getColor());
    }

    @Test
    public void drawNewColoredBorderRectangle() {
        //GIVEN:
        String expectedColor = "black";
        ColoredBordersShapeDecorator coloredRectangle = new ColoredBordersShapeDecorator(new Rectangle(), expectedColor);

        //WHEN:
        coloredRectangle.draw();

        //THEN:
        Assert.assertNotNull(coloredRectangle);
        Assert.assertEquals(expectedColor, coloredRectangle.getColor());
    }
}