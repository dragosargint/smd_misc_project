package com.example.foodfinder;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringChecker {
    public static boolean check(String string, String regex)
    {

        boolean isRegex;
        Pattern pattern = null;

        try {
            pattern = Pattern.compile(regex);
            isRegex = true;
        } catch (PatternSyntaxException e) {
            isRegex = false;
        }

        if (isRegex == true && pattern != null)
            return pattern.matcher(string).matches();

        return false;
    }
}
