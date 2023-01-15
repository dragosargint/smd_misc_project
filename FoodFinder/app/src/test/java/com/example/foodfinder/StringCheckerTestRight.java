package com.example.foodfinder;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringCheckerTestRight {

    /* Date in format yyyy-mm-dd */
    final String dateRegex = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

    final String txtExtensionRegex = ".*\\.txt";

    final String numberRegex = "[1-9][0-9]*";

    @Test
    /**
     * Check the check() method of StringChecker by giving a date regex
     */
    public void checkCorrectDateFormat() {
        boolean expected = true;
        boolean actual = StringChecker.check("2022-12-20", dateRegex);
        assertEquals(expected, actual);
    }

    @Test
    /**
     * Check the check() method of StringChecker by giving a txtExtension regex
     */
    public  void checkCorrectTxtExtension() {
        boolean expected = true;
        boolean actual = StringChecker.check("file.txt", txtExtensionRegex);
        assertEquals(expected, actual);
    }

    @Test
    public  void checkCorrectNumber() {
        boolean expected = true;
        boolean actual = StringChecker.check("1234", numberRegex);
        assertEquals(expected, actual);
    }

}