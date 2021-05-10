package com.revature.quizzard.util.structures;

public interface Queue<T> extends Collection<T> {
    T poll();
    T peek();
}
