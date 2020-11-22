package com.molboro1.java.problems.strings;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ReverseWordsTest {

    public static String reverseWordsInSentence(String sentence) {
        String[] words = sentence.split(" ");

        return IntStream.rangeClosed(0, words.length - 1)
                        .mapToObj(i -> words[words.length - 1 - i])
                        .collect(Collectors.joining(" "));
    }

    @Test
    public void shouldReverseTheStringUsingStreams() {
        assertThat("I'm Java Developer").isEqualTo(reverseWordsInSentence("Developer Java I'm"));
    }
}
