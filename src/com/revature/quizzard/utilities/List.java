package com.revature.quizzard.utilities;


public interface List<T> {

    int size();
    boolean isEmpty();
    boolean contains(T element);
    MyIterator<T> iterator();
    T[] toArray();
    boolean add(T element);
    void add(int index, T element);
    void clear();
    T get(int index);
    T set(int index, T element);
    boolean remove(T element);
    T remove(int index);
    int indexOf(T element);
    T at(int index);

}
