package com.revature.quizzard.oop;

public class Cat extends Animal{
    // this is a redeclaration of a variable in Animal
    //also known as "shadowing"
    public int numberOfLives = 9;

    @Override
    public void makeSound(){
        System.out.println("Meow!");
    }
}
