package com.revature.quizzard.util;

import com.revature.quizzard.screens.Screen;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LinkedListTest {

    private LinkedList<String> sut;

    //Having this set up and tear down method ensures you are working with methods
    //A unit test tests the smallest unit of code, which is a method
    //If it tests more than a method then it's integration testing.
    @Before
    public void setUpTest(){
        sut = new LinkedList<>();
    }

    @After
    public void tearDownTest(){
        sut = null;
    }

    //Declare our first test
    @Test
    public void test_addWithNonNullValue(){
        //Arrange (prepare the test)
        int expectedSize = 1;

        //Act (do the test)
        sut.add("data");

        //Assert (ensure the results)
        int actualSize = sut.size();
        //Assert methods in JUNit are all static!
        //.assertSame are looking for the same object
        //.assertEquals looks if the values are the same
        Assert.assertEquals(expectedSize, actualSize);

    }
    //expected will always be looking for Exception classes
    @Test(expected = IllegalArgumentException.class)
    public void test_addWithNullValue(){
        //Arrange
        //sometimes blank if there's nothing in particular to do

        //Act
        sut.add(null);

        //Assert
        //sometimes blank, especially if you expect an exception to be thrown
    }

    //NOTE: Tests always return void
    @Test
    public void test_popWithEmptyList(){
        //Arrange

        //Act
        String actualResult = sut.pop();

        //Assert
        Assert.assertNull(actualResult);
    }

    @Test
    public void test_popWithPopulatedList(){
        //Arrange
        sut.add("test data 1");
        sut.add("test data 2");
        String expectedResult = "test data 1";
        int expectedSize = 1;

        //Act
        String actualResult = sut.pop();

        //Assert
        int actualSize = sut.size();
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSize, actualSize);
    }
    /* Hard code Tests
    //sut stands for "system under test"
    private LinkedList<String> sut;
    private LinkedList<? extends Object> ex1; //generics with subtyping, anything that extends object
    private LinkedList<? extends Screen> ex12; //generics specific to screens
    private LinkedList<?> ex2; // the ? denotes a wildcard

    //Naming conventions of test methods will vary
    public void test_add_withNull(){
        //Arrange test
        sut = new LinkedList<>();

        //Act (perform the action to be tested)
        try{
            sut.add(null);
            System.out.println("Test: test_add_withNull did not pass!");
        }catch (IllegalArgumentException e){
            //Assert (assert that what you got is what you expected)
            System.out.println("Test: test_add_withNull passed!");
        }

    }

    public void test_add_withNonNullValue(){
        //Arrange
        sut = new LinkedList<>();

        //Act
        sut.add("not null!!");

        //Assert
        String result = sut.pop();
        assert result.equals("not null!!");
        System.out.println("Test: test_add_withNonNullValue passed!");

    }

    */
}
