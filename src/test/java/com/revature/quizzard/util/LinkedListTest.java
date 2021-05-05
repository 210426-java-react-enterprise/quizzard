package com.revature.quizzard.util;

import com.revature.quizzard.util.LinkedList;
import org.junit.Test;

public class LinkedListTest {

    private LinkedList<String> sut;

    public void test_add_withNull() {

        // Arrange test
        sut = new LinkedList<>();

        // Act (perform the action to be tested)
        try {
            sut.add(null);
            System.err.println("Test: test_add_withNull did not pass!");
        } catch (IllegalArgumentException e) {
            // Assert
            System.out.println("Test: test_add_withNull passed!");
        }

    }


    @Test
    public void test_pollWithEmptyList() {
        // Arrange
        // nothing to do here...

        // Act
        String actualResult = sut.poll();


        // Act (do the test)
        sut.add("not null!!");

    /*
    @Test
    public void test_pollWithPopulatedList(){
        // Arrange
        sut.add("test data 1");
        sut.add("test data 2");
        String expectedResult = "test data 1";
        int expectedSize = 1;

        // Act
        String actualResult = sut.poll();

        //Asert
    }


    // TODO: (Associate task) implement this method!
    @Test
    public void test_peekWithEmptyList() {

    }

    // TODO: (Associate task) implement this method!
    @Test
    public void test_peekWithPopulatedList() {

    }

    // TODO: (Associate task) implement this method!
    @Test
    public void test_containsWithEmptyList() {

    }

    // TODO: (Associate task) implement this method!
    @Test
    public void test_containsWithPopulatedList() {

    }

*/
    }
}