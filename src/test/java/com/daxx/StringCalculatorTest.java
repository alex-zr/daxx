package com.daxx;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {
    private StringCalculator stringCalculator;
    
    @Before
    public void setUp() throws Exception {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void add() throws Exception {
        assertEquals(0, stringCalculator.add(""));
        assertEquals(1, stringCalculator.add("1"));
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    public void addNewLineDelim() throws Exception {
        assertEquals(3, stringCalculator.add("1\n2"));
    }
}