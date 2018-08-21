package com.epam.list.impl;

import com.epam.list.MyList;

public class MyArrayList<E> implements MyList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] array;
    private int size;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initCapacity) {
        if (initCapacity > 0 && initCapacity < Integer.MAX_VALUE) {
            this.array = (E[]) new Object[initCapacity];
            size = 0;
        } else {
            throw new IllegalArgumentException("Incorrect initial capacity");
        }
    }

    @Override
    public void add(E element) {
        if (size + 1 >= array.length) {
            increaseCapacity();
        }
        array[size++] = element;
    }

    @Override
    public void add(int index, E element) {
        try {
            checkIndex(index, true);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect index value " + index);
        }
        array[index] = element;
        size++;
    }

    @Override
    public void remove(int index) {
        try {
            checkIndex(index, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect index value " + index);
        }
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
    }

    @Override
    public E get(int index) {
        try {
            checkIndex(index, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect index value " + index);
        }
        return array[index];
    }

    @Override
    public void set(int index, E element) {
        try {
            checkIndex(index, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect index value " + index);
        }
        array[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    private void increaseCapacity() {
        if (size >= array.length / 1.5) {
            E[] tmpArray = (E[]) new Object[size() * 2];
            System.arraycopy(array, 0, tmpArray, 0, array.length);
            array = tmpArray;
        }
    }

    private void checkIndex(int index, boolean includeSize) {
        int limit = includeSize ? size : size - 1;
        if (index < 0 || index > limit) {
            throw new IllegalArgumentException("Incorrect index value " + index);
        }
    }
}