package com.revature.quizzard.util;

public interface List<T> {

    void add(T t);
    T get(int index);
    T pop();
    int contains(T t);
    int size();
}
