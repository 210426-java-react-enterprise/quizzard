package com.revature.quizzard.util;

public interface Queue<T> extends Collection<T> {
    T poll();
    T peek();
}
