package com.revature.quizzard.utilities;


import java.util.Arrays;
import java.util.Iterator;

public class MyList<T> implements List<T>, Iterable<T> {

    private T[] array;
    private int numberOfElements;

    public MyList() {
        this.array = (T[]) new Object[5];
        numberOfElements = 0;
    }

    private void grow()
    {
        T[] newArray = (T[]) new Object[2 * array.length];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T t : array) {
            if(o.equals(t)) return true;
        }
        return false;
    }

    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<T>(array);
    }

    @Override
    public T[] toArray() {
        T[] tempArray = (T[]) new Object[numberOfElements];
        for (int i = 0; i < numberOfElements; i++) {
            tempArray[i] = array[i];
        }
        return tempArray;
    }

    @Override
    public boolean add(T element)
    {
        if(array.length == numberOfElements) grow();
        array[numberOfElements] = element;
        numberOfElements++;
        return true;
    }

    @Override
    public void clear() {
        for (T t : array) {
            t = null;
        }
    }

    @Override
    public T get(int index) {
        if(index < numberOfElements)
        return array[index];
        return null;
    }

    @Override
    public T set(int index, T element) {
        T tempT;
        if (index < numberOfElements) {
            tempT = array[index];
            array[index] = element;
            return tempT;
        }
        return null;
    }

    @Override
    public void add(int index, T element) {
        if (index < numberOfElements)
        {
            if(array.length == numberOfElements) grow();

            for (int i = numberOfElements; i >= index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = element;
            numberOfElements++;
        }
    }

    //fix the remove function
    @Override
    public boolean remove(T element) {
        for (int i = 0; i < numberOfElements; i++) {
            if(element.equals(array[i]))
            {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index < numberOfElements)
        {
            for (int i = index; i < numberOfElements - 1; i++) {
                array[i] = array[i + 1];
            }
            array[numberOfElements - 1] = null;
            numberOfElements--;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < numberOfElements; i++) {
            if(array[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public T at(int index)
    {
        if(index >= 0 && index < numberOfElements)
        return array[index];
        return  null;
    }

    @Override
    public String toString() {
        return "MyList{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
