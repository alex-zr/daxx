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

        try {
            assertEquals(3, stringCalculator.add("1,"));
            fail("Wrong string has parsed");
        } catch (NumberFormatException nfe) {}
        
        try {
            stringCalculator.add("1,\n");
            fail("Wrong string has parsed");
        } catch (NumberFormatException nfe) {}
    }
      
    @Test
    public void addChangeDelim() throws Exception {
        assertEquals(3, stringCalculator.add("//;\n1;2"));
    }

    @Test
    public void addNegatives() throws Exception {
        try {
            assertEquals(0, stringCalculator.add("-1,-2\n-3"));
        } catch (Exception e) {
            String message = e.getMessage();
            String prefix = "negatives not allowed ";
            assertTrue(message.startsWith(prefix));
            assertEquals("[-1, -2, -3]", message.substring(prefix.length()));

        }
    }

    @Test
    public void addBigNumbers() throws Exception {
        assertEquals(3, stringCalculator.add("1000,2,1001"));
    }
}