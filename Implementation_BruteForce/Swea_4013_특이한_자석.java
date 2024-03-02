package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class Swea_4013_특이한_자석 {
    static int t = 0;
    static int n = 0;

    static int[][] board;
    static int result;

    static int[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for (int a = 1; a <= t; a++) {

            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            board = new int[5][9];

            for(int i = 1 ; i <= 4 ; i++){
                st = new StringTokenizer(br.readLine(), " ");

                for(int j = 1 ; j <= 8 ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 1 ; i <= n ; i++){
                st = new StringTokenizer(br.readLine(), " ");

                int what = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                find(what,dir);

                for(int j =1 ; j <= 4 ; j++){
                    if(v[j] != 0){
                        turn(j,v[j]);
                    }
                }
            }

            int result = board[1][1] + board[2][1] * 2 + board[3][1] * 4 + board[4][1] * 8;
            v = new int[5];

            sb.append("#"+a+" "+result+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int changeDir(int dir){
        if(dir == 1)
            return -1;

        return 1;
    }

    static void find(int idx,int dir){
        v = new int[5];
        v[idx] = dir;

        int  tmpDir = dir;

        for(int i = idx ; i < 4 ; i++){
            if(board[i][3] != board[i+1][7]){
                tmpDir = changeDir(tmpDir);
                v[i+1] = tmpDir;
            }else{
                break;
            }
        }

        tmpDir = dir;

        for(int i = idx ; i > 1 ; i--){
            if(board[i][7] != board[i-1][3]){
                tmpDir = changeDir(tmpDir);
                v[i-1] = tmpDir;
            }else{
                break;
            }
        }
    }

    static void print(int idx){
        for(int  i = 1 ; i <= 8 ; i++){
            System.out.print(board[idx][i]+" ");
        }
        System.out.println();
    }

    static void turn(int idx,int dir){
        if(dir == 1){
            int tmp = board[idx][8];

            for(int i = 8; i >= 2 ; i--){
                board[idx][i] = board[idx][i - 1];
            }

            board[idx][1] = tmp;
        }else{
            int tmp = board[idx][1];

            for(int i = 1 ; i <= 7 ; i++){
                board[idx][i] = board[idx][i+1];
            }

            board[idx][8] = tmp;
        }
    }
}
