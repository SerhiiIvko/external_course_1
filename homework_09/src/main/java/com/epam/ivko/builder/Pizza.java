package com.epam.ivko.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pizza {
    public static final String DEFAULT_CHEESE = "Feta";
    public static final int DEFAULT_CHEESE_QUANTITY = 100;
    public static final String DEFAULT_SOUSE = "Pesto";
    public static final int DEFAULT_SOUSE_QUANTITY = 50;

    private Cheese cheese;
    private Souse souse;
    private List<Ingredient> additionalIngredients;

    private Pizza(Cheese cheese, Souse souse, List<Ingredient> additionalIngredients) {
        this.cheese = cheese;
        this.souse = souse;
        this.additionalIngredients = additionalIngredients;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public Souse getSouse() {
        return souse;
    }

    public List<Ingredient> getAdditionalIngredients() {
        return additionalIngredients;
    }

    @Override
    public String toString() {
        return "Pizza contains: " + cheese + ", " + souse + ", Additional ingredients: "
                + additionalIngredients.stream()
                .map(i -> i.getName() + ", quantity " + i.getQuantity())
                .collect(Collectors.joining(", ")) + " gr";
    }

    static class PizzaBuilder {

        private Cheese builderCheese = new Cheese(DEFAULT_CHEESE, DEFAULT_CHEESE_QUANTITY);
        private Souse builderSouse = new Souse(DEFAULT_SOUSE, DEFAULT_SOUSE_QUANTITY);
        private List<Ingredient> builderAdditionalIngredients = new ArrayList<>();

        public PizzaBuilder addCheese(Cheese cheese) {
            this.builderCheese = cheese;
            return this;
        }

        public PizzaBuilder addSouce(Souse souse) {
            this.builderSouse = souse;
            return this;
        }

        public PizzaBuilder addIngredient(Ingredient ingredient) {
            builderAdditionalIngredients.add(ingredient);
            return this;
        }

        public Pizza build() {
            return new Pizza(builderCheese, builderSouse, builderAdditionalIngredients);
        }
    }
}