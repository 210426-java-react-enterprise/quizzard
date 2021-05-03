package com.revature.quizzard.util;

public class LinkedList<T> implements List<T>{

    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public void add(T t) throws IllegalArgumentException {
        if (t == null) {
            throw new IllegalArgumentException("linked list cannot accept null values");
        }

        Node<T> newNode = new Node<T>(t);

        if (head == null) {
            tail = head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {

        return null;
    }

    @Override
    public T pop() {

        if (head == null) {
            return null;
        }

        T data = head.data;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        size--;
        return data;
    }

    /**
     * Returns the index of the first instance of t, if none is found returns -1
     * @param t data of type T
     * @return int
     */
    @Override
    public int contains(T t) {
        Node<T> cursor = head;
        int index = 0;
        while(cursor != null) {
            if (cursor.data.equals(t)) {
                return index;
            }
            else {
                index++;
                cursor = cursor.next;
            }
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T t) {
            this.data = t;
        }

        Node(T t, Node<T> next, Node<T> prev) {
            this(t);
            this.next = next;
            this.prev = prev;
        }
    }
}
