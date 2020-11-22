package com.molboro1.java.problems.strings;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeTest {

    /**
     * KAJAK (5 letters)
     * iterate through from 0 to half of the string length: 0..2
     * nonMatch  true if there is no match for a predicate: char(i) != char(5-i-1), false otherwise
     * KAJAK:
     *   - char(0)=K, char(4)=K => false (same)
     *   - char(1)=A, char(3)=A => false (same)
     */
    public boolean isTextPalindrome(String text) {
        String temp = text.replaceAll("\\s+", "").toLowerCase();
        return IntStream.range(0, temp.length() / 2)
                .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
//                .noneMatch(i -> {
//                    int j = temp.length() - i - 1;
//                    boolean val = temp.charAt(i) != temp.charAt(j);
//                    System.out.println("i="+i+", j="+j);
//                    return val;
//                });
    }

    /**
     * start with 127,
     * - map it:
     * dived it by 10 and round to integer, we will have: 127, than 12, than 1 as an input to mpa operation
     * map output is equal to: number % 10 (rest): 127/10=>7, 12/10=>2, 1/10=>1
     * - reduce it: multiple acc by 10 & add map output number
     * 0*10+7=>7 ==> 7*10+2=>72, 72*10+1=>721
     * - since 127 is not equal to 721 - it is not a palindrome. Bu t it would be
     * but it would be for 727 - if we would reverse it - it would still be 727.
     */
    public static boolean isNumberPalindromeJava8(int number) {
        return number == IntStream.iterate(number, i -> i / 10)
                .map(n -> n % 10)
                .limit(String.valueOf(number).length()) //we need to know when to stop in java8
                .reduce(0, (a, b) -> a * 10 + b);
    }

    public static boolean isNumberPalindromeJava9(int number) {
        return number == IntStream.iterate(number, n -> n != 0, i ->  i / 10)
                .map(n -> n % 10)
                .reduce(0, (a, b) -> a * 10 + b);
    }

    @Test
    public void shouldCheckForPalindromes() {
        assertTrue(isTextPalindrome("kajak"));
        assertFalse(isTextPalindrome("kajak2"));

        assertTrue(isNumberPalindromeClassic("kajak"));
        assertFalse(isNumberPalindromeClassic("kajak2"));

        assertTrue(isNumberPalindromeJava8WithDebug(727));
        assertFalse(isNumberPalindromeJava8(1231));

        assertTrue(isNumberPalindromeJava9WithDebug(747));
        assertFalse(isNumberPalindromeJava9(1231));
    }

    public static boolean isNumberPalindromeJava8WithDebug(int number) {
        System.out.println("Is number " + number + " a palindrome ?");

        return number == IntStream.iterate(number, i -> i / 10)
                .map(n -> {
                    System.out.println("MAP IN, n=" + n);
                    int val = n % 10;
                    System.out.println("MAP OUT, val=" + val);
                    return val;
                })
                .limit(String.valueOf(number).length()) //we need to know when to stop in java8
                .reduce(0, (a, b) -> {
                    System.out.println("REDUCE IN, a=" + a + ", b=" + b);
                    int val = a * 10 + b;
                    System.out.println("REDUCE OUT, val=" + val);
                    return val;
                });
    }

    public static boolean isNumberPalindromeJava9WithDebug(int number) {
        System.out.println("Is number " + number + " a palindrome ?");

        return number == IntStream.iterate(number, n -> {
            System.out.println("ITERATE, n=" + n);
            return n != 0;
        }, i -> {
            int val = i / 10;
            System.out.println("ITERATE,val(i/10)=i=" + i + ", val=" + val);
            return val;
        })
                .map(n -> {
                    System.out.println("MAP IN, n=" + n);
                    int val = n % 10;
                    System.out.println("MAP OUT, val=" + val);
                    return val;
                })
                .reduce(0, (a, b) -> {
                    System.out.println("REDUCE IN, a=" + a + ", b=" + b);
                    int val = a * 10 + b;
                    System.out.println("REDUCE OUT, val=" + val);
                    return val;
                });
    }

    public boolean isNumberPalindromeClassic(String text) {
        String clean = text.replaceAll("\\s+", "").toLowerCase();
        int length = clean.length();
        int forward = 0;
        int backward = length - 1;
        while (backward > forward) {
            char forwardChar = clean.charAt(forward++);
            char backwardChar = clean.charAt(backward--);
            if (forwardChar != backwardChar)
                return false;
        }
        return true;
    }
}
