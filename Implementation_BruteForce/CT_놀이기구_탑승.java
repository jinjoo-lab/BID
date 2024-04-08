package Implementation_BruteForce;

import java.util.*;
import java.io.*;
public class CT_놀이기구_탑승 {

    static int n;
    static int[][] board;

    static int[][] people;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        people = new int[n*n+2][4];

        for(int i = 1 ; i <= n*n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int curNum = Integer.parseInt(st.nextToken());
            int[] curArr = new int[4];
            for(int j = 0 ; j < 4 ; j++){
                curArr[j] = Integer.parseInt(st.nextToken());
            }

            people[curNum] = curArr;

            int rX = 500;
            int rY = 500;
            int rC = -1;
            int rE = -1;

            for(int r = 1 ; r <= n ; r++){
                for(int c = 1 ; c <= n ; c++){

                    if(board[r][c] != 0)
                        continue;

                    int tmpC = 0;
                    int tmpE = 0;

                    for(int k = 0 ; k < 4 ; k++){
                        int nx = r + dx[k];
                        int ny = c + dy[k];

                        if(isOut(nx,ny))
                            continue;

                        if(find(board[nx][ny],curArr)){
                            tmpC++;
                        }else if(board[nx][ny] == 0){
                            tmpE++;
                        }
                    }

                    if(tmpC > rC){
                        rC = tmpC;
                        rX = r;
                        rY = c;
                        rE = tmpE;
                    }else if(tmpC == rC){
                        if(tmpE > rE){
                            rC = tmpC;
                            rX = r;
                            rY = c;
                            rE = tmpE;
                        }else if(tmpE == rE){
                            if(rX > r){
                                rC = tmpC;
                                rX = r;
                                rY = c;
                                rE = tmpE;
                            }else if(rX == r){
                                if(rY > c){
                                    rC = tmpC;
                                    rX = r;
                                    rY = c;
                                    rE = tmpE;
                                }
                            }
                        }
                    }
                }
            }

            board[rX][rY] = curNum;
        }

        int result = 0;

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                result += getPoint(i,j);
            }
        }

        System.out.println(result);
        br.close();
    }

    static int[] score = {0,1,10,100,1000};

    static int getPoint(int x,int y){

        int curNum = board[x][y];
        int count = 0;
        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isOut(nx,ny))
                continue;

            if(find(board[nx][ny],people[curNum])){
                count++;
            }
        }

        return score[count];
    }

    static void print(){
        for(int i =1 ; i <= n ;i++){
            for(int j =1; j <=n ; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static boolean find(int cur, int[] arr){
        for(int i = 0 ; i < 4 ; i ++){
            if(cur == arr[i])
                return true;
        }

        return false;
    }
}
