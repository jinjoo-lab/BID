package DP;

import java.util.*;
import java.io.*;

public class BOJ_1509_팰린드롬_분할 {

    static int n;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();
        n = arr.length;

        boolean[][] dp = new boolean[n+1][n+1];
        for(int i = 0 ; i < n ; i++) {
            dp[i][i] = true;
        }

        for(int i = 0 ; i < n - 1 ; i++) {
            if(arr[i] == arr[i+1])
                dp[i][i+1] = true;
        }

        for(int k = 2 ; k < n ; k++) {
            for(int i = 0 ; i < n - k ; i++) {
                int last = i + k;

                if(arr[i] == arr[last]) {
                    if(dp[i+1][last - 1]) {
                        dp[i][last] = true;
                    }
                }
            }
        }

        int[] go = new int[n + 1];
        for(int i = 1 ; i <= n ; i++) {
            go[i] = 2501;
        }

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= i ; j++) {
                if(dp[j - 1][i - 1]) {
                    go[i] = Math.min(go[i], go[j-1] + 1);
                }
            }
        }

        System.out.println(go[n]);

        br.close();
    }

    static void printOne(int[][] dp) {
        for(int i = 0 ; i < n ; i++) {
            System.out.print(dp[0][i]+" ");
        }
        System.out.println();
    }

    static void print(boolean[][] dp) {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j ++) {

                int v = dp[i][j] ? 1 : 0;

                System.out.print(v+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
