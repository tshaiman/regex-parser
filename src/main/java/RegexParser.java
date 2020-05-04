import org.apache.commons.lang3.StringUtils;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Inneractive's special regex parser.
 * possible regex values are [a-z], +, *, where: + is zero or one character and * is zero to any number of characters
 */
public class RegexParser {
    static final char PLUS = '+';
    static final char ASTERIX = '*';
    static final char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {

        assertThat("isMatch(\"ab\",\"a+\") should return true", isMatch("ab", "a+"));
        assertThat("isMatch(\"abc\",\"a*\") should return true", isMatch("abc", "a*"));
        assertThat("isMatch(\"abc\",\"ab*\") should return true", isMatch("abc", "ab*"));
        assertThat("isMatch(\"abc\",\"abc*\") should return true", isMatch("abc", "abc*"));
        assertThat("isMatch(\"abc\",\"abc+\") should return true", isMatch("abc", "abc+"));
        assertThat("isMatch(\"abc\",\"ab+\") should return true", isMatch("abc", "ab+"));
        assertThat("isMatch(\"abc\",\"a*c\") should return true", isMatch("abc", "a*c"));
        assertThat("isMatch(\"abc\",\"a+c\") should return true", isMatch("abc", "a+c"));
        assertThat("!isMatch(\"abc\",\"a+\") should return true", !isMatch("abc", "a+"));
        assertThat("!isMatch(\"abc\",\"a*d*\") should return true", !isMatch("abc", "a*d*"));
        assertThat("isMatch(\"abc\",\"a**\") should return true", isMatch("abc", "a**"));
        assertThat("isMatch(\"abc\",\"a*c*\") should return true", isMatch("abc", "a*c*"));
        assertThat("isMatch(\"abc\",\"a*c+\") should return true", isMatch("abc", "a*c+"));
        assertThat("isMatch(\"abc\",\"a*c+**+\") should return true", isMatch("abc", "a*c+**+"));
        assertThat("isMatch(\"\",\"*\") should return true", isMatch("", "*"));
        assertThat("isMatch(\"a\",\"*\") should return true", isMatch("a", "*"));
        assertThat("isMatch(\"abcdefg\",\"*\") should return true", isMatch("abcdefg", "*"));
        assertThat("isMatch(\"\",\"+\") should return true", isMatch("", "+"));
        assertThat("isMatch(\"a\",\"+\") should return true", isMatch("a", "+"));
        assertThat("!isMatch(\"ab\",\"+\") should return true", !isMatch("ab", "+"));

        assertThat("isMatch(\"abc\",\"ab+c\") should return true", isMatch("abc", "ab+c"));
        assertThat("isMatch(\"abc\",\"+abc\") should return true", isMatch("abc", "+abc"));
        assertThat("isMatch(\"abc\",\"abc+\") should return true", isMatch("abc", "abc+"));
        assertThat("isMatch(\"abc\",\"a+bc\") should return true", isMatch("abc", "a+bc"));
        assertThat("!isMatch(\"abc\",\"a+bcd\") should return true", !isMatch("abc", "a+bcd"));
        assertThat("!isMatch(\"abc\",\"abc+d\") should return true", !isMatch("abc", "abc+d"));

        assertThat("isMatch(\"abc\",\"+bc\") should return true", isMatch("abc", "+bc"));
        assertThat("isMatch(\"abc\",\"*bc\") should return true", isMatch("abc", "*bc"));
        assertThat("isMatch(\"abc\",\"*c\") should return true", isMatch("abc", "*c"));
        assertThat("!isMatch(\"abc\",\"*d\") should return true", !isMatch("abc", "*d"));


        assertThat("isMatch(\"abdbcdd\",\"**++abdbc+dd++**\") should return true", isMatch("abdbcdd", "**++abdbc+dd++**"));
        assertThat("isMatch(\"ac\",\"a******c\") should return true", isMatch("ac", "a******c"));
        assertThat("isMatch(\"ac\",\"**a***c**\") should return true", isMatch("ac", "**a***c**"));
        assertThat("isMatch(\"abcdef\",\"a*\") should return true", isMatch("abcdef", "*+a***b++c+++*d++e++f**"));


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
        if (inputLen == 0)
            return !StringUtils.containsAny(regex, letters);

        if (regLen == 0)
            return false; //because inputLen is not empty

        char regCh = regex.charAt(0);
        switch (regCh) {

            case ASTERIX: {
                return isMatch(input.substring(1), regex.substring(1))          //case "1"
                        || isMatch(input.substring(1), regex)                   //case "0"
                        || isMatch(input, regex.substring(1));                  //case "N"
            }

            case PLUS: {
                return isMatch(input.substring(1), regex.substring(1))          //case "1"
                        || isMatch(input, regex.substring(1));                  //case "0"
            }
            //REG EX starts with letter
            default: {
                if (regex.charAt(0) != input.charAt(0))
                    return false;
                return isMatch(input.substring(1), regex.substring(1));
            }
        }

    }


}
