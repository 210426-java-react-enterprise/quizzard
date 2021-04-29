package com.revature.quizzard;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.screens.RegisterScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class driver {

    static int[] ourArray;

    public static void main(String[] args) {
        int[] intArray1 = new int[10]; //an empty array of ints, with space for 10 element
        ourArray = createArrayOfSize(10);


    }

    //instance method!
    public int[] createArrayOfSize_instance(int size){
        return new int[size];
    }

    //class-scope method!
    public static int[] createArrayOfSize(int size){
        return new int[size];
    }


}
