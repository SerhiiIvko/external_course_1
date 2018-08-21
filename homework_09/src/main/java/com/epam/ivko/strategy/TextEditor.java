package com.epam.ivko.strategy;

public class TextEditor {
    private final TextFormatter formatter;

    public TextEditor(TextFormatter formatter) {
        this.formatter = formatter;
    }

    public String publishText(String text) {
        return formatter.formatText(text);
    }
}