package com.epam.ivko.factoryMethod;

public class ItalianPizza implements Pizza {
    private PizzaType pizzaType;

    public ItalianPizza(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    @Override
    public String toString() {
        return "Your ordered pizza: " + pizzaType;
    }

    @Override
    public PizzaType getPizzaType() {
        return pizzaType;
    }
}