package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_루돌프의_반란 {

    static int n,m,p,c,d,lx,ly;
    static Point[] santa;
    static int[] point;
    static int[] stun;
    static int[] rdx = {0,0,-1,1,1,1,-1,-1};
    static int[] rdy = {1,-1,0,0,1,-1,1,-1};
    static int[][] board;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        lx = Integer.parseInt(st.nextToken());
        ly = Integer.parseInt(st.nextToken());

        santa = new Point[p+1];
        point = new int[p+1];
        stun = new int[p+1];
        board = new int[n+1][n+1];

        for(int i = 1 ; i <= p ; i ++){
            st = new StringTokenizer(br.readLine()," ");
            int num = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            santa[num] = new Point(x,y);
            board[x][y] = num;
        }

        for(int i = 1 ; i <= m ; i++) {
            int rDir = dolfMoveDir();

            if(rDir == -1)
                break;

            lx += rdx[rDir];
            ly += rdy[rDir];


            if (board[lx][ly] >= 1) {
                force(true, board[lx][ly], rDir);
            }


            santaMove();

            for (int j = 1; j <= p; j++) {
                if (stun[j] > 0) {
                    stun[j]--;
                }

                if (!isOut(santa[j].x, santa[j].y)) {
                    point[j]++;
                }
            }

        }

        for(int i = 1 ; i <= p ; i++){
            sb.append(point[i]+" ");
        }
        System.out.println(sb);

        br.close();
    }

    static void print(){
        for(int i= 1; i <= n ;i++){
            for(int j= 1; j <=n ; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int reverseDir(int cur){
        if(cur == 0)
            return 2;

        if(cur == 1)
            return 3;

        if(cur == 2)
            return 0;

        return 1;
    }

    static void force(boolean isRudolf,int idx,int dir){

        Point cur = santa[idx];
        stun[idx] = 2;
        int tmpX = cur.x;
        int tmpY = cur.y;

        int nx = 0;
        int ny = 0;
        if(isRudolf){
            point[idx] += c;

            nx = cur.x + (c * rdx[dir]);
            ny = cur.y + (c * rdy[dir]);

            santa[idx].x = nx;
            santa[idx].y = ny;
        }else{
            point[idx] += d;

            dir = reverseDir(dir);

            nx = cur.x + (d * dx[dir]);
            ny = cur.y + (d * dy[dir]);

            santa[idx].x = nx;
            santa[idx].y = ny;
        }

        int num = board[tmpX][tmpY];
        board[tmpX][tmpY] = 0;

        if(isOut(nx,ny))
            return;

        if(board[nx][ny] >= 1){
            kcante(isRudolf,nx,ny,dir);
        }

        board[nx][ny] = num;
    }

    static void kcante(boolean isRudolf,int x,int y,int dir){

        int px = x;
        int py = y;
        int nx = x;
        int ny = y;
        int idx = board[nx][ny];

        while(idx >= 1) {
            if (isRudolf) {
                nx = px + rdx[dir];
                ny = py + rdy[dir];
            } else {
                nx = px + dx[dir];
                ny = py + dy[dir];
            }

            if (isOut(nx, ny)) {
                santa[idx].x = nx;
                santa[idx].y = ny;
                break;
            }

            if (board[nx][ny] == 0) {
                board[nx][ny] = idx;
                santa[idx].x = nx;
                santa[idx].y = ny;
                break;
            }

            int nextNum = board[nx][ny];
            board[nx][ny] = idx;
            santa[idx].x = nx;
            santa[idx].y = ny;
            px = nx;
            py = ny;
            idx = nextNum;
        }
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static void santaMove(){

        for(int i = 1 ; i <= p ; i++){
            Point cur = santa[i];

            if(isOut(cur.x,cur.y) || stun[i] > 0)
                continue;

            int dir = -1;

            int curDis = calDis(cur.x,cur.y,lx,ly);

            for(int k = 0 ; k < 4 ; k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(isOut(nx,ny))
                    continue;

                if(board[nx][ny] >= 1)
                    continue;

                int tmpDis = calDis(nx,ny,lx,ly);

                if(tmpDis >= curDis) {
                    continue;
                }

                curDis = tmpDis;
                dir = k;
            }

            if(dir != -1){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                board[nx][ny] = i;
                board[cur.x][cur.y] = 0;
                santa[i].x = nx;
                santa[i].y = ny;

                if(lx == nx && ly == ny){
                    force(false,board[nx][ny],dir);
                }
            }
        }

    }
    static int dolfMoveDir(){
        int idx = 0;
        int dis = Integer.MAX_VALUE;

        for(int i = 1; i <= p ; i++){
            Point tmp = santa[i];

            if(isOut(tmp.x , tmp.y)){
                continue;
            }
            int tmpDis = calDis(tmp.x,tmp.y,lx,ly);

            if(dis > tmpDis){
                dis = tmpDis;
                idx = i;
            }else if(dis == tmpDis){
                if(santa[idx].x < tmp.x){
                    idx = i;
                }else if(santa[idx].x == tmp.x){
                    if(santa[idx].y < tmp.y){
                        idx = i;
                    }
                }
            }
        }

        if(idx == 0){
            return -1;
        }

        int dir = 0;
        int tmpDis = dis;

        for(int i = 0 ; i < 8 ; i ++){
            int nx = lx + rdx[i];
            int ny = ly + rdy[i];

            if(isOut(nx,ny))
                continue;

            int tmp2Dis = calDis(nx,ny,santa[idx].x,santa[idx].y);

            if(tmpDis > tmp2Dis){
                tmpDis = tmp2Dis;
                dir = i;
            }
        }
        return dir;
    }
    static int calDis(int x,int y,int nx,int ny){
        return (x - nx) * (x - nx) + (y - ny) * (y - ny);
    }
}
class Point{
    int x;
    int y;

    Point(int x,int y){
        this.x = x;
        this.y = y;
    }
}
