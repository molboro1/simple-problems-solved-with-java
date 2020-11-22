package com.molboro1.java.problems.strings;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class StringToIntegerTest {

    /**
     * convert a string to a signed integer
     * <p>
     * algorithm:
     * - assuming we have number 215
     * -  2 represents 200 because it is in the hundred’s place,
     * -  1 represents 10 because it is in the ten’s place,
     * -  and the 5 is just 5 because it is in the one’s place.
     * -  Sum is = 200 + 10 + 5 => 215
     * - scan  digits from right to left, so the first number would be in one's place, than you will have 10'th place, .... => 5*1 + 1*10 + 2*100
     * - or we could go from left to right and when there is a new value found in the string - we multiple all of them by 10:
     * first scan: 2
     * second scan: 2 & 1, so we multiply 2*10 and add 1
     * third scan: we have 21 and we found 5, so we multiply by 10 previously found numbers: 21*10 and we have 210 + 5 => 215
     * - what about negative value? A negative number has a '-' character in the first position. We need to skip it for the conversion and use negation at the end (or calculate this: (0-215)=-215 for the "-215" string)
     * <p>
     * -String  "123" jes represented internally as table [
     */
    private static Integer convertStringToInteger(String numberAsString) {
        int i = 0, num = 0;
        boolean isNeg = false;
        int len = numberAsString.length();

        if (numberAsString.charAt(0) == '-') {
            isNeg = true;
            i = 1;
        }

        while (i < len) {
            num *= 10;
            num += (numberAsString.charAt(i) - '0');
            i++;
        }
        if (isNeg)
            num = -num;
        return num;
    }

    private static Integer convertStringToIntegerUsingStreams(String numberAsString) {
        boolean isNegative = (numberAsString.charAt(0) == '-') ? true : false;
        int i = (isNegative) ? 1 : 0;

        Integer value = IntStream.range(i, numberAsString.length())
                .mapToObj(index -> numberAsString.charAt(index))
                .map(Character::getNumericValue)
                .reduce((acc, digit) -> {
                    acc = (acc * 10) + digit;
                    return acc;
                }).get();
        value = (isNegative) ? -value : value;
        return value;
    }

    private static Integer convertStringToIntegerUsingStreamsWithDebug(String numberAsString) {
        boolean isNegative = (numberAsString.charAt(0) == '-') ? true : false;
        int i = (isNegative) ? 1 : 0;

        Integer value = IntStream.range(i, numberAsString.length())
                .mapToObj(index -> numberAsString.charAt(index))
                .map(Character::getNumericValue)
                .reduce((acc, digit) -> {
                    System.out.println("==input: acc=" + acc + ", digit=" + digit);
                    acc = (acc * 10) + digit;
                    System.out.println("==output: acc=" + acc);
                    return acc;
                }).get();
        value = (isNegative) ? -value : value;
        System.out.println("== final outcome is:" + value);
        return value;
    }

    @Test
    public void shouldConvertStringToInteger() {
        assertThat((int) -215)
                .isEqualTo(convertStringToInteger("-215"));
        assertThat((int) -216)
                .isEqualTo(convertStringToIntegerUsingStreams("-216"));
        assertThat((int) -217)
                .isEqualTo(convertStringToIntegerUsingStreamsWithDebug("-217"));
    }
}
