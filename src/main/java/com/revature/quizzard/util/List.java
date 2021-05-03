package com.revature.quizzard.util;

public interface List<T> {

    void add(T data);
    T pop();
    T get(int index);
    boolean contains(T data);
    int size();

}
