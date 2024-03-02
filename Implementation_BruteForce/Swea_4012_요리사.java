package Implementation_BruteForce;

import java.util.*;
import java.io.*;


public class Swea_4012_요리사 {
    static int t = 0;
    static int n = 0;

    static int[] data;
    static int[][] board;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for (int a = 1; a <= t; a++) {

            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            board = new int[n+1][n+1];
            data = new int[n];
            for(int i = 1 ; i <= n ; i++){
                st = new StringTokenizer(br.readLine(), " ");

                for(int j = 1 ; j <= n ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = Integer.MAX_VALUE;

            comb(0,0);

            sb.append("#"+a+" "+result+"\n");
        }

        System.out.print(sb);
        br.close();
    }
    static int getResult(){
        boolean[] peek = new boolean[n+1];
        int[] data2 = new int[n/2];
        int idx = 0;

        for(int i = 0 ; i < n / 2  ; i++){
            peek[data[i]] = true;
        }

        for(int i =1 ; i <= n ; i++){
            if(!peek[i]){
                data2[idx] =  i;
                idx++;
            }
        }

        int result1 = 0;
        for(int i = 0 ; i < n / 2 - 1 ; i++){
            for(int j = i + 1 ; j < n / 2 ; j++){
                result1 += board[data[i]][data[j]] + board[data[j]][data[i]];
            }
        }

        int result2 = 0;
        for(int i = 0 ; i < n / 2 - 1 ; i++){
            for(int j = i + 1 ; j < n / 2 ; j++){
                result2 += board[data2[i]][data2[j]] + board[data2[j]][data2[i]];
            }
        }


        return Math.abs(result1 - result2);
    }

    static void comb(int depth,int idx){

        if(depth == n / 2){
            result = Math.min(result,getResult());
            return;
        }

        for(int i = idx ; i < n ; i++){
            data[depth] = i + 1;
            comb(depth + 1,i + 1);
        }
    }
}
