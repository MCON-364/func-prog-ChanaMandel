package edu.touro.las.mcon364.func_prog.exercises;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Functional Interface Practice
 *
 * In this assignment you will:
 *  - Create and return different functional interfaces
 *  - Apply them
 *  - Practice chaining where appropriate
 *
 * IMPORTANT:
 *  - Use lambdas
 *  - Do NOT use anonymous classes
 */
public class FunctionalInterfaceExercises {

    // =========================================================
    // PART 1 — SUPPLIERS
    // =========================================================

    /**
     * 1) Create a Supplier that returns the current year.
     *
     * Hint:
     * You can get the current date using:
     *     LocalDate.now()
     *
     * Then extract the year using:
     *     getYear()
     *
     * Example (not the solution):
     *
     */
    public static Supplier<Integer> currentYearSupplier() {
      Supplier<Integer> thisYear = () -> LocalDate.now().getYear();
        return thisYear;
    }

    /**
     * 2) Create a Supplier that generates a random number
     * between 1 and 100.
     */
    public static Supplier<Integer> randomScoreSupplier() {
        Random random = new Random();
        Supplier<Integer> randomNumber = () -> random.nextInt(1,100);
        return randomNumber;
    }

    // =========================================================
    // PART 2 — PREDICATES
    // =========================================================

    /**
     * 3) Create a Predicate that checks whether
     * a string is all uppercase.
     */
    public static Predicate<String> isAllUpperCase() {
        Predicate<String> isUpperCase = s -> s.equals(s.toUpperCase());
        return isUpperCase;
    }

    /**
     * 4) Create a Predicate that checks whether
     * a number is positive AND divisible by 5.
     *
     * Hint: consider chaining.
     */
    public static Predicate<Integer> positiveAndDivisibleByFive() {
        Predicate<Integer> isDivisibleByFive = i -> i % 5 == 0;
        Predicate<Integer> positive = i -> i >= 0;
        Predicate<Integer> positiveAndDivisibleByFive = isDivisibleByFive.and(positive);
        System.out.println(positiveAndDivisibleByFive.test(25));
        System.out.println(positiveAndDivisibleByFive.test(-25));
        return positiveAndDivisibleByFive;
    }

    // =========================================================
    // PART 3 — FUNCTIONS
    // =========================================================

    /**
     * 5) Create a Function that converts
     * a temperature in Celsius to Fahrenheit.
     *
     * Formula: F = C * 9/5 + 32
     */
    public static Function<Double, Double> celsiusToFahrenheit() {
        Function<Double,Double> celsiusToFahrenheit = celsius -> celsius * 9.0/5.0 + 32;
        return celsiusToFahrenheit;
    }

    /**
     * 6) Create a Function that takes a String
     * and returns the number of vowels in it.
     *
     * Bonus: Make it case-insensitive.
     */
    public static Function<String, Integer> countVowels(){
        Function<String, Integer> countVowels= string -> {
            string = string.toLowerCase();
            int vowelCtr = 0;
            for (char c : string.toCharArray()) {
                if ("aeiou".indexOf(c) >= 0) {
                    vowelCtr++;
                }
            }
            return vowelCtr;
        };
        return countVowels;
    }
    // =========================================================
    // PART 4 — CONSUMERS
    // =========================================================

    /**
     * 7) Create a Consumer that prints a value
     * surrounded by "***"
     *
     * Example output:
     * *** Hello ***
     */
    public static Consumer<String> starPrinter () {
        Consumer<String> starPrinter = s -> System.out.println("*** " + s + " ***");
        return starPrinter;
    }

    /**
     * 8) Create a Consumer that prints the square
     * of an integer.
     */
    public static Consumer<Integer> printSquare () {
        Consumer<Integer> printSquare =number -> System.out.println(number * number);
        return printSquare;
    }
    // =========================================================
    // PART 5 — APPLYING FUNCTIONAL INTERFACES
    // =========================================================

    /**
     * 9) Apply:
     *  - A Predicate
     *  - A Function
     *  - A Consumer
     *
     * Process the list as follows:
     *  - Keep only strings longer than 3 characters
     *  - Convert them to lowercase
     *  - Print them
     */
    public static void processStrings(List<String> values) {
        Predicate<String> filter = string -> string.length() >= 3;
        Function<String, String> transform = string -> string.toLowerCase();
        Consumer<String> print = string -> System.out.println(string);

        for (String s : values) {
            if(filter.test(s)) {
                String result = transform.apply(s);
                print.accept(result);
            }
        }
    }

    /**
     * 10) Apply:
     *  - A Supplier
     *  - A Predicate
     *  - A Consumer
     *
     * Generate 5 random scores.
     * Print only those above 70.
     */
    public static void generateAndFilterScores() {
        Random random = new Random();
        Supplier<Integer> scoreGenerator = () -> random.nextInt(1,100);
        Predicate<Integer> isAbove70 = score -> score > 70;
        Consumer<Integer> scorePrinter = score -> System.out.println(score);

        for(int i = 0; i < 5; i++){
            Integer score = scoreGenerator.get();
            if(isAbove70.test(score)) {
                scorePrinter.accept(score);
            }
        }
    }
}
