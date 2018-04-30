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
        assertThat("isMatch(\"abc\",\"abc*\") should return true", isMatch("abc", "abc*"));
        assertThat("isMatch(\"abc\",\"abc+\") should return true", isMatch("abc", "abc+"));
        assertThat("isMatch(\"abc\",\"ab+\") should return true", isMatch("abc", "ab+"));
        assertThat("isMatch(\"abc\",\"a*c\") should return true", isMatch("abc", "a*c"));
        assertThat("isMatch(\"abc\",\"a+c\") should return true", isMatch("abc", "a+c"));
        assertThat("isMatch(\"abc\",\"a+\") should return true", !isMatch("abc", "a+"));
        assertThat("isMatch(\"abc\",\"a*d*\") should return true", !isMatch("abc", "a*d*"));
        assertThat("isMatch(\"abc\",\"a**\") should return true", isMatch("abc", "a**"));
        assertThat("isMatch(\"abc\",\"a*c*\") should return true", isMatch("abc", "a*c*"));
        assertThat("isMatch(\"abc\",\"a*c+\") should return true", isMatch("abc", "a*c+"));
        assertThat("isMatch(\"abc\",\"a*c+**+\") should return true", isMatch("abc", "a*c+**+"));
        assertThat("isMatch(\"\",\"*\") should return true", isMatch("", "*"));
        assertThat("isMatch(\"a\",\"*\") should return true", isMatch("a", "*"));
        assertThat("isMatch(\"abcdefg\",\"*\") should return true", isMatch("abcdefg", "*"));
        assertThat("isMatch(\"\",\"+\") should return true", isMatch("", "+"));
        assertThat("isMatch(\"a\",\"+\") should return true", isMatch("a", "+"));
        assertThat("isMatch(\"ab\",\"+\") should return true", !isMatch("ab", "+"));

        assertThat("isMatch(\"abc\",\"ab+c\") should return true", isMatch("abc", "ab+c"));
        assertThat("isMatch(\"abc\",\"+abc\") should return true", isMatch("abc", "+abc"));
        assertThat("isMatch(\"abc\",\"abc+\") should return true", isMatch("abc", "abc+"));
        assertThat("isMatch(\"abc\",\"a+bc\") should return true", isMatch("abc", "a+bc"));
        assertThat("isMatch(\"abc\",\"a+bcd\") should return true", !isMatch("abc", "a+bcd"));
        assertThat("isMatch(\"abc\",\"abc+d\") should return true", !isMatch("abc", "abc+d"));




        System.out.println("All tests passed successfully");
    }
    /**
     * @param input - a string input containing characters from a to z
     * @param regex - a regex containing a to z, +s and *s
     * @return true if the input matches the regex, false otherwise
     */
    public static boolean isMatch(String input, String regex) {

        int regLen = regex.length();
        int inputLen = input.length();

        //empty Cases
        if(inputLen == 0)
            return isAllSpecial(regex);

        if(regLen == 0)
            return false; //because inputLen is not empty

        char regCh = regex.charAt(0);
        switch (regCh) {

            case ASTERIX : {
                if(regLen == 1) return true;// '*' => Match ALL
                return isMatch(input.substring(1), regex.substring(1)) || isMatch(input.substring(1),regex);
            }

            case PLUS : {
                if(regLen == 1) return inputLen <=1;// '+' => Match 0 , or 1 character only
                return isMatch(input.substring(1), regex.substring(1)) || isMatch(input,regex.substring(1));
            }
            //REG EX starts with letter
            default: {
                if(regex.charAt(0) != input.charAt(0))
                    return false;
                return isMatch(input.substring(1),regex.substring(1));
            }
        }

    }

    private static boolean isAllSpecial(String regex) {
        for(char ch : regex.toCharArray()) {
            if (ch != PLUS && ch != ASTERIX )
                return false;
        }
        return true;
    }



}
