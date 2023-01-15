package com.example.foodfinder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCheckerTestErrorConditions {

    /* Date in format yyyy-mm-dd */
    final String dateRegex = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

    final String txtExtensionRegex = ".*\\.txt";

    final String numberRegex = "[1-9][0-9]*";

    /**
     * Check the check() method of StringChecker by giving a date regex
     * Use an invalid date
     */
    @Test
    public void checkIncorrectDateFormat() {
        boolean expected = false;
        boolean actual = StringChecker.check("3030-30-30", dateRegex);
        assertEquals(expected, actual);
    }

    @Test
    /**
     * Check the check() method of StringChecker by giving a txtExtension regex
     * Use an invalid path
     */
    public  void checkIncorrectTxtExtension() {
        boolean expected = false;
        boolean actual = StringChecker.check("file.jpgtxt", txtExtensionRegex);
        assertEquals(expected, actual);
    }

    @Test
    /**
     * Check the check() method of StringChecker by giving a txtExtension regex
     * Use an invalid path
     */
    public  void checkIncorrectNumberLetters() {
        boolean expected = false;
        boolean actual = StringChecker.check("a10b0", numberRegex);
        assertEquals(expected, actual);
    }

    @Test
    /**
     * Check the check() method of StringChecker by giving a txtExtension regex
     * Use an invalid path
     */
    public  void checkIncorrectNumberBeginZero() {
        boolean expected = false;
        boolean actual = StringChecker.check("0100", numberRegex);
        assertEquals(expected, actual);
    }


}