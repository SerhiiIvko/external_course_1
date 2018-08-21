package com.epam.list.launch;

import com.epam.list.MyList;
import com.epam.list.impl.MyArrayList;

public class MainMyArrayList {
    public static void main(String[] args) {
        MyList<String> arrayList = new MyArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("13");
        arrayList.add("14");
        arrayList.add("15");
        showList(arrayList);
        arrayList.remove(14);
        showList(arrayList);
        System.out.println(arrayList.get(8));
        arrayList.set(8, "element 10 changed to 1000");
        System.out.println(arrayList.get(8));
        try {
            MyList<String> myArrayList1 = new MyArrayList<>(25);
            myArrayList1.add(0, "one");
            myArrayList1.add(1, "two");
            myArrayList1.add(2, "two");
            myArrayList1.add(3, "two");
            showList(myArrayList1);
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect capacity value!");
        }
    }

    private static void showList(MyList myList) {
        System.out.println("Size of array = " + myList.size());
        for (int i = 0; i < myList.size(); i++) {
            System.out.print("Element " + i + " = ");
            System.out.println(myList.get(i));
        }
        System.out.println();
    }
}