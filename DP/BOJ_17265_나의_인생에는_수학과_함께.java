package DP;

import java.util.*;
import java.io.*;

public class BOJ_17265_나의_인생에는_수학과_함께 {

    static int n;
    static char[][] board;
    static StringTokenizer st;

    static int[] dx = {0,-1};
    static int[] dy = {-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new char[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++) {
                board[i][j] = st.nextToken().charAt(0);
            }
        }

        int[][] dp = new int[n+1][n+1];
        int[][] dp2 = new int[n+1][n+1];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                dp[i][j] = Integer.MIN_VALUE;
                dp2[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[1][1] = board[1][1] - '0';
        dp2[1][1] = dp[1][1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {

                if(board[i][j] == '+' || board[i][j] == '-' || board[i][j] == '*')
                    continue;

                int curV = board[i][j] - '0';

                for(int a = 0 ; a <= 1 ; a++) {
                    for(int b = 0 ; b <= 1 ; b++) {
                        int nx1 = i + dx[a];
                        int ny1 = j + dy[a];

                        int nx2 = nx1 + dx[b];
                        int ny2 = ny1 + dy[b];

                        if(isOut(nx1,ny1) || isOut(nx2,ny2))
                            continue;


                        int tmpV = cal(dp[nx2][ny2], curV, board[nx1][ny1]);
                        int tmp2V = cal(dp2[nx2][ny2], curV , board[nx1][ny1]);

                        dp[i][j] = Math.max(dp[i][j], tmpV);
                        dp2[i][j] = Math.min(dp2[i][j], tmp2V);
                    }
                }
            }
        }

        System.out.println(dp[n][n]+" "+dp2[n][n]);

        br.close();
    }

    static void print(int[][] dp) {
        for(int i = 1 ; i <= n ; i+=2) {
            for(int j = 1 ; j <= n ; j+=2) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static int cal(int v1,int v2,char op) {
        if(op == '+') {
            return v1 + v2;
        }else if(op == '*') {
            return v1 * v2;
        }else {
            return v1 - v2;
        }
    }
}
