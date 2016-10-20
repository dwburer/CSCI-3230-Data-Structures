package com.daniel;

/**
 * CSCI 3230A - Data Structures
 * Daniel Burer
 * Due September 8th, 2016
 *
 * This class is designed to demonstrate each class method for Bags of various sizes
 * and display the runtime and complexity of each.
 */
public class Main {

    public static void main(String[] args) {

        Bag small = new Bag(20);

        for (int i = 0; i < 10; i++) {
            small.add(new Object(), false);
        }

        Bag large = new Bag(100000);

        for (int i = 0; i < 50000; i++) {
            large.add(new Object(), false);
        }

        Object testObject = new Object(); // For demonstrating add(), contains(), and remove() with a known object.

        System.out.printf("-------------------%nMethods for %s%n-------------------%n%n", small);
        System.out.printf("%s:%n\tsize(): %d%n%n", small, small.size());
        System.out.printf("%s:%n\tisEmpty(): %s%n%n", small, small.isEmpty());
        small.removeRandom();
        small.add(testObject, true);
        small.contains(testObject, true);
        small.remove(testObject, true);
        small.addAll(small, true);

        System.out.printf("------------------------%nMethods for %s%n------------------------%n%n", large);
        System.out.printf("%s:%n\tsize(): %d%n%n", large, large.size());
        System.out.printf("%s:%n\tisEmpty(): %s%n%n", large, large.isEmpty());
        large.removeRandom();
        large.add(testObject, true);
        large.contains(testObject, true);
        large.remove(testObject, true);
        large.addAll(large, true);

        Bag combined = small.union(large, true);
        Bag combined2 = large.union(small, true);

        System.out.printf("-------------------------%nDemonstration of equals()%n-------------------------%n%n");
        System.out.printf("Bag combined is equal to Bag combined2: %s%n%n", combined.equals(combined2));
        System.out.println("------------------------------------------------");
        System.out.printf("Bag small is equal to Bag small: %s%n%n", small.equals(small));
        System.out.println("------------------------------------------------");
        System.out.printf("Bag small is equal to Bag large: %s%n%n", small.equals(large));

    }
}
