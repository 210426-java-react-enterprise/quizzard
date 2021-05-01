package com.revature.quizzard.utilities;

import java.util.Iterator;
import java.util.function.Consumer;

public class MyIterator<T> implements Iterator<T> {

    private final T[] array;
    private int pointer;

    MyIterator(T[] array)
    {
        this.array = array;
        this.pointer = 0;
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {

    }

    @Override
    public boolean hasNext() {
        return array.length >= pointer + 1;
    }

    @Override
    public T next() {
        if(hasNext())
        {
            pointer++;
            return array[pointer - 1];
        }
        return null;
    }

    @Override
    public void remove() {

    }
}
