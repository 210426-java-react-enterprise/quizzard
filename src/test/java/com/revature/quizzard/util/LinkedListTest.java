package com.revature.quizzard.util;


import org.junit.*;

public class LinkedListTest {

    private  LinkedList<String> sut;

    @Before
    public void setUpTest() {
        sut = new LinkedList<>();
    }

    @After
    public void tearDownTest(){
        sut = null;
    }

    @Test
    public void test_addWithNonNullValue(){
        //Arrange (Prepare the test)
        int expectedSize = 1;
        //Act (Do the test)
        sut.add("data");

        //Assert (Insure the results)
        int actualSize = sut.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addWithNullValue(){
        //Arrange

        //Act
        sut.add(null);
        //Assert
        //Sometimes blank especially if exception will be thrown
    }

    @Test
    public void test_popWithEmptyList(){
        //Arrange
        //Nothing to do here

        //Act
        String actualResult = sut.pop();


        //Assert
        Assert.assertNull(actualResult);
    }

    @Test
    public void test_popWithPopulatedList(){
        //Arrange
        sut.add("Test data 1");
        sut.add("Test data 2");

        String expectedResult = "Test data 1";
        int expectedSize = 1;
        String actualReult = sut.pop();
        int actualSize = sut.size();

        Assert.assertEquals(expectedResult, actualReult);
        Assert.assertEquals(expectedSize, actualSize);
    }
}
