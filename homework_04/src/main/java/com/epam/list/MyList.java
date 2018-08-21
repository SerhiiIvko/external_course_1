package com.epam.list;

public interface MyList<T>{
    void add(T element);
    void add(int index, T element);
    void remove(int index);
    T get(int index);
    void set(int index, T element);
    int size();
}