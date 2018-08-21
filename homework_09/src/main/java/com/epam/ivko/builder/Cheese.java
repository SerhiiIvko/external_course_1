package com.epam.ivko.builder;

import java.util.Objects;

class Cheese implements Ingredient {
    private String name;
    private int quantity;

    public Cheese(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Cheese: " + name + ", quantity: " + quantity + " gr";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cheese)) return false;
        Cheese cheese = (Cheese) o;
        return quantity == cheese.quantity &&
                Objects.equals(name, cheese.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }
}