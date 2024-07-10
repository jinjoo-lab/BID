package DP;

import java.util.*;
import java.io.*;

public class BOJ_2662_기업투자 {

    static int n,m;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int money = Integer.parseInt(st.nextToken());

            for(int j = 1 ; j <= m ; j++) {
                board[money][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n+1][m+1];



        br.close();
    }
}
