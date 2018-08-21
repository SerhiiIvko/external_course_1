package com.epam.ivko.builder;

import java.util.Objects;

class Souse implements Ingredient {
    private String name;
    private int quantity;

    public Souse(String name, int quantity) {
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
        return "Souse: " + name + ", quantity: " + quantity + " gr";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Souse)) return false;
        Souse souse = (Souse) o;
        return quantity == souse.quantity &&
                Objects.equals(name, souse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }
}