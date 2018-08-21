package com.epam;

import com.epam.list.impl.MyArrayList;
import org.junit.Assert;
import org.junit.Test;

public class AdditionMethodsTest {
    private MyArrayList<String> test;

    public AdditionMethodsTest() {
    }

    @Test
    public void addToTheEndOfListWhenListIsEmpty() {
        //GIVEN
        String expected = "expected";
        test = new MyArrayList<>();

        //WHEN
        test.add(expected);
        String actual = test.get(0);

        //THEN
        Assert.assertNotNull(test.get(0));
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(1, test.size());
    }

    @Test
    public void addToTheEndOfListWhenListHasOneElement() {
        //GIVEN
        String expected = "expected";
        test = new MyArrayList<>();
        test.add("oneElement");
        test.add(expected);

        //WHEN
        String actual = test.get(1);

        //THEN
        Assert.assertNotNull(test.get(1));
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, test.size());
    }

    @Test
    public void addToTheEndOfListWhenListHas15Elements() {
        //GIVEN
        String expected = "expected";
        test = new MyArrayList<>();
        for (int i = 0; i < 15; i++) {
            test.add("element" + i);
        }
        test.add(expected);

        //WHEN
        String actual = test.get(15);

        //THEN
        Assert.assertNotNull(test.get(15));
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(16, test.size());
    }

    @Test
    public void addToTheEndOfListNullShouldNotThrowException() {
        //GIVEN
        String expected = null;
        test = new MyArrayList<>();
        test.add(expected);

        //WHEN
        String actual = test.get(0);

        //THEN
        Assert.assertNull(test.get(0));
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(1, test.size());
    }
}