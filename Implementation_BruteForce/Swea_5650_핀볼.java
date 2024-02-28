package Implementation_BruteForce;

import java.io.*;
import java.util.*;

public class Swea_5650_핀볼 {

    static int t = 0;
    static int n = 0;
    static int[][] board;

    // R - L - U - D
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");

        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");

            n = Integer.parseInt(st.nextToken().trim());

            board = new int[n+1][n+1];

            for(int i = 1 ; i <= n ; i++){

                st = new StringTokenizer(br.readLine()," ");

                for(int j = 1 ; j <= n ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken().trim());
                }
            }


        }

        br.close();
    }

    static void print(){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
