package com.epam.ivko.factoryMethod;

import org.junit.Assert;
import org.junit.Test;

public class FactoryMethodTest {

    @Test
    public void bakeVegetarianPizzaType() {
        //GIVEN:
        PizzaMaker pizzaMaker = new ItalianPizzaMaker();

        //WHEN:
        Pizza pizza = pizzaMaker.makePizza(PizzaType.VEGETARIAN);

        //THEN:
        verifyPizzaType(pizza, PizzaType.VEGETARIAN);
    }

    @Test
    public void bakeCanadianPizzaType() {
        //GIVEN:
        PizzaMaker pizzaMaker = new ItalianPizzaMaker();

        //WHEN:
        Pizza pizza = pizzaMaker.makePizza(PizzaType.CANADIAN);

        //THEN:
        verifyPizzaType(pizza, PizzaType.CANADIAN);
    }

    @Test
    public void bakeCheesePizzaType() {
        //GIVEN:
        PizzaMaker pizzaMaker = new ItalianPizzaMaker();

        //WHEN:
        Pizza pizza = pizzaMaker.makePizza(PizzaType.CHEESE);

        //THEN:
        verifyPizzaType(pizza, PizzaType.CHEESE);
    }

    @Test
    public void bakeGreekPizzaType() {
        //GIVEN:
        PizzaMaker pizzaMaker = new ItalianPizzaMaker();

        //WHEN:
        Pizza pizza = pizzaMaker.makePizza(PizzaType.GREEK);

        //THEN:
        verifyPizzaType(pizza, PizzaType.GREEK);
    }

    @Test
    public void bakeGardenPizzaType() {
        //GIVEN:
        PizzaMaker pizzaMaker = new ItalianPizzaMaker();

        //WHEN:
        Pizza pizza = pizzaMaker.makePizza(PizzaType.GARDEN);

        //THEN:
        verifyPizzaType(pizza, PizzaType.GARDEN);
    }

    @Test
    public void bakeMushroomPizzaType() {
        //GIVEN:
        PizzaMaker pizzaMaker = new ItalianPizzaMaker();

        //WHEN:
        Pizza pizza = pizzaMaker.makePizza(PizzaType.MUSHROOM);

        //THEN:
        verifyPizzaType(pizza, PizzaType.MUSHROOM);
    }

    private void verifyPizzaType(Pizza pizza, PizzaType expectedPizzaType) {
        Assert.assertTrue(ItalianPizza.class.getName(), pizza instanceof ItalianPizza);
        Assert.assertEquals(expectedPizzaType, pizza.getPizzaType());
    }
}