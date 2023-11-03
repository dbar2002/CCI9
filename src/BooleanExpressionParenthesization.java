// 8.14 Given a boolean expression consisting of the symbols 0 (false), 1 (true), &
// (AND), I (OR), and /\ (XOR), and a desired boolean result value result, implement a function to
// count the number of ways of parenthesizing the expression such that it evaluates to result.

public class BooleanExpressionParenthesization {

    public static int countWaysToEvaluate(String expression, boolean result) {
        int n = expression.length() / 2 + 1;
        int[][] trueCount = new int[n][n];
        int[][] falseCount = new int[n][n];

        // Initialize base cases
        for (int i = 0; i < n; i++) {
            if (expression.charAt(2 * i) == '1') {
                trueCount[i][i] = 1;
            } else {
                falseCount[i][i] = 1;
            }
        }

        // Fill the tables using dynamic programming
        for (int len = 3; len <= n; len += 2) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                for (int k = i; k < j; k += 2) {
                    char operator = expression.charAt(2 * k + 1);
                    int waysTrue = trueCount[i][k] * trueCount[k + 1][j];
                    int waysFalse = (trueCount[i][k] + falseCount[i][k]) * (trueCount[k + 1][j] + falseCount[k + 1][j]) - waysTrue;

                    if (operator == '&') {
                        if (result) {
                            trueCount[i][j] += waysTrue;
                        } else {
                            falseCount[i][j] += waysFalse;
                        }
                    } else if (operator == '|') {
                        if (result) {
                            trueCount[i][j] += waysTrue + trueCount[i][k] * falseCount[k + 1][j] + falseCount[i][k] * trueCount[k + 1][j];
                        } else {
                            falseCount[i][j] += waysFalse;
                        }
                    } else if (operator == '^') {
                        if (result) {
                            trueCount[i][j] += trueCount[i][k] * falseCount[k + 1][j] + falseCount[i][k] * trueCount[k + 1][j];
                        } else {
                            falseCount[i][j] += waysFalse + trueCount[i][k] * trueCount[k + 1][j] + falseCount[i][k] * falseCount[k + 1][j];
                        }
                    }
                }
            }
        }

        return result ? trueCount[0][n - 1] : falseCount[0][n - 1];
    }

    public static void main(String[] args) {
        String expression = "1|0&1^1";
        boolean result = true;

        int ways = countWaysToEvaluate(expression, result);
        System.out.println("Number of ways to parenthesize the expression to evaluate to " + result + ": " + ways);
    }
}
