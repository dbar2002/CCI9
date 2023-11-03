// 8.9 Parens: Implement an algorithm to print all valid
// (e.g., properly opened and closed) combinations
// of n pairs of parentheses

import java.util.ArrayList;
import java.util.List;

public class ParenthesesCombinations {

    public static List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateParenthesisHelper("", n, n, combinations);
        return combinations;
    }

    private static void generateParenthesisHelper(String current, int openCount, int closeCount, List<String> combinations) {
        if (openCount == 0 && closeCount == 0) {
            combinations.add(current);
            return;
        }

        if (openCount > 0) {
            generateParenthesisHelper(current + "(", openCount - 1, closeCount, combinations);
        }

        if (closeCount > openCount) {
            generateParenthesisHelper(current + ")", openCount, closeCount - 1, combinations);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        List<String> combinations = generateParenthesis(n);

        for (String combination : combinations) {
            System.out.println(combination);
        }
    }
}
