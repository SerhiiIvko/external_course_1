package com.epam.ivko.strategy;

public class ToLowerCaseFormatter implements TextFormatter {

    @Override
    public String formatText(String text) {
        return text.toLowerCase();
    }
}