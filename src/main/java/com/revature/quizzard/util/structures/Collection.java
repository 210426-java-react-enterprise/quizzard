package com.revature.quizzard.util.structures;

public interface Collection<T> extends Iterable<T> {

    boolean add(T data);
    boolean contains(T data);
    boolean isEmpty();
    boolean remove(T data);
    int size();

}
