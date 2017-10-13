package com.daxx;

import java.util.*;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        List<String> strNumbers = parseNumbersStr(numbers);
        checkIfNegativesPresentThrowError(strNumbers);

        return strNumbers.stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }


    private List<String> parseNumbersStr(String numbers) {
        List<String> strNumbers;
        List<String> delimsList = Arrays.asList(",", "\n");

        if (numbers.startsWith("//")) {
            String delimiterStr = getDelimiterString(numbers);
            String delim = delimiterStr.substring(2);
            numbers = numbers.substring(delimiterStr.length() + 1);
            delimsList = Arrays.asList(delim, "\n");
        }
        strNumbers = split(numbers, delimsList);
        return strNumbers;
    }

    private String getDelimiterString(String numbers) {
        return numbers.substring(0, numbers.indexOf("\n"));
    }

    private List<String> split(String str, List<String> delimsList) {
        List<String> res = Collections.singletonList(str);
        for (String delim : delimsList) {
            res = res.stream()
                    .flatMap(s -> Arrays.stream(s.split("[" + delim + "]+", -1)))
                    .collect(Collectors.toList());
        }

        return res;
    }


    private void checkIfNegativesPresentThrowError(List<String> strNumbers) {
        List<Integer> negatives = strNumbers.stream()
                .mapToInt(Integer::parseInt)
                .filter(n -> n < 0)
                .boxed()
                .collect(Collectors.toList());

        if (!negatives.isEmpty()) {
            throw new RuntimeException("negatives not allowed " + negatives);
        }
    }
}
