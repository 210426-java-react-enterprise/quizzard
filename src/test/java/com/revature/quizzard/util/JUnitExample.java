package com.revature.quizzard.util;

import org.junit.*;

/*
    This class is a "test suite" because it contains
    test methods which JUnit will execute to unit test
    our logic
 */
public class JUnitExample {
	/*
		JUnit Annotations:
			- @Test
				+ used to denote a method as a JUnit test
			- @Ignore("reason for being ignored")
				+ used to indicate that JUnit should skip this test
			- @BeforeClass
				+ denotes a method as a "set up" method that will run only
				  once, before all test case methods run.
				+ must be declared as static
			- @AfterClass
				+ denotes a method as a "tear down" method that will run only
				  once, after all test case methods run.
				+ must be declared as static
			- @Before
				+ denotes a method as a "set up" method, but will run before each
				  test case runs.
			- @After
				+ denotes a method as a "tear down" method, but will run after each
				  test case runs.
	 */
	@BeforeClass
	public static void runBeforeTestSuite() {
		System.out.println("called runBeforeTestSuite()");
	}

	@AfterClass
	public static void runAfterTestSuite() {
		System.out.println("called runAfterTestSuite()");
	}

	@Before
	public void runBeforeEachTestCase() {
		System.out.println("called runBeforeEachTestCase()");
	}

	@After
	public void runAfterEachTestCase() {
		System.out.println("called runAfterEachTestCase()");
	}

	@Test
	public void test1() {
		System.out.println("called test1()");
	}

	@Test
	public void test2() {
		System.out.println("called test2()");
	}

	@Test
	@Ignore("You can give a reason why this test is ignored")
	public void test3() {
		System.out.println("should not be called, because it is ignored");
	}
}