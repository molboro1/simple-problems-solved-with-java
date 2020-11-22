package com.molboro1.java.problems.recursion;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * play with n! problem
 */
public class FactorialOperatorTest {

    /**
     * play with n! problem:
     *     n! = n (n – 1)! till 0! = 1! = 1
     *     4! = 4· 3 · 2 · 1 = 24
     *     4! = 4·3!=>4·3·2!=>4·3·2·1=>24
     */
    int factorial(int n) {
        return (n > 1) ? factorial(n - 1) * n : 1;
    }


    @Test
    public void shouldcalculateFactorial() {
        assertThat(24).isEqualTo(factorial(4));
    }
}
