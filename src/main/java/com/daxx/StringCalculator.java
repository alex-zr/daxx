package com.daxx;

import java.util.Arrays;
import java.util.List;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        List<String> strNumbers = parseNumbersStr(numbers);

        return strNumbers.stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }


    private List<String> parseNumbersStr(String numbers) {
        return Arrays.asList(numbers.split("[,\n]+"));
    }
}
