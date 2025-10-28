import java.util.*;

public class DP_Palindrome_Tabulation {
    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) return s;

        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLen = 1;

        // All substrings of length 1 are palindromes
        for (int i = 0; i < n; i++) dp[i][i] = true;

        // Substrings of length >= 2
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2) dp[i][j] = true;
                    else dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j] && len > maxLen) {
                        start = i;
                        maxLen = len;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // one valid output: "bab" or "aba"
        System.out.println(longestPalindrome("cbbd"));  // "bb"
    }
}
