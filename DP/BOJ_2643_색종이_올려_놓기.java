package DP;

import java.util.*;
import java.io.*;

public class BOJ_2643_색종이_올려_놓기 {

    static int n;
    static int[][] board;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n][2];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int max = Math.max(x,y);
            int min = Math.min(x,y);

            board[i][0] = min;
            board[i][1] = max;

        }

        Arrays.sort(board,(x,y) ->{
            if(x[1] == y[1]){
                return y[0] - x[0];
            }

            return y[1] - x[1];
        });


        if(n == 0){
            System.out.println(0);
            return;
        }

        int result = 1;
        dp = new int[n];

        dp[0] = 0;
        int i = 1;
        int j = 0;

        while(i < n){
            if(board[i][0] <= board[dp[j]][0] && board[i][1] <= board[dp[j]][1]){
                dp[j+1] = i;
                j++;
            }else{
                int idx = bs(0,j,board[i]);
                dp[idx] = i;
            }

            i++;
        }

        System.out.println(j + 1);

        br.close();
    }

    static int bs(int l,int r,int[] target){
        int mid;

        while(l <= r){
            mid = (l + r) / 2;

            if(target[0] <= board[dp[mid]][0] && target[1] <= board[dp[mid]][1]){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }

        return l;
    }


    static void print(){
        for(int i = 0 ; i < n ; i++){
            System.out.println(board[i][0]+" "+board[i][1] +" : "+dp[i]);
        }
    }
}
