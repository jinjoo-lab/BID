package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_메이즈_러너 {

    static int n = 0;

    static int m = 0;
    static int k = 0;

    static int[][] board;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    static Point[] p;

    static int result = 0;
    static int lx,ly;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i = 1; i <= n ; i++){
            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 1 ; j <= n ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        p = new Point[m+1];
        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            p[i] = new Point(x,y,false);
        }

        st = new StringTokenizer(br.readLine(), " ");
        lx = Integer.parseInt(st.nextToken());
        ly = Integer.parseInt(st.nextToken());
        board[lx][ly] = -1;

        for(int i = 1 ; i <= k ; i++){

            if(allOut())
                break;

            move();
            turn();

        }

        System.out.println(result);
        System.out.println(lx+" "+ly);
        br.close();
    }

    static boolean allOut(){
        for(int i = 1 ; i <= m ; i++){
            if(p[i].out)
                continue;

            return false;
        }

        return true;
    }

    static void print(){
        for(int i = 1 ; i<=n ; i++){
            for(int j =1 ; j<=n ; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static int calDir(int idx){
        int curX = p[idx].x;
        int curY = p[idx].y;

        int tmpDis = calDis(curX,curY,lx,ly);
        int rIdx = -1;

        for(int i = 0 ; i < 4 ; i++){
            int nx = curX + dx[i];
            int ny = curY + dy[i];

            if(isOut(nx,ny) || board[nx][ny] >= 1)
                continue;

            int tmp2Dis = calDis(nx,ny,lx,ly);

            if(tmpDis > tmp2Dis){
                tmpDis = tmp2Dis;
                rIdx = i;
            }
        }

        return rIdx;
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static void turn(){
        int[] tmp = findTurn();

        if(tmp[0] == -1 || tmp[1] == -1)
            return;

        int[][] tmpBoard = new int[n+1][n+1];

        int sx = tmp[0];
        int sy = tmp[1];

        int tmpX = sx;
        int tmpY = sy + tmp[2];

        boolean[] aMove = new boolean[m+1];

        for(int i = sx ; i <= sx + tmp[2] ; i++){
            for(int j = sy ; j <= sy + tmp[2] ; j++){
                tmpBoard[tmpX][tmpY] = board[i][j];

                if(tmpBoard[tmpX][tmpY] >= 1)
                    tmpBoard[tmpX][tmpY] -= 1;

                for(int k = 1 ; k <= m ; k++){
                    if(aMove[k])
                        continue;

                    if(findPeople(i,j,k)){
                        aMove[k] = true;
                        p[k].x = tmpX;
                        p[k].y = tmpY;
                    }
                }

                tmpX++;

                if(tmpX > sx + tmp[2]){
                    tmpX = sx;
                    tmpY--;
                }
            }
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){

                if(tmpBoard[i][j] == -1){
                    lx = i;
                    ly = j;
                }

                if(i >= sx && i <= sx + tmp[2] && j >= sy && j <= sy +tmp[2])
                    continue;

                tmpBoard[i][j] = board[i][j];
            }
        }

        board = tmpBoard;
    }

    static boolean findPeople(int x,int y,int idx){

        if(x == p[idx].x && y == p[idx].y)
            return true;

        return false;
    }

    static void move(){
        for(int i = 1 ; i <= m ; i++){
            if(p[i].out)
                continue;

            int tmpIdx = calDir(i);

            if(tmpIdx == -1)
                continue;

            int nx = p[i].x + dx[tmpIdx];
            int ny = p[i].y + dy[tmpIdx];

            p[i].x = nx;
            p[i].y = ny;

            result++;

            if(nx == lx && ny == ly){
                p[i].out = true;
            }
        }
    }

    static int[] findTurn(){

        int tx = -1;
        int ty = -1;
        int size = n*n;

        for(int i = 1; i <= n ; i++){
            for(int j = 1; j <= n ; j++){
                for(int k = 1 ; k < n ; k ++){
                    boolean tmp = canMove(i,j,k);

                    if(tmp){
                        if(size > k){
                            size = k;
                            tx = i;
                            ty = j;
                        }else if(size == k){
                            if(tx > i){
                                tx = i;
                                ty = j;
                            }else if(tx == i){
                                if(ty > j){
                                    tx = i;
                                    ty = j;
                                }
                            }
                        }

                        break;
                    }
                }
            }
        }

        return new int[]{tx,ty,size};
    }

    static boolean canMove(int x,int y,int idx){

        boolean findExit = false;
        boolean findPeople = false;

        for(int i = x ; i <= x + idx ; i++){
            for(int j = y ; j <= y + idx ; j++){
                if(i == lx && j == ly)
                    findExit = true;

                for(int k = 1 ; k <= m ; k++){
                    if(p[k].out)
                        continue;

                    if(i == p[k].x && j == p[k].y)
                        findPeople = true;
                }
            }
        }

        return findExit && findPeople;
    }

    static int calDis(int x,int y,int nx,int ny){
        return Math.abs(x - nx) + Math.abs(y - ny);
    }

    static class Point{
        int x;
        int y;

        boolean out;

        Point (int x,int y,boolean out){
            this.x = x;
            this.y = y;
            this.out = out;
        }
    }

}
