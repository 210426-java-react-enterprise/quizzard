package com.revature.quizzard.oop;

import java.util.List;

public class OopDriver {
    public static void main(String[] args) {
        Animal ref = new Cat(); //legal! called covariance
//        Cat wontWork = new Animal(); //illegal! called contravariance

        ref.makeSound(); //Meow! because Animal has no body
        System.out.println(ref.numberOfLives); //should be 1 because reference is Animal
    }

    //covariacne is in Java because in this you can pass in any type of List
    //including ArrayList

    public void sortList(List someList){
        //implementation here
    }
}
