package com.revature.quizzard.util;

public interface List<T> {
    void add(T data);
    T pop();
    boolean contains(T data);
    int size();
}
