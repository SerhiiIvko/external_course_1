package com.epam.list.launch;

import com.epam.list.MyList;
import com.epam.list.impl.MyLinkedList;

public class MainMyLinkedList {
    public static void main(String[] args) {
        MyList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("100");
        myLinkedList.add("200");
        myLinkedList.add("300");
        myLinkedList.add("400");
        myLinkedList.add("500");
        showList(myLinkedList);
        System.out.println(((MyLinkedList<String>) myLinkedList).getFirst());
        System.out.println(((MyLinkedList<String>) myLinkedList).getLast());
        ((MyLinkedList<String>) myLinkedList).addToStart("1000");
        myLinkedList.add(1, "new element");
        myLinkedList.add(6, "last added element");
        myLinkedList.set(2,"set element");
        showList(myLinkedList);
        ((MyLinkedList<String>) myLinkedList).removeLast();
        showList(myLinkedList);
        ((MyLinkedList<String>) myLinkedList).removeFirst();
        showList(myLinkedList);
        myLinkedList.remove(0);
        myLinkedList.remove(4);
        myLinkedList.remove(2);
        showList(myLinkedList);
    }

    private static void showList(MyList myList) {
        System.out.println("Size of list = " + myList.size());
        for (int i = 0; i < myList.size(); i++) {
            System.out.print("Element " + i + " = ");
            System.out.println(myList.get(i));
        }
        System.out.println();
    }
}