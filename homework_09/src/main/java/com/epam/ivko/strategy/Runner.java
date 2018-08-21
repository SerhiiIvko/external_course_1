package com.epam.ivko.strategy;

public class Runner {
    public static void main(String[] args) {
        TextFormatter lowerFormatter = new ToLowerCaseFormatter();
        TextFormatter upperFormatter = new ToUpperCaseFormatter();
        TextEditor lowTxt = new TextEditor(lowerFormatter);
        TextEditor upTxt = new TextEditor(upperFormatter);

        String str1 = lowTxt.publishText("HeLlO WorlD");
        String str2 = upTxt.publishText("HeLlO WorlD");
        System.out.println(str1);
        System.out.println(str2);
    }
}