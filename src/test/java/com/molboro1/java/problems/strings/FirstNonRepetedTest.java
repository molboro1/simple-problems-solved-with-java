package com.molboro1.java.problems.strings;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstNonRepetedTest {
    private Character findFirstNotRepeatedChar(String word) {
        Character result =  word.chars()
                .mapToObj(a -> (char)a)
                .collect(Collectors.groupingBy(a->a, LinkedHashMap::new, Collectors.counting())) //store in a map with the count
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst().get();

        return result;
    }

    @Test
    public void shouldReturnFirstNonRepeatedCharacterFromString() {
        assertThat('g').isEqualTo(findFirstNotRepeatedChar("abdefhabdscdcefghasdscdwf"));
    }
}
