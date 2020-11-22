package com.molboro1.java.problems.strings;

import static org.assertj.core.api.Assertions.assertThat;  // main one
import static org.assertj.core.api.Assertions.atIndex; // for List assertions
import static org.assertj.core.api.Assertions.entry;  // for Map assertions
import static org.assertj.core.api.Assertions.tuple; // when extracting several properties at once
import static org.assertj.core.api.Assertions.fail; // use when writing exception tests
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown; // idem
import static org.assertj.core.api.Assertions.filter; // for Iterable/Array assertions
import static org.assertj.core.api.Assertions.offset; // for floating number assertions
import static org.assertj.core.api.Assertions.anyOf; // use with Condition
import static org.assertj.core.api.Assertions.contentOf; // use with File assertions

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseStringTest {
    private String reverse(String word) {
        return IntStream.range(0, word.length())
                .mapToObj(i -> word.charAt((word.length() - 1) - i))
                .map(character -> String.valueOf(character))
                .collect(Collectors.joining(""));
    }

    @Test
    public void shouldReverseTheStringUsingStreams() {
        assertThat("BOOKS").isEqualTo(reverse("SKOOB"));
    }
}
