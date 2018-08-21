package com.epam.ivko.task2;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SingletonTest extends TestCase {
    private Singleton firstInstance;
    private Singleton secondInstance;

    //GIVEN:
    public SingletonTest() {
        firstInstance = null;
        secondInstance = null;
    }

    //WHEN:
    public void setUp() {
        System.out.println("getting singleton...");
        firstInstance = Singleton.getInstance();
        System.out.println("...got singleton: " + firstInstance.getClass().getSimpleName());
        System.out.println("getting singleton...");
        secondInstance = Singleton.getInstance();
        System.out.println("...got singleton: " + secondInstance.getClass().getSimpleName());
    }

    //THEN:
    public void testUnique() {
        System.out.println("checking singletons for equality");
        Assert.assertTrue(firstInstance == secondInstance);
    }
}