package com.epam;

import com.epam.list.impl.MyArrayList;
import org.junit.Assert;
import org.junit.Test;

public class ListTest {
    private MyArrayList<String> test;

    public ListTest() {
    }

    @Test
    public void removeByCorrectIndexValue() {
        //GIVEN
        int index = 0;
        String element = "element";
        test = new MyArrayList<>();
        test.add(element);

        //WHEN
        test.remove(index);

        //THEN
        Assert.assertEquals(0, test.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIncorrectIndexValue() {
        //GIVEN
        int indexForAdd = 0;
        int indexForRemove = 24;
        int initialCapacity = 15;
        String element = "element";
        test = new MyArrayList<>(initialCapacity);
        for (int i = indexForAdd; i < initialCapacity; i++) {
            test.add(element + i);
        }

        //WHEN
        test.remove(indexForRemove);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeFromEmptyArray() {
        //GIVEN
        int indexForRemove = 0;
        test = new MyArrayList<>();

        //WHEN
        test.remove(indexForRemove);
    }

    @Test
    public void setElementByCorrectIndex() {
        //GIVEN
        String element = "element";
        test = new MyArrayList<>();
        test.add(element);

        //WHEN
        test.set(0, "set element");

        //THEN
        Assert.assertEquals("set element", "set element");
        Assert.assertNotNull(test.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setElementByIncorrectIndex() {
        //GIVEN
        int initialCapacity = 12;
        int index = -1;
        String element = "element";
        test = new MyArrayList<>(initialCapacity);
        test.add(element);

        //WHEN
        test.set(index, "set element");
    }
}