package DP;

import java.util.*;
import java.io.*;

public class BOJ_5569_출근_경로 {

    static int n,m;

    static int MOD = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[][][][] dp = new int[n+1][m+1][2][2];

        dp[1][1][0][0] = 1;
        dp[1][1][1][1] = 1;

        for(int i = 2 ; i <= n; i++) {
            dp[i][1][0][0] = 1;
        }

        for(int i = 2 ; i <= m ; i++) {
            dp[1][i][1][0] = 1;
        }


        for(int i = 2 ; i <= n ; i++) {
            for(int j = 2 ; j <= m ; j++) {
                dp[i][j][1][0] = (dp[i][j - 1][1][1] + dp[i][j - 1][1][0]) % MOD;
                dp[i][j][1][1] = dp[i][j - 1][0][0] % MOD;
                dp[i][j][0][0] = (dp[i - 1][j][0][0] + dp[i - 1][j][0][1]) % MOD;
                dp[i][j][0][1] = dp[i - 1][j][1][0] % MOD;
            }
        }

        System.out.println((dp[n][m][0][0] + dp[n][m][0][1] + dp[n][m][1][0] + dp[n][m][1][1]) % MOD);

        br.close();
    }


}
