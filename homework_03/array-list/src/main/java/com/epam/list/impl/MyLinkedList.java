package com.epam.list.impl;

import com.epam.list.MyList;

public class MyLinkedList<E> implements MyList<E> {
    private MyNode<E> rootNode;
    private MyNode<E> tailNode;
    private int size = 0;

    private static class MyNode<E> {
        E value;
        MyNode<E> next;
        MyNode<E> previous;

        MyNode(MyNode<E> next, E value, MyNode<E> previous) {
            this.next = next;
            this.value = value;
            this.previous = previous;
        }
    }

    public MyLinkedList() {
        rootNode = null;
        tailNode = null;
    }

    public void addToStart(E element) {
        if (rootNode == null) {
            rootNode = new MyNode<E>(null, null, null);
            tailNode = rootNode;
        } else {
            MyNode<E> node = new MyNode<E>(rootNode, element, null);
            this.rootNode.previous = node;
            this.rootNode = node;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (isValidIndex(index, false)) {
            int indexCounter = 0;
            if (index == 0) {
                addToStart(element);
                return;
            }
            if (index == size) {
                add(element);
                return;
            }
            MyNode<E> newNode = new MyNode<E>(null, element, null);
            MyNode<E> currentNode = rootNode;
            while (indexCounter++ < index && currentNode != null && currentNode.next != null) {
                currentNode = currentNode.next;
            }
            if (currentNode != null) {
                newNode.next = currentNode;
                newNode.previous = currentNode.previous;
                currentNode.previous.next = newNode;
                currentNode.previous = newNode;
                size++;
            }
        }
    }

    @Override
    public void add(E element) {
        if (rootNode == null) {
            rootNode = new MyNode<E>(null, element, null);
            tailNode = rootNode;
        } else {
            MyNode<E> myNode = new MyNode<E>(null, element, null);
            tailNode.next = myNode;
            myNode.previous = tailNode;
            tailNode = myNode;
        }
        size++;
    }

    public MyNode<E> removeFirst() {
        MyNode<E> nodeToRemove = null;
        if (size == 1) {
            rootNode = null;
            tailNode = null;
            size = 0;
        } else if (size > 1) {
            nodeToRemove = rootNode;
            rootNode = rootNode.next;
            rootNode.previous = null;
            size--;
        }
        return nodeToRemove;
    }

    public MyNode<E> removeLast() {
        MyNode<E> nodeToRemove = null;
        if (size == 1) {
            rootNode = null;
            tailNode = null;
            size = 0;
        } else if (size > 1) {
            nodeToRemove = tailNode;
            tailNode = tailNode.previous;
            tailNode.next = null;
            size--;
        }
        return nodeToRemove;
    }

    @Override
    public void remove(int index) {
        if (isValidIndex(index, false)) {
            if (index == 0) {
                removeFirst();
                return;
            }
            if (index == size - 1) {
                removeLast();
                return;
            }
            MyNode<E> nodeToRemove = rootNode;
            int indexCounter = 0;
            while (indexCounter++ < index && nodeToRemove != null && nodeToRemove.next != null) {
                nodeToRemove = nodeToRemove.next;
            }
            if (nodeToRemove != null) {
                nodeToRemove.next = nodeToRemove.previous;
                nodeToRemove.previous = nodeToRemove.next;
                size--;
            }
        }
    }

    @Override
    public E get(int index) {
        E result = null;
        if (isValidIndex(index, false)) {
            MyNode<E> currentNode = rootNode;
            int indexCounter = 0;
            while (indexCounter++ < index && currentNode != null && currentNode.next != null) {
                currentNode = currentNode.next;
            }
            if (currentNode != null) {
                result = currentNode.value;
            }
        }
        return result;
    }

    public E getFirst() {
        return rootNode != null ? rootNode.value : null;
    }

    public E getLast() {
        return tailNode != null ? tailNode.value : null;
    }

    @Override
    public void set(int index, E element) {
        if (isValidIndex(index, false)) {
            if (rootNode == null && index == 0) {
                addToStart(element);
            }
            MyNode<E> currentNode = rootNode;
            int indexCounter = 0;
            while (indexCounter++ < index && currentNode != null && currentNode.next != null) {
                currentNode = currentNode.next;
            }
            if (currentNode != null) {
                currentNode.value = element;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    private boolean isValidIndex(int index, boolean includeSize) {
        int limit = includeSize ? size : size - 1;
        if (index < 0 || index > limit) {
            throw new IllegalArgumentException("Incorrect index value " + index);
        }
        return true;
    }
}