package com.revature.quizzard.util;

import com.revature.quizzard.screens.Screen;

public class LinkedListTest {

	private LinkedList<String> sut;
	private LinkedList<? extends Screen> ex1; // generics with subtyping
	private LinkedList<?> ex2; // the ? denotes wildcard

	public void test_add_withNull() {
		// Arrange (setup the test
		sut = new LinkedList<>();

		// Act (do the test)
		try {
			sut.add(null);
			System.err.println("Test: test_add_withNull failed!");
		} catch (IllegalArgumentException e) {
			// Assert (verify outcomes)
			System.out.println("Test: test_add_withNull passed!");
		}
	}

	public void test_add_withNonNullValue() {
		// Arrange (setup the test
		sut = new LinkedList<>();

		// Act (do the test)
		sut.add("not null!");

		// Assert (verify outcomes)
		if (sut.size() == 1) {
			System.out.println("Test: test_add_withNonNullValue passed!");
		} else {
			System.err.println("Test: test_add_withNonNullValue failed!");
		}

	}
}
