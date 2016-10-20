package com.daniel;

/**
 * CSCI 3230A - Data Structures
 * Daniel Burer
 * Due September 8th, 2016
 *
 * This class is an implementation of a Bag data structure. Methods called by other methods in this class
 * include a boolean "verbose" argument for printing complexity and runtime. In the main testing class,
 * this is set to true to show these statistic for each unique method. However, in this class when they are called
 * this is set to false to avoid output clutter and to keep focus on the main method being shown.
 */
public class Bag {

    final private Object[] contents; // Array holding contents of the bag. Length is set by int passed into constructor.
    private int bagCount; // Keeps track of all non-null elements of the contents array. This is the number of actual Objects.

    // Constructs bag of size max.
    public Bag(int max) {
        contents = new Object[max];
        bagCount = 0;
    }

    // Returns amount of objects currently in bag.
    public int size() {
        return bagCount;
    }

    // Returns whether or not the bag is empty.
    public boolean isEmpty() {
        return bagCount == 0;
    }

    public Bag clone() {
        Bag cloned = new Bag(contents.length);
        cloned.addAll(this, false);
        return cloned;
    }

    // Adds an object to the bag, checking first to see if the bag is full or not.
    public void add(Object o, boolean verbose) {
        long tStart = System.nanoTime();
        int steps = 0;
        if (size() < contents.length) {
            steps++;
            contents[size()] = o;
            steps++;
            bagCount++;
            steps++;
            long tEnd = System.nanoTime();
            if (verbose) {
                printComplexity(steps, tEnd - tStart, "add()");
            }
            return;
        }
        steps++;
        System.out.printf("%s:\tERROR - Bag is full. Item could not be added.%n", this);
        long tEnd = System.nanoTime();
        if (verbose) {
            printComplexity(steps, tEnd - tStart, "add()");
        }
    }

    // Removes an object from the bag, checking first to see if the bag contains said object.
    public void remove(Object o, boolean verbose) {
        long tStart = System.nanoTime();
        int steps = 0;
        if (this.contains(o, false)) {
            steps++;
            for (int i = 0; i < size(); i++) {
                steps++;
                if (contents[i].equals(o)) {
                    steps++;
                    contents[i] = contents[size() - 1];
                    steps++;
                    contents[size() - 1] = null;
                    steps++;
                    bagCount--;
                    steps++;
                    long tEnd = System.nanoTime();
                    if (verbose) {
                        printComplexity(steps, tEnd - tStart, "remove()");
                    }
                    return;
                }
            }
        }
        String err = "ERROR - Item not found in bag. Item could not be removed.";
        long tEnd = System.nanoTime();
        if (verbose) {
            printComplexity(err, steps, tEnd - tStart, "remove()");
        }
    }

    // Removes a random object from the bag.
    public void removeRandom() {
        int steps = 0;
        long tStart = System.nanoTime();
        contents[(int) (Math.random() * size() - 1)] = contents[size() - 1];
        steps++;
        contents[size() - 1] = null;
        steps++;
        bagCount--;
        steps++;
        long tEnd = System.nanoTime();
        printComplexity(steps, tEnd - tStart, "removeRandom()");
    }

    // Checks to see if the bag contains a specific object.
    public boolean contains(Object o, boolean verbose) {
        int steps = 0;
        long tStart = System.nanoTime();
        for (int i = 0; i < size(); i++) {
            steps++;
            if (contents[i].equals(o)) {
                steps++;
                long tEnd = System.nanoTime();
                if (verbose) {
                    printComplexity(steps, tEnd - tStart, "contains()");
                }
                return true;
            }
        }
        long tEnd = System.nanoTime();
        if (verbose) {
            printComplexity(steps, tEnd - tStart, "contains()");
        }
        return false;
    }

    // Adds all of the contents of another bag to this bag, checking to prevent overflow.
    public Bag addAll(Bag b, boolean verbose) {
        int steps = 0;
        long tStart = System.nanoTime();
        for (int i = 0; i < b.size(); i++) {
            steps++;
            if (size() < contents.length) {
                this.contents[size()] = b.contents[i];
                steps++;
                bagCount++;
                steps++;
            } else {
                String err = "ERROR - Bag is full. Item could not be added.";
                long tEnd = System.nanoTime();
                if (verbose) {
                    printComplexity(err, steps, tEnd - tStart, "addAll()");
                }
                return this;
            }
            steps++;
        }
        long tEnd = System.nanoTime();
        if (verbose) {
            printComplexity(steps, tEnd - tStart, "addAll()");
        }
        return this;
    }

    // Returns a bag which is a combination of the current bag and another bag.
    public Bag union(Bag b, boolean verbose) {
        int steps = 0;
        long tStart = System.nanoTime();
        Bag combined = new Bag(size() + b.size());
        steps++;
        for (int i = 0; i < size(); i++) {
            combined.contents[i] = this.contents[i];
            steps++;
            combined.bagCount++;
            steps++;
        }
        for (int i = 0; i < b.size(); i++) {
            combined.contents[combined.size()] = b.contents[i];
            steps++;
            combined.bagCount++;
            steps++;
        }
        long tEnd = System.nanoTime();
        if (verbose) {
            printComplexity(steps, tEnd - tStart, "union()");
        }
        return combined;
    }

    // Determine if a bag is the same size and contains the same contents as another bag.
    public boolean equals(Bag b) {
        int steps = 0;
        long tStart = System.nanoTime();
        Bag cloneBag = this.clone();
        steps++;
        if (cloneBag.size() != b.size()) {
            steps++;
            long tEnd = System.nanoTime();
            printComplexity(steps, tEnd - tStart, "equals()");
            return false;
        }
        steps++;
        for (int i = 0; i < b.size(); i++) {
            if (cloneBag.contains(b.contents[i], false)) {
                steps++;
                cloneBag.remove(b.contents[i], false);
                steps++;
            }
            steps++;
        }
        long tEnd = System.nanoTime();
        printComplexity(steps, tEnd - tStart, "equals()");
        return cloneBag.isEmpty();
    }

    // Used for more human-readable console output and bag identification.
    public String toString() {
        return String.format("Bag[%s]", this.contents.length);
    }

    // Utility methods to print the complexity and time of bag methods.
    private void printComplexity(int steps, long time, String method) {
        System.out.printf("%s:%s:%n\tBasic operations (complexity): %d%n\t" +
                "Run time: %fms%n%n", this, method, steps, time / 1000000.0);
    }

    private void printComplexity(String err, int steps, long time, String method) {
        System.out.printf("%s:%s:%n\t%s%n\tBasic operations (complexity): %d%n\t" +
                "Run time: %fms%n%n", this, method, err, steps, time / 1000000.0);
    }
}
