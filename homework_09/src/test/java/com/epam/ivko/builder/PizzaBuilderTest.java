package com.epam.ivko.builder;

import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilderTest {
    private Pizza pizza;

    @Test
    public void makePizzaWithDefaultCheeseAndSouse() {
        //GIVEN:
        String expectedCheeseName = "Feta";
        String expectedSouseName = "Pesto";
        int expectedCheeseQuantity = 100;
        int expectedSouseQuantity = 50;

        //WHEN:
        pizza = new Pizza.PizzaBuilder().build();

        //THEN:
        Assert.assertNotNull(pizza);
        Assert.assertEquals(expectedCheeseName, pizza.getCheese().getName());
        Assert.assertEquals(expectedSouseName, pizza.getSouse().getName());
        Assert.assertEquals(expectedCheeseQuantity, pizza.getCheese().getQuantity());
        Assert.assertEquals(expectedSouseQuantity, pizza.getSouse().getQuantity());
    }

    @Test
    public void makePizzaWithNewCheeseAndSouse() {
        //GIVEN:
        String expectedCheeseName = "bri";
        String expectedSouseName = "chili";
        int expectedCheeseQuantity = 150;
        int expectedSouseQuantity = 80;

        //WHEN:
        pizza = new Pizza.PizzaBuilder()
                .addCheese(new Cheese(expectedCheeseName, expectedCheeseQuantity))
                .addSouce(new Souse(expectedSouseName, expectedSouseQuantity))
                .build();

        //THEN:
        Assert.assertNotNull(pizza);
        Assert.assertEquals(expectedCheeseName, pizza.getCheese().getName());
        Assert.assertEquals(expectedSouseName, pizza.getSouse().getName());
        Assert.assertEquals(expectedCheeseQuantity, pizza.getCheese().getQuantity());
        Assert.assertEquals(expectedSouseQuantity, pizza.getSouse().getQuantity());
    }

    @Test
    public void makePizzaWithNewListIngredientsAndDefaultSouse() {
        //GIVEN:
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Cheese("Cheddar", 50));
        ingredients.add(new Vegetable("tomato", 50));
        String expectedCheeseName = ingredients.get(0).getName();
        String expectedVegetableName = ingredients.get(1).getName();
        String expectedSouseName = "Pesto";
        int expectedCheeseQuantity = ingredients.get(0).getQuantity();
        int expectedVegetableQuantity = ingredients.get(1).getQuantity();
        int expectedSouseQuantity = 50;

        //WHEN:
        pizza = new Pizza.PizzaBuilder()
                .addIngredient(ingredients.get(0))
                .addIngredient(ingredients.get(1))
                .build();

        //THEN:
        Assert.assertNotNull(pizza);
        Assert.assertEquals(expectedCheeseName, pizza.getAdditionalIngredients().get(0).getName());
        Assert.assertEquals(expectedVegetableName, pizza.getAdditionalIngredients().get(1).getName());
        Assert.assertEquals(expectedSouseName, pizza.getSouse().getName());
        Assert.assertEquals(expectedCheeseQuantity, pizza.getAdditionalIngredients().get(0).getQuantity());
        Assert.assertEquals(expectedVegetableQuantity, pizza.getAdditionalIngredients().get(1).getQuantity());
        Assert.assertEquals(expectedSouseQuantity, pizza.getSouse().getQuantity());
    }

    @Test
    public void makePizzaWithNewListIngredientsAndDefaultCheese() {
        //GIVEN:
        List<Ingredient> ingredients = new ArrayList<>();
        Souse souse = new Souse("Salsa", 50);
        Vegetable pepper = new Vegetable("pepper", 50);
        Vegetable tomato = new Vegetable("tomato", 50);
        Souse expectedSouse = new Souse(Pizza.DEFAULT_SOUSE, Pizza.DEFAULT_SOUSE_QUANTITY);
        Cheese expectedCheese = new Cheese(Pizza.DEFAULT_CHEESE, Pizza.DEFAULT_CHEESE_QUANTITY);
        ingredients.add(souse);
        ingredients.add(pepper);
        ingredients.add(tomato);
        List<Ingredient> expectedIngredients = new ArrayList<>(ingredients);

        //WHEN:
        pizza = new Pizza.PizzaBuilder()
                .addIngredient(ingredients.get(0))
                .addIngredient(ingredients.get(1))
                .addIngredient(ingredients.get(2))
                .build();

        //THEN:
        Assert.assertNotNull(pizza);
        Assert.assertEquals(expectedSouse, pizza.getSouse());
        Assert.assertEquals(expectedCheese, pizza.getCheese());
        Assert.assertTrue(Iterables.elementsEqual(expectedIngredients, pizza.getAdditionalIngredients()));
    }
}