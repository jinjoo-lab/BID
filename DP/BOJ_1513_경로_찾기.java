package DP;

import java.util.*;
import java.io.*;

public class BOJ_1513_경로_찾기 {

    static int n,m,k;
    static int[][] board;

    static int[] dx = {0,1};
    static int[] dy = {1,0};

    static int[][][][] dp;
    // (i,j) 지점에 k개의 오락실을 방문하고 l이 가장 큰 지점 왔을 때

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        for(int i =1 ; i <= k ;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = i;
        }

        dp = new int[n+1][m+1][k+1][k+1];

        if(board[1][1] == 0) {
            dp[1][1][0][0] = 1;
        }else {
            dp[1][1][1][board[1][1]] = 1;
        }

        for(int i = 2 ; i <= n ; i++) {

        }


        br.close();
    }


    static void go(int limit) {

    }
}
