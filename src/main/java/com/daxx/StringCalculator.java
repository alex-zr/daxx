package com.daxx;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        List<String> strNumbers = parseNumbersStr(numbers);
        checkIfNegativesPresentThrowError(strNumbers);

        return strNumbers.stream()
                .mapToInt(Integer::parseInt)
                .map(i -> i >= 1000 ? i - 1000 : i)
                .sum();
    }


    private List<String> parseNumbersStr(String numbers) {
        List<String> strNumbers;
        List<String> delimsList = Arrays.asList(",", "\n");

        if (numbers.startsWith("//")) {
            String delimitersStr = getDelimitersString(numbers);
            delimsList = getDelimitersAsList(delimitersStr);
            numbers = numbers.substring(delimitersStr.length() + 1);
        }
        strNumbers = split(numbers, delimsList);
        return strNumbers;
    }

    private String getDelimitersString(String numbers) {
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

    private List<String> getDelimitersAsList(String numbers) {
        numbers = numbers.substring(2);
        if (!numbers.startsWith("[")) {
            if (numbers.length() != 1) {
                throw new RuntimeException("Delimiters format error " + numbers);
            }
            return Collections.singletonList(numbers);
        }
        boolean isDelimFormatError = Arrays.stream(new String[] {numbers})
                .flatMap(this::splitDelims)
                .filter(s -> !s.startsWith("[") || !s.endsWith("]"))
                .collect(Collectors.toList()).isEmpty();

        if (isDelimFormatError) {
            throw new RuntimeException("Delimiters format error " + numbers);
        }
        return Arrays.stream(new String[]{numbers})
                .flatMap(this::splitDelims)
                .collect(Collectors.toList());
    }

    private Stream<String> splitDelims(String str) {
        List<String> res = new LinkedList<>();
        while (str.startsWith("[")) {
            String delim = str.substring(1, str.indexOf("]"));
            res.add(delim);
            str = str.substring(delim.length() + 2);
        }

        return res.stream();
    }
}
