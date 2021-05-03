package com.revature.quizzard.util;

import org.junit.*;

public class JUnitExample {
    /*
        This class is a "test suite" because it contains
        test methods which JUnit will execute to unit test
        our logic
     */
    /*
        JUnit Annotations:
            - @Test
                + used to denote a method as a JUnit test

            - @Ignore("reason for being ignored")
                + used to indicate that JUnit should skip this test

            - @BeforeClass
                + denotes a method as a "set up" method that will run only once,
                  before all test case methods run.

            - @AfterClass
                + denotes a "tear down" method that will run only once
                  after all test case methods run.
                + must be declared as static

            - @Before
                + denotes a method as a "set up" method but will run
                  before each test case runs.
                + a thing you do before every test, instead of just running
                  once overall
            - @After
                + denotes a method as a "tear down" method, but will run after
                each test case runs.
     */


    @BeforeClass
    public static void runBeforeTestSuit(){
        System.out.println("called runBeforeTestSuite()");
    }

    @AfterClass
    public static void runAfterTestSuit(){
        System.out.println("called runBeforeTestSuite()");
    }

    @Before
    public void runBeforeEachTestCase(){
        System.out.println("called runBeforeEachTestCase()");

    }

    @After
    public void runAfterEachTestCase(){
        System.out.println("called runAfterEachTestCase()");
    }

    @Test
    public void test1() {
        System.out.println("called test1()");
    }
    // indicates that we expect for this test case to throw an exception
    @Test(expected = Exception.class)
    public void test2() {
        System.out.println("called test2()");
    }
    @Test
    @Ignore("You can give a reason why this test is ignored")
    public void test3() {
        System.out.println("should not be called, because it is ignored");
    }
}

