package com.daxx;

import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        return Arrays.stream(numbers.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
