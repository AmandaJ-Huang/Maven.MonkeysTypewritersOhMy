package io.zipcoder;

import java.util.NoSuchElementException;

public class MonkeyTypewriter {
    public static void main(String[] args) {
        String introduction = "It was the best of times,\n" +
                "it was the blurst of times,\n" +
                "it was the age of wisdom,\n" +
                "it was the age of foolishness,\n" +
                "it was the epoch of belief,\n" +
                "it was the epoch of incredulity,\n" +
                "it was the season of Light,\n" +
                "it was the season of Darkness,\n" +
                "it was the spring of hope,\n" +
                "it was the winter of despair,\n" +
                "we had everything before us,\n" +
                "we had nothing before us,\n" +
                "we were all going direct to Heaven,\n" +
                "we were all going direct the other way--\n" +
                "in short, the period was so far like the present period, that some of\n" +
                "its noisiest authorities insisted on its being received, for good or for\n" +
                "evil, in the superlative degree of comparison only.";

        // Do all of the Monkey / Thread building here
        // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
        // A Tale Of Two Cities.
        UnsafeCopier unsafeCopier = new UnsafeCopier(introduction);
        Thread monkeyOne = new Thread(unsafeCopier, "monkeyOne");
        Thread monkeyTwo = new Thread(unsafeCopier, "monkeyTwo");
        Thread monkeyThree = new Thread(unsafeCopier, "monkeyThree");
        Thread monkeyFour = new Thread(unsafeCopier, "monkeyFour");
        Thread monkeyFive = new Thread(unsafeCopier, "monkeyFive");

        try {
            monkeyOne.start();
            monkeyTwo.start();
            monkeyThree.start();
            monkeyFour.start();
            monkeyFive.start();
        } catch (NoSuchElementException e) {
            System.out.println("monkey no go");
        }

        SafeCopier safeCopier = new SafeCopier(introduction);
        monkeyOne = new Thread(safeCopier, "monkeyOne");
        monkeyTwo = new Thread(safeCopier, "monkeyTwo");
        monkeyThree = new Thread(safeCopier, "monkeyThree");
        monkeyFour = new Thread(safeCopier, "monkeyFour");
        monkeyFive = new Thread(safeCopier, "monkeyFive");

        try {
            monkeyOne.start();
            monkeyTwo.start();
            monkeyThree.start();
            monkeyFour.start();
            monkeyFive.start();
        } catch (NoSuchElementException e) {
            System.out.println("monkey do go");
        }

        // This wait is here because main is still a thread and we want the main method to print the finished copies
        // after enough time has passed.
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("MAIN INTERRUPTED");
        }

        // Print out the copied versions here.
        System.out.println(new StringBuilder()
                .append("Unsafe Copier: \n")
                .append(unsafeCopier.copied));

        System.out.println(new StringBuilder()
                .append("-------------\n")
                .append("Safe Copier: \n")
                .append(safeCopier.copied));
    }
}