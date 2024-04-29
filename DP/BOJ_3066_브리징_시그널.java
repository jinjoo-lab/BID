package DP;

import java.util.*;
import java.io.*;

public class BOJ_3066_브리징_시그널 {

    static int t,n;
    static int[][] input;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            input = new int[n+1][2];
            dp = new int[n+1];

            for(int i = 1 ; i <= n ; i++){
                int tmp = Integer.parseInt(br.readLine());

                input[i][0] = i;
                input[i][1] = tmp;
            }

            dp[1] = input[1][1];
            int i = 2;
            int j=  1;

            while(i <= n){
                if(input[i][1] > dp[j]){
                    dp[j+1] = input[i][1];
                    j++;
                }else{
                    int idx = binarySearch(1,j,input[i][1]);
                    dp[idx] = input[i][1];
                }

                i++;
            }

            sb.append(j+"\n");
        }
        System.out.print(sb);

        br.close();
    }

    static void print(){
        for(int i = 1 ; i <= n ; i++){
            System.out.print(dp[i]+" ");
        }
        System.out.println();
    }

    static int binarySearch(int left,int right,int target){
        int mid = 0;

        while(left < right){
            mid = (left + right) / 2;

            if(dp[mid] > target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return right;
    }
}
