package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_16500_문자열_판별 {

    static char[] first;
    static int n,l;

    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        first = br.readLine().toCharArray();
        l = first.length;

        dp = new boolean[l+1][l+1];

        n = Integer.parseInt(br.readLine());

        for(int i = 1 ; i <= n ; i ++) {
            char[] input = br.readLine().toCharArray();

            for(int j = 0 ; j < l; j++) {
               if(j + input.length > l)
                   break;

               dp[j][j + input.length - 1] = isIt(input,j);

            }
        }


        int result = dp[0][l - 1] ? 1 : 0;

        System.out.println(result);
        br.close();
    }

    static boolean isIt(char[] input,int idx) {
        int len = input.length;

        if(idx + len > l)
            return false;

        for(int i = 0 ; i < len ; i++) {
            if(input[i] != first[idx+i])
                return false;
        }

        return true;
    }
}
