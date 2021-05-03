
package com.revature.intro.nested_types;

import com.revature.intro.oop.Animal;
import com.revature.intro.oop.Sphinx;

public class OuterClass {

    private static int privateStaticField = 10;
    private int privateInstanceField = 20;

    private static void privateStaticMethod() {
        System.out.println("Private static method!");
    }

    private void privateInstanceMethod() {
        System.out.println("Private instance method!");
    }

    private void localClassExample() {

        /*
            Local classes (method-scoped class declarations) can be used if
            you need to create more than one instance of a class, access its
            constructor, or introduce a new, named type (because, for example,
            you need to invoke additional methods later).
            When a program containing a local inner class is compiled, the
            compiler generate two .class files, one for the outer class and
            the other for the inner class that has the reference to the outer
            class.
         */
        class LocalClass {
            int value;
        }

        LocalClass localClass = new LocalClass();
        localClass.value++;

    }

    private void localAnonClassExample() {

        /*
            Local anonymous classes are basically inline implementations of
            already declared classes. You can used them to implement method-scoped
            class declarations of abstract classes or interfaces.
         */
        Animal anonAnimal = new Animal() {
            @Override
            public void makeSound() {
                System.out.println("Kazoo-ka-booky!");
            }
        };

        anonAnimal.makeSound();

        /*
            You could even use a local anonymous class to make a method-scoped
            subclass declaration of a concrete type.
         */
        Sphinx anonSphinx = new Sphinx() {
            @Override
            public void makeSound() {
                super.makeSound();
            }

            @Override
            public void setCatNumberOfLives(int newLives) throws RuntimeException {
                super.setCatNumberOfLives(newLives);
            }
        };

        anonSphinx.makeSound();
    }


    /*
        Nested classes (static classes encapsulated within another class)
        have access to the outer class's static members. However, they have
        no access to the instance members of the outer class (unless a object
        reference is used).
     */
    static class NestedClass {

        int nestedClassMethod() {
            // privateInstanceField = 10; // not visible
            // privateInstanceMethod(); // not visible
            privateStaticMethod(); // visible
            return privateStaticField; // visible
        }

    }

    /*
        Inner classes (non-static classes encapsulated within another class)
        have access to both the static and non-static members of the outer
        class.
     */
    class InnerClass {

        int innerClassMethod() {
            privateStaticField++; // visible
            privateStaticMethod(); // visible
            privateInstanceMethod(); // visible
            return privateInstanceField; // visible
        }

    }
}