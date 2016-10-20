package com.daniel;

import java.util.Scanner;

/**
 * CSCI 3230A - Data Structures
 * Daniel Burer
 * Due October 18th, 2016
 *
 * This is an implementation of a Queue data structure. Methods in the Queue class
 * include a boolean "verbose" argument for printing details about each step.
 *
 * The Queue itself is implemented using a circular array, repositioning front and back pointers as elements are enqueued
 * or dequeued.
 */

class Main {
    public static void main(String[] args) {

        Queue<Integer> smallQueue = new Queue<Integer>(20);

        for(int i = 0; i< 20; i++){
            smallQueue.enqueue((int)(Math.random() * 100), true);
        }

        int temp = smallQueue.dequeue(true);
        temp = smallQueue.dequeue(true);
        temp = smallQueue.size(true);

        long startTime = System.nanoTime();
        System.out.println("Creating largeQueue with 10,000 elements:");

        Queue<Integer> largeQueue = new Queue<Integer>(10000);

        for(int i = 0; i< 10000; i++){
            largeQueue.enqueue((int)(Math.random() * 100), false);
        }

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Took " + (elapsedTime / 1000000.0) + "ms to create largeQueue");

        System.out.println("Press \"ENTER\" to print largeQueue");
        (new Scanner(System.in)).nextLine();

        largeQueue.print();
    }
}

class Queue<T> {

    private int front = 0;
    private int back = 0;
    private int size = 0;
    private int capacity;
    private T[] contents;

    @SuppressWarnings("unchecked")
    public Queue(int capacity) {
        this.capacity = capacity;
        contents = (T[])(new Object[capacity]);
    }

    public void enqueue(T o, boolean v) {
        if(size < capacity) {
            if (v) System.out.println("--------Adding item to back: " + o + "\n");
            contents[back] = o;
            back = (back+1) % capacity;
            size++;
            if (v) this.print();
        } else {
            System.out.println("--------Item cannot be added: queue is full.");
        }
    }

    public T dequeue(boolean v) {
        if (size != 0) {
            if (v) System.out.println("--------Removing item from front: " + this.first(false) + "\n");
            T out = contents[front];
            contents[front] = null;
            front = (front+1) % capacity;
            size--;
            if (v) this.print();
            return out;
        } else {
            System.out.println("--------Item cannot be dequeued: queue is empty.");
            return null;
        }
    }

    public T first(boolean v) {
        if (v) {
            System.out.println("--------Retrieving first item: " + contents[front] + "\n");
            this.print();
        }
        return contents[front];
    }

    public int size(boolean v) {
        if (v) {
            System.out.println("--------Size of queue: " + size + "\n");
            this.print();
        }
        return size;
    }

    public void print() {
        System.out.println(":Front:");
        for(int i = front; i < size; i++) {
            System.out.println("[" + i + "]\t" + contents[i % capacity]);
        }
        System.out.println(":Back:\n");
    }
}