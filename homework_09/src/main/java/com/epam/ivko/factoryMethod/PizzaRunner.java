package com.epam.ivko.factoryMethod;

public class PizzaRunner {
    private final PizzaMaker pizzaMaker;

    public PizzaRunner(PizzaMaker pizzaMaker) {
        this.pizzaMaker = pizzaMaker;
    }

    public static void main(String[] args) {
        PizzaRunner pizzaRunner = new PizzaRunner(new ItalianPizzaMaker());
        pizzaRunner.bakePizza();
    }

    private void bakePizza() {
        Pizza pizza;
        pizza = pizzaMaker.makePizza(PizzaType.VEGETARIAN);
        System.out.println(pizza.toString());
    }
}