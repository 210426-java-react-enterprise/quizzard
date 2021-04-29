package com.revature.quizzard;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.screens.RegisterScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Driver;
import java.util.Arrays;


public class driver {

    int[] nonStaticArray = new int[5];
    static int[] ourArray;

    public static void main(String[] args) {
        int[] intArray1 = new int[10]; //an empty array of ints, with space for 10 element
        ourArray = createArrayOfSize(10);

        //if you don't have a static variable you need to instantiate first
        driver driverInstance = new driver();
        driverInstance.nonStaticArray = null;

        //multidimensional arrays
        int[][] multiDimensionalArray1 = new int[3][3];
        int[][] multiDimensionalArray2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        //this is LEGAL but can't be done in the instantiation syntax
        int[][] multiDimensionalArray3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9, 10}
        };

        //grabbing multidimensional array values, even if they are irregular
        for (int i = 0; i < multiDimensionalArray3.length; i++) {
            for (int j = 0; j < multiDimensionalArray3[i].length; j++) {
                System.out.printf("Array %d, value: %d \n", i, multiDimensionalArray3[i][j]);
            }

        }
        //The Arrays utility class
        int[] arrayForSorting = {4, 1, -1, 0, 0, 34, 9, 93};
        Arrays.sort(arrayForSorting);
        for (int value : arrayForSorting) {
            System.out.println(value);
        }

        //This object array can hold various crazy things, but it's gross
        Object[][] multiDimensionalArray4 = {
                {1,2,3},
                {"gross",5,6}
        };
        //To access one of the objects oyu will need to cast
        //but having to do this is bad practice, make arrays with the same type!
        String str = (String) multiDimensionalArray4[1][1]; //explicit casting


        //Parametric polymorphism


    }

    //instance method!
    public int[] createArrayOfSize_instance(int size){
        return new int[size];
    }

    //class-scope method!
    public static int[] createArrayOfSize(int size){
        return new int[size];
    }

    //The ... means you are passing in String array of ANY SIZE
    public static void printValues(String...strings){

        if(strings.length == 0){
            System.out.println("Nothing provided!");
        }

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }

}
