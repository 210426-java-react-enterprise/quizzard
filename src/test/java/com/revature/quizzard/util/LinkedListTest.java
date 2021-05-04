package com.revature.quizzard.util;

import org.junit.*;

public class LinkedListTest {

	private LinkedList<String> sut;

	@Before
	public void setUpTest() {
		sut = new LinkedList<>();
	}

	@After
	public void tearDownTest() {
		sut = null;
	}

	@Test
	public void test_addWithNonNullValue() {
		// Arrange (prepare the test)
		int expectedSize = 1;

		//Act (perform the test)
		sut.add("data");

		//Assert (ensure results)
		int actualSize = sut.size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_addWithNullValue() {
		// Arrange
		// sometimes blank if there's nothing in particular to do

		// Act
		sut.add(null);

		// Assert
		// sometimes blank, especially if you expect an exception to be thrown
	}

	@Test
	public void test_popWithEmptyList() {
		// Arrange
		// Nothing to do here...

		// Act
		String actualResult = sut.pop();

		// Assert
		Assert.assertNull(actualResult);
	}

	@Test
	public void test_popWithPopulatedList() {
		// Arrange
		sut.add("test data 1");
		sut.add("test data 2");
		String expectedResult = "test data 1";
		int expectedSize = 1;

		// Act
		String actualResult = sut.pop();
		int actualSize = sut.size();

		// Assert
		Assert.assertEquals(expectedResult, actualResult);
		Assert.assertEquals(expectedSize, actualSize);
	}
}
