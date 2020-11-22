package com.molboro1.java.problems.strings;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.stream.Collector;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerToStringTest {

    private static final int REDIX = 10; //redix 10 is for decimal number, for hexa use redix 16

    /**
     * take a number: -479
     * 1) if number < 0 - it is negative, work with the non-negative one but remember this flag
     * 2) 479 / 100 = 4 (first digit); 479 – 4 * 100 = 79
     * 79 / 10 = 7 (second digit); 79 – 7 * 10 = 9
     * 9 / 1 = 9 (third digit)
     * 3) build string from that, append negative sign if needed
     */
    private static String convertIntegerToString(Integer number) {
        boolean isNegative = (number < 0);
        if (isNegative) number = -number;

        LinkedList<Integer> stack = new LinkedList<>();
        while (number > 0) {
            stack.push(number % 10);
            number = number / 10;
        }
        String stringValue = stack.stream()
                .map(digit -> Character.forDigit(digit, REDIX))
                // .peek(e -> System.out.println(e))
                .collect(
                        Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append, StringBuilder::toString)
                );

        return (isNegative) ? ("-" + stringValue) : stringValue;
    }

    @Test
    public void shouldConvertStringToInteger() {
        assertThat("-215")
                .isEqualTo(convertIntegerToString(-215));
        assertThat("-215")
                .isEqualTo(intToStrSecondApproach(-215));

    }

    public static final int MAX_DIGITS = 10;

    public static String intToStrSecondApproach(int num) {
        int i = 0;
        boolean isNeg = false;
        /* Buffer big enough for largest int and - sign */
        char[] temp = new char[MAX_DIGITS + 1];
        /* Check to see if the number is negative */
        if (num < 0) {
            num = -num;
            isNeg = true;
        }

        /* Fill buffer with digit characters in reverse order */
        while (num != 0) {
            temp[i++] = (char) ((num % 10) + '0');
            num /= 10;
        }
        StringBuilder b = new StringBuilder();
        if (isNeg)
            b.append('-');

        while (i > 0) {
            b.append(temp[--i]);
        }
        return b.toString();
    }

}
