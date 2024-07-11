package DP;

import java.util.*;
import java.io.*;

public class BOJ_13302_리조트 {

    static boolean[] isRest = new boolean[301];
    static int n,m;
    static int O = 10_000;
    static int T = 25_000;
    static int F = 37_000;
    static int MAX = 5000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if(m != 0) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < m; i++) {
                int curDay = Integer.parseInt(st.nextToken());

                isRest[curDay] = true;
            }
        }

        int[][] dp = new int[n+1][301];

        for(int j = 0 ; j <= n ; j++) {
            for (int i = 0; i <= 100; i++) {
                dp[j][i] = MAX;
            }
        }

        dp[0][0] = 0;

        for(int i = 1 ; i <= n ; i++) {

           if(isRest[i]) {
               for(int j = 0 ; j <= 100 ; j++) {
                   dp[i][j] = dp[i-1][j];
               }
           }

           for(int j = 0 ; j < 97 ; j++) {
               dp[i][j] = Math.min(dp[i][j] , dp[i-1][j + 3]);
           }

           if(i - 1 >= 0) {
               for(int j = 0 ; j <= 100 ; j++) {
                   dp[i][j] = Math.min(dp[i][j] , dp[i-1][j] + O);
               }
           }

           if(i - 3 >= 0 && !isRest[i-2]) {
               for(int j = 0 ; j < 100 ; j++) {
                   dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i-3][j] + T);
               }
           }

           if(i - 5 >= 0 && !isRest[i-4]) {
               for(int j = 0 ; j <= 98 ; j++) {
                   dp[i][j + 2] = Math.min(dp[i][j + 2], dp[i-5][j] + F);
               }
           }

        }

       // print(dp);

        int result = dp[n][0];

        for(int i = 1 ; i <= 100 ; i++) {
            result = Math.min(result,dp[n][i]);
        }

        System.out.println(result);

        br.close();
    }

    static void print(int[][] dp) {
        for(int i = 0 ; i <= n ; i++) {
            System.out.print(i+" : ");
            for(int j = 0 ; j<= 3; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
