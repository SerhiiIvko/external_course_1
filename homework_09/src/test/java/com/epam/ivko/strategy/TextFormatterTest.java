package com.epam.ivko.strategy;

import org.junit.Assert;
import org.junit.Test;

public class TextFormatterTest {

    @Test
    public void formatTextToLowerCase() {
        //GIVEN:
        String unformattedText = "HeLlO WorlD";
        String expectedFormattedText = "hello world";
        String actualFormattedText;
        TextFormatter formatter = new ToLowerCaseFormatter();
        TextEditor editor = new TextEditor(formatter);

        //WHEN:
        actualFormattedText = editor.publishText(unformattedText);

        //THEN:
        Assert.assertEquals(expectedFormattedText, actualFormattedText);
    }

    @Test
    public void formatTextToUpperCase() {
        //GIVEN:
        String unformattedText = "HeLlO WorlD";
        String expectedFormattedText = "HELLO WORLD";
        String actualFormattedText;
        TextFormatter formatter = new ToUpperCaseFormatter();
        TextEditor editor = new TextEditor(formatter);

        //WHEN:
        actualFormattedText = editor.publishText(unformattedText);

        //THEN:
        Assert.assertEquals(expectedFormattedText, actualFormattedText);
    }
}