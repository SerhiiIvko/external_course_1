package com.epam.ivko.factoryMethod;

public enum PizzaType {
    CHEESE("Cheese"),
    VEGETARIAN("Vegetarian"),
    GARDEN("Garden"),
    GREEK("Greek"),
    CANADIAN("Canadian"),
    MUSHROOM("Mushroom");

    private String name;

    PizzaType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}