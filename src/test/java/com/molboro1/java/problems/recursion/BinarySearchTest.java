package com.molboro1.java.problems.recursion;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Implement a function to perform a binary search on a sorted array of integers to find the index of a given integer
 */
public class BinarySearchTest {
    int[] sortedArray = {3, 4, 5, 6, 7, 8, 9};

    int binarySearchRecursive(int[] array, int target) {
        return binarySearchRecursive(array, target, 0, array.length - 1);
    }

    /**
     * we are looking for number 5, so returned index should be 2: sortedArray[2]=5
     * - range=6 (from 0 to 6, because we have 7 elements): lower=0, upper = size-1 at start
     * - center = range/2 + lower, so 3
     * - if sortedArray[3] = target => return 3
     * - if target < sortedArray[3], make recursive call with range from o to 2 (center-1)
     * - if target > sortedArray[3], make recursive call with range from 4 (center+1) to 6 (upper)
     * <p>
     * - in our case it is lower, to w have a second round with:
     * - lower=0, upper=2, range=(2-0)=2, center=(range/2+lower)=1
     * - target is > than sortedArray[1], so recursive call with:
     * - center+1=2 to 2
     */
    int binarySearchRecursive(int[] array, int target, int lower,
                              int upper) {
        int center, range;

        if (lower == upper && target == array[upper]) { // found it
            return lower;
        }

        range = upper - lower;
        if (range < 0) {
            throw new RuntimeException("Element not in array");
        }

        if (array[lower] > array[upper]) {
            throw new RuntimeException("Array not sorted");
        }
        center = (range / 2) + lower;
        if (target == array[center]) { // found it
            return center;
        } else if (target < array[center]) {
            return binarySearchRecursive(array, target, lower, center - 1);
        } else {
            return binarySearchRecursive(array, target, center + 1, upper);
        }
    }


    @Test
    public void shouldcalculateFactorial() {
        assertThat(Arrays.binarySearch(sortedArray, 6))
                .isEqualTo(binarySearchRecursive(sortedArray, 6));
        assertThat(Arrays.binarySearch(sortedArray, 6))
                .isEqualTo(binarySearchIterative(sortedArray, 6));
    }

    int binarySearchIterative(int[] array, int target) {
        int lower = 0, upper = array.length - 1;
        int center, range;

        while (true) {
            range = upper - lower;
            if (range < 0) {
                throw new RuntimeException("Element not in array");
            }
            if (array[lower] > array[upper]) {
                throw new RuntimeException("Array not sorted");
            }
            center = (range / 2) + lower;
            if (target == array[center]) {
                return center;
            } else if (target < array[center]) {
                upper = center - 1;
            } else {
                lower = center + 1;
            }
        }
    }

}
