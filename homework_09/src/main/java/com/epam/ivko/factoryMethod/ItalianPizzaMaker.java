package com.epam.ivko.factoryMethod;

public class ItalianPizzaMaker implements PizzaMaker {
    @Override
    public Pizza makePizza(PizzaType pizzaType) {
        return new ItalianPizza(pizzaType);
    }
}