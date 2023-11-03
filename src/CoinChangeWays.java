// 8.11 Coins: Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and
//pennies (1 cent), write code to calculate the number of ways of representing n cents.

public class CoinChangeWays {

    public static int countCoinChangeWays(int n) {
        int[] coins = {1, 5, 10, 25};
        int[] dp = new int[n + 1];
        dp[0] = 1; // There is one way to make change for 0 cents (using no coins).

        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 10; // Change for 10 cents
        int ways = countCoinChangeWays(n);
        System.out.println("Number of ways to represent " + n + " cents: " + ways);
    }
}

