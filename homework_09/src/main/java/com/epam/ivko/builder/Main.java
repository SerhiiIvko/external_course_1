package com.epam.ivko.builder;

class Main {
    public static void main(String[] args) {
        Cheese cheese = new Cheese("Mozzarella", 100);
        Souse souse = new Souse("Pesto", 110);
        Ingredient ingredient = new Ingredient() {
            @Override
            public String getName() {
                return "mushrooms";
            }

            @Override
            public int getQuantity() {
                return 150;
            }

            @Override
            public String toString() {
                return "Ingredient: " +
                        getName() + '\'' +
                        ", quantity: " + getQuantity();
            }
        };
        Pizza pizza = new Pizza.PizzaBuilder()
                .addCheese(cheese)
                .addSouce(souse)
                .addIngredient(ingredient)
                .build();
        System.out.println(pizza);
    }
}