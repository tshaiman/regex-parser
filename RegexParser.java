package com.interviewQuestions;

import java.util.StringTokenizer;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Inneractive's special regex parser.
 * possible regex values are [a-z], +, *, where: + is zero or one character and * is zero to any number of characters
 */
public class RegexParser {
    static final  char PLUS = '+';
    static final  char ASTERIX = '*';

    public static void main(String[] args) {
        assertThat("isMatch(\"ab\",\"a+\") should return true", isMatch("ab", "a+"));
        assertThat("isMatch(\"abc\",\"a*\") should return true", isMatch("abc", "a*"));
        assertThat("isMatch(\"abc\",\"ab*\") should return true", isMatch("abc", "ab*"));




        System.out.println("All tests passed successfully");
    }
    /**
     * @param input - a string input containing characters from a to z
     * @param regex - a regex containing a to z, +s and *s
     * @return true if the input matches the regex, false otherwise
     */
    public static boolean isMatch(String input, String regex) {
        return true;

    }




}
