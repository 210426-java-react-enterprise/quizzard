package com.revature.quizzard.util;

public interface List<T> {
    //all fields declared within interfaces are implicitly
    //public, static, and final

    //all method stubs declared within interfaces are implicity
    //public and abstract
    void add(T data);
    T get(int index);
    T pop();
    boolean contains();
    int size();
    void printLinkedList();
}
