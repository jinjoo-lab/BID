package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1332_풀자 {

    static int count1 = 0;
    static int n,v,result;

    static int[] board;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        result = n;

        int[][][] dp = new int[55][51][51];
        dp[0][0][0]=1;





        System.out.println(result);

        br.close();
    }



}
