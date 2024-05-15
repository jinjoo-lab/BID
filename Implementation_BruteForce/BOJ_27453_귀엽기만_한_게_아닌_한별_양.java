package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_27453_귀엽기만_한_게_아닌_한별_양 {

    static int n,m,k;

    static char[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int sx,sy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        for(int i = 1 ; i <= n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = arr[j-1];

                if(board[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }
            }
        }

        br.close();
    }

    static int bfs(){
        int result = -1;



        return result;
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        if(board[x][y] == 'X')
            return true;

        return false;
    }

    static class Node{
        int x;
        int y;
        int[] numList = new int[4];

        Node(int x,int y){
            this.x = x;
            this.y = y;
        }

    }
}
