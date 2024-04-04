package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class Swea_2112_보호_필름_2 {

    static int result = 1001;
    static int t, n, m, k;

    static int[][] board;

    static int[] change;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();


        t = Integer.parseInt(st.nextToken());

        for (int a = 1; a <= t; a++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            board = new int[n + 1][m + 1];
            change = new int[n + 1];
            result = 1001;
            
            for (int i = 1; i <= n; i++) {
                change[i] = -1;
            }

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                for (int j = 1; j <= m; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            go(1,0);
            sb.append("#"+a+" "+result+"\n");
        }

        System.out.print(sb);

        br.close();
    }

    static int[] dx = {-1,0,1};

    static void go(int idx,int num){

        if(result <= num)
            return;

        if(idx > n) {
            boolean keep = true;

            for(int i = 1 ; i <= m ; i++){
                boolean tmp = can(i);

                if(!tmp){
                    keep = false;
                    break;
                }
            }

            if(keep){
                result = Math.min(result,num);
            }
            return;
        }

        for(int i = 0 ; i <=2 ; i++){
            change[idx] = dx[i];
            int plusNum = change[idx] != -1 ? 1 : 0;
            go(idx+1,num+plusNum);
        }

    }

    static boolean oneCycle(int i,int j){
        int sNum = change[i] != -1 ? change[i] : board[i][j];

        for (int z = i; z < i + k; z++) {
            int nNum = change[z] != -1 ? change[z] : board[z][j];

            if (sNum != nNum) {
                return false;
            }
        }

        return true;
    }

    static boolean can(int j) {

        for (int i = 1; i <= n; i++) {

            if (i + k - 1 > n)
                break;

            if(oneCycle(i,j))
                return true;
        }

        return false;
    }

}
