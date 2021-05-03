package com.revature.quizzard.util;


// T is short for 'Type'. This is just general convention
public interface List<T> {
	//interfaces do not have constructors
	// all fields (variables) declared within interfaces are implicitly public, static and final
	// all method stubs declared within interfaces are implicitly public and abstract

	void add(T data);
	T pop();
	T get(int index);
	boolean contains (T data);
	int size();

	// static methods cannot be overriden (but you can shadow it)
	static void staticMethod() {
		System.out.println("This is a static method declared within an interface");
	}

	// since Java 8 you can declare default methods which provide a default implementation,
	// but can be overridden by lower level classes

	default void defaultMethod() {
		System.out.println("This is a default method declared within an interface");
	}

}
