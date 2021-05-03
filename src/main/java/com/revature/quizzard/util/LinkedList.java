package com.revature.quizzard.util;

//Day 5 Lecture

//Adds documentation that will show up when you hover over the LinkedList<T>
/**
 * A simple implementation of a doubly LinkedList structure
 * that does not accept null data.
 * @param <T>
 */

public class LinkedList<T> implements List<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public void add(T data) throws IllegalArgumentException {
        //check if data is null
        if(data == null){
            throw new IllegalArgumentException("This LinkedList does not accept null values!");
        }

        Node<T> newNode = new Node<T>(data);

        //checks if this is the first node
        if(head == null){
            //This is legal!(
            tail = (head = newNode); //sets both head and tail equal to new node
        }else{
            //Remember that tail is a pointer
            //set the nextNode of tail to the newNode
            //then set prevNode of newNode to the tail
            //then set the tail to the newNode;
            tail.nextNode = newNode;
            newNode.prevNode = tail;
            tail = newNode;
        }
        //Increment size of LinkedList once you add a node.
        size++;

    }

    /**
     * Returns the head node's data or else returns null.
     * @return
     */
    @Override
    public T pop() {
        if(head == null){
            return null;
        }

        T soughtData = head.data;
        //point the head to the next node, thus removing the current head
        //garbage collection ensues
        head = head.nextNode;

        if(head != null){
            head.prevNode = null;
        }else{
            tail = null;
        }

        size--;
        return soughtData;


    }

    @Override
    public T get(int index){
        return null;
    }

    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    //Not a good idea to make a Node class separate
    //You want to encapsulate your Node where you will
    //Exclusively use it in LinkedList

    private static class Node<T>{
        T data;
        Node<T> nextNode;
        Node<T> prevNode;

        //Constructor 1, where prev and next are null
        //This would be the first node you inject
        Node(T data){
            this.data = data;
        }

        //Constructor 2
        Node(T data, Node<T> nextNode, Node<T> prevNode){
            this.data = data;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }


    }

}

