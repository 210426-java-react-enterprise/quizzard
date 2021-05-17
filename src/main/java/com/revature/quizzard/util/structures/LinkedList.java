package com.revature.quizzard.util.structures;

import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * A simple implementation of a doubly linked-list structure that
 * does not accept null data.
 *
 * @param <T>
 */
public class LinkedList<T> implements List<T>, Iterable<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    public boolean add(T data) throws IllegalArgumentException {

        if (data == null) {
            throw new IllegalArgumentException("This linked list does not accept null values");
        }

        Node<T> newNode = new Node<>(data);
        if (head == null) {
            tail = head = newNode;
        } else {
            tail.nextNode = newNode;
            newNode.prevNode = tail;
            tail = newNode;
        }

        size++;

        return true;

    }

    public boolean contains(T data) {
        Node<T> runner = head;
        for (int i = 0; i < size; i++) {
            if (runner.data == data) {
                return true;
            }
            runner = runner.nextNode;
        }

        return false;
    }

    @Override
    public T get(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The provided index is out of bounds.");
        }

        Node<T> runner = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return runner.data;
            }
            runner = runner.nextNode;
        }

        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T poll() {

        if (head == null) {
            return null;
        }

        T soughtData = head.data;
        head = head.nextNode;

        if (head != null) {
            head.prevNode = null;
        } else {
            tail = null;
        }

        size--;

        return soughtData;

    }

    public T peek() {
        return head.data;
    }

    public boolean remove(T data) {
        Node<T> runner = head;
        for (int i = 0; i < size; i++) {
            if (runner.data == data) {
                runner.prevNode = runner.nextNode;
                runner.nextNode.prevNode = runner.prevNode;
                return true;
            }
            runner = runner.nextNode;
        }

        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {

                T data = null;

                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }

                while(current != null) {
                    data = current.data;
                    current = current.nextNode;
                }

                return data;
            }
        };
    }

    public void stream() {
    }

    private static class Node<T> {
        T data;
        Node<T> nextNode;
        Node<T> prevNode;

        Node(T data) {
            this.data = data;
        }
    }


}
