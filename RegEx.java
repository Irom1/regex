public class RegEx {
    private String[] options;

    public RegEx(String options) {
        this.options = options.split(", ");
    }

    public String findAllMatches(String expression) {
        //System.out.println("Processing " + expression);
        String result = "";
        for (String option : options) {
            if (findMatch(expression, option)) {
                result += option + ", ";
            }
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 2);
        } else {
            result = "NONE";
        }
        return result;
    }

    private boolean findMatch(String expression, String test) {
        if (expression.indexOf(".") != -1) {
            return periodMatch(expression, test);
        } else if (expression.indexOf("{") != -1) {
            return curlyBracketMatch(expression, test);
        } else if (expression.indexOf("*") != -1) {
            return starMatch(expression, test);
        } else if (expression.indexOf("[^") != -1) {
            return bracketCarrotMatch(expression, test);
        } else if (expression.indexOf("[") != -1) {
            return bracketMatch(expression, test);
        } else {
            return expression.equals(test); // if no operators, just compare
        }
    }

    private boolean periodMatch(String expression, String test) {
        // Matches any single character. Ex: a.c matches aac, abc, acc, adc, a#c,
        // a4c...etc.
        String beforePeriod = expression.substring(0, expression.indexOf("."));
        String afterPeriod = expression.substring(expression.indexOf(".") + 1);
        if (test.length() == expression.length()) {
            if (test.substring(0, beforePeriod.length()).equals(beforePeriod)
                    && test.substring(test.length() - afterPeriod.length()).equals(afterPeriod)) {
                return true;
            }
        }
        return false;
    }

    private boolean bracketMatch(String expression, String test) {
        // Matches a single character contained within the brackets. Ex: [abc] matches
        // a, b, or c.
        String possibleLetters = expression.substring(expression.indexOf("[") + 1, expression.indexOf("]"));
        if (test.length() == (expression.substring(0, expression.indexOf("["))
                + expression.substring(expression.indexOf("]"))).length()) {
            if (possibleLetters.indexOf(test.substring(expression.indexOf("["), expression.indexOf("[") + 1)) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean bracketCarrotMatch(String expression, String test) {
        // Matches any single character not contained within the brackets. [^abc]
        // matches any character other than a, b, or c.
        String possibleLetters = expression.substring(expression.indexOf("[") + 1, expression.indexOf("]"));
        if (test.length() == (expression.substring(0, expression.indexOf("["))
                + expression.substring(expression.indexOf("]"))).length()) {
            if (!(possibleLetters
                    .indexOf(test.substring(expression.indexOf("["), expression.indexOf("[") + 1)) != -1)) {
                return true;
            }
        }
        return false;
    }

    private boolean starMatch(String expression, String test) {
        // Matches the preceding character zero or more times. Ex; a*b matches b, ab,
        // aab, aaab, ...etc.
        char prevChar = expression.charAt(expression.indexOf("*") - 1);
        String beforeStar = expression.substring(0, expression.indexOf("*") - 1);
        String stuffAfterStar = expression.substring(expression.indexOf("*") + 1);
        if (beforeStar.equals(test.substring(0, beforeStar.length()))) {
            for (int i = beforeStar.length(); i < test.length() - stuffAfterStar.length(); i++) {
                if (test.charAt(i) == prevChar) {
                    continue;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        if (!stuffAfterStar.equals(test.substring(test.length() - stuffAfterStar.length()))) {
            return false;
        }
        return true;
    }

    private boolean curlyBracketMatch(String expression, String test) {
        // Matches the preceding character at least m but not more than n times. Ex:
        // a{3,5} matches aaa, aaaa, or aaaaa.
        
        // find repreatign character (before curly brackets)
        int repeatedCharIndex = expression.indexOf("{") - 1;
        char repeatedChar = expression.charAt(repeatedCharIndex);
        // find m and n
        int m = Integer.parseInt(expression.substring(expression.indexOf("{") + 1, expression.indexOf(",")));
        int n = Integer.parseInt(expression.substring(expression.indexOf(",") + 1, expression.indexOf("}")));
        // find substring before repeating character
        String beforeRepeatingChar = expression.substring(0, repeatedCharIndex);
        // find substring after curly brackets
        String afterRepeatingChar = expression.substring(expression.indexOf("}") + 1);
        // generate all possible strings with m to n repeating characters
        String[] possibleStrings = new String[n - m + 1];
        int index = 0;
        while (m <= n) {
            String possibility = beforeRepeatingChar;
            for(int i = 0; i < m; i++){
                possibility += repeatedChar;
            }
            possibility += afterRepeatingChar;
            possibleStrings[index] = possibility;
            index++;
            m++;
        }
        // check if test is one of the possible strings
        for(String possibility : possibleStrings){
            if (possibility.equals(test)) {
                return true;
            }
        }
        return false;
    }
}