package com.epam.ivko.strategy;

public class ToUpperCaseFormatter implements TextFormatter {

    @Override
    public String formatText(String text) {
        return text.toUpperCase();
    }
}