package com.epam.ivko.builder;

import java.util.Objects;

public class Vegetable implements Ingredient {
    private String name;
    private int quantity;

    public Vegetable(String name, int quantity) {
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
        return "Vegetable{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vegetable)) return false;
        Vegetable vegetable = (Vegetable) o;
        return quantity == vegetable.quantity &&
                Objects.equals(name, vegetable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }
}