package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_술래_잡기 {
    static int n,m,h,k;
    static int[][] board;
    static Node[] runner;

    // U R D L
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static int cx,cy,cIdx;
    static int[] cMove;

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];

        runner = new Node[m+1];
        for(int i = 1 ; i <= m ; i ++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            int d = t == 1 ? 1 : 2;

            // t == 1 ( 좌 우) R 1
            // t == 2 ( 상 하) D 2
            runner[i] = new Node(x,y,t,d,false);
        }

        for(int i = 1 ; i <= h ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1; // tree
        }

        cMove = new int[2*n*n + 2];

        makeMove();

        cx = (1 + n) / 2;
        cy = (1 + n) / 2;
        cIdx = 1;

        for(int turn = 1 ; turn <= k ; turn++){
            allMove();
            catcherMove(turn);
        }

        System.out.println(result);

        br.close();
    }
    static int nextNum(int cur){
        if(cur + 1 > (2*n*n -2))
            return 1;
        return cur + 1;
    }

    static void catcherMove(int turn){
        cx = cx + dx[cMove[cIdx]];
        cy = cy + dy[cMove[cIdx]];

        cIdx = nextNum(cIdx);

        int wD = cMove[cIdx];

        int count = 0;

        for(int i = 0 ; i <= 2 ; i++){
            int nx = cx + (i*dx[wD]);
            int ny = cy + (i*dy[wD]);

            if(isOut(nx,ny))
                break;

            if(board[nx][ny] == 1)
                continue;

            for(int j = 1 ; j <= m ; j++){
                if(runner[j].dead)
                    continue;

                if(runner[j].x == nx && runner[j].y == ny){
                    count++;
                    runner[j].dead = true;
                }
            }
        }

        result += (turn * count);
    }
    static void makeMove(){
        int limit = n * n - 1;

        // 1 1 2 2 3 3 4 4
        int tmpLimit = 1;
        int count = 0;
        int idx = 0;

        for(int i = 1 ; i <= limit ; i++){
            cMove[i] = idx;

            count++;

            if(count == tmpLimit){
                count = 0;
                idx = nextDir(idx);

                if(idx % 2 == 0){
                    tmpLimit += 1;
                }
            }
        }

        int tmpIdx= limit;

        for(int i = limit + 1 ; i <= limit * 2 ; i++){
            cMove[i] = reverseDir(cMove[tmpIdx]);
            tmpIdx--;
        }
    }

    static void printCmove(int limit){
        for(int i =1 ; i <= limit ; i++){
            System.out.print(cMove[i]+" ");
        }
        System.out.println();
    }
    static int nextDir(int curDir){
        if(curDir + 1 > 3)
            return 0;
        return curDir + 1;
    }
    // U R D L
    static int reverseDir(int cur){
        if(cur == 0)
            return 2;

        if(cur == 1)
            return 3;

        if(cur == 2)
            return 0;

        return 1;
    }
    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;
        return false;
    }
    static boolean calDis(int x,int y){
        int tmpDis = Math.abs(x - cx) + Math.abs(y - cy);

        return tmpDis <= 3;
    }
    static class Node{
        int x;
        int y;
        int t; // L - R or U - D
        int d;
        boolean dead;

        Node(int x,int y,int t,int d,boolean dead){
            this.x = x;
            this.y = y;
            this.t = t;
            this.d = d;
            this.dead = dead;
        }
    }

    static void allMove(){
        for(int i = 1 ; i <= m ; i++){
            if(runner[i].dead)
                continue;

            moveP(i);
        }
    }
    static void moveP(int idx){
        Node curP = runner[idx];

        if(!calDis(curP.x,curP.y))
            return;

        int nx = curP.x + dx[curP.d];
        int ny = curP.y + dy[curP.d];


        if(isOut(nx,ny)){
            // 격자를 벗어난 경우
            curP.d = reverseDir(curP.d); // 우선 방향 전환
            int n2x = curP.x + dx[curP.d];
            int n2y = curP.y + dy[curP.d];

            if(n2x == cx && n2y == cy){
                return;
            }else{
                curP.x = n2x;
                curP.y = n2y;
            }
        }else{
            // 격자를 나가지 않은 경우
            if(nx == cx && ny == cy) {
                // 술래가 있는 경우
                return;
            }else{
                curP.x = nx;
                curP.y = ny;
                return;
            }
        }
    }
}
