package com.revature.quizzard.util;

import org.junit.*;

public class LinkedListTest {
    private LinkedList<String> sut;

    @Before
    public void setUpTest() {
        sut = new LinkedList<String>();
    }

    @After
    public void tearDownTest() {
        sut = null;
    }

    @Test
    public void test_addWithNonNullValue() {
        //arrange (prepare the test)
        int expectedSize = 1;

        //act (do the test)
        sut.add("Something");

        //assert (ensue the results)
        int actualSize = sut.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addWithNullValue() {
        //arrange
        //nothing to do here

        //act
        sut.add(null);

        //assert
        //blank here too, we are testing the thrown exception.
    }
}
