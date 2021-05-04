package com.revature.quizzard.util;

import org.junit.*;

public class JUnitExample {

    /* JUnit annotations:
    @Test - used to denote a method as a JUnit test
    @Ignore - used to indicate JUnit should skip this test
    @Before - Denotes a set-up method that runs before each test case.
    @After - Denotes a set-up method that runs after each test case.
    @BeforeClass - Denotes a set-up method that will run only once before all test case methods run. Must be static.
    @AfterClass - Denotes a set-up method that will run only once after all test case methods run. Must be static.
    */

    //arrange
    //act
    //assert



    @BeforeClass
    public static void runBeforeTestSuite() {
        System.out.println("runBeforeTestSuite()");
    }

    @AfterClass
    public static void runAfterTestSuite() {
        System.out.println("runAfterTestSuite()");
    }

    @Before
    public void runBeforeEachTest() {
        System.out.println("runBeforeTest()");
    }

    @After
    public void runAfterEachTest() {
        System.out.println("runAfterTest()");
    }

    @Test
    public void test1() {
        System.out.println("Test1");
    }

    @Test(expected = Exception.class)
    public void test2() throws Exception{
        System.out.println("Test2");
        throw new Exception();
    }
}
