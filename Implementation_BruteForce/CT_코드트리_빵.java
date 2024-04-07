package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_코드트리_빵 {

    static int n,m;
    static int[][] board;

    static boolean[][] already;

    static boolean[][] tmpBoard;
    static Node[] destination;

    static boolean[] out;
    static Node[] people;

    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        already = new boolean[n+1][n+1];
        tmpBoard = new boolean[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        out = new boolean[m+1];
        destination = new Node[m+1];
        people = new Node[m+1];

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            destination[i] = new Node(x,y,0);
            people[i] = new Node(-1,-1,0);
        }

        int sec = 1;

        while(true){
            for(int i = 1 ; i <= m ; i++){
                if(people[i].x != -1 && people[i].y != -1){
                    if(!out[i] && !isClear(i,people[i].x,people[i].y)) {
                        move(i);
                    }
                }else{
                    break;
                }

            }

            copy();

            if(sec <= m ){
                findCamp(sec);
               // System.out.println(sec+" : "+people[sec].x+" "+people[sec].y);
            }

            //System.out.println();

            copy();

            if(isOver())
                break;

            //print(already);
            sec++;
        }


        System.out.println(sec);
        br.close();
    }

    static boolean isOver(){
        for(int i=  1; i <= m ; i++){
            if(!out[i])
                return false;
        }

        return true;
    }

    static boolean isClear(int idx, int x,int y){
        if(x == destination[idx].x && y == destination[idx].y)
            return true;

        return false;
    }

    static void copy(){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                already[i][j] = already[i][j] || tmpBoard[i][j];
            }
        }
    }

    static void move(int idx){
        int curX = people[idx].x;
        int curY = people[idx].y;
        boolean[][] v = new boolean[n+1][n+1];
        Queue<Point> q = new ArrayDeque<>();
        v[curX][curY] = true;

        for(int i = 0 ; i < 4 ; i++) {
            int nx = curX + dx[i];
            int ny = curY + dy[i];

            if (isOut(nx, ny))
                continue;

            if (already[nx][ny])
                continue;

            if(isClear(idx,nx,ny)){
                out[idx] = true;
                people[idx].x = nx;
                people[idx].y = ny;
                tmpBoard[nx][ny] = true;
                return;
            }

            v[nx][ny] = true;
            q.add(new Point(nx,ny,i,0));
        }

        int rC = 10001;
        int rD = 100;

        while(!q.isEmpty()){
            Point cur = q.poll();

            if(isClear(idx,cur.x,cur.y)){
                if(rC > cur.c){
                    rC = cur.c;
                    rD = cur.d;
                }else if(rC == cur.c){
                    rD = Math.min(rD,cur.d);
                }

                continue;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny))
                    continue;

                if(already[nx][ny] || v[nx][ny])
                    continue;

                v[nx][ny] = true;
                q.add(new Point(nx,ny,cur.d,cur.c + 1));
            }
        }

        people[idx].x += dx[rD];
        people[idx].y += dy[rD];
    }

    static void findCamp(int idx){
        int rX = -1;
        int rY = -1;
        int rd = 10001;

        Queue<Node> q = new ArrayDeque<>();
        boolean[][] v = new boolean[n+1][n+1];

        int curX = destination[idx].x;
        int curY = destination[idx].y;

        v[curX][curY] = true;
        q.add(new Node(curX,curY,0));

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(board[cur.x][cur.y] == 1){
                if(rd > cur.c){
                    rd = cur.c;
                    rX = cur.x;
                    rY = cur.y;
                }else if(rd == cur.c){
                    if(rX > cur.x){
                        rd = cur.c;
                        rX = cur.x;
                        rY = cur.y;
                    }else if(rX == cur.x){
                        if(rY > cur.y){
                            rd = cur.c;
                            rX = cur.x;
                            rY = cur.y;
                        }
                    }
                }
                continue;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny))
                    continue;

                if(already[nx][ny] || v[nx][ny])
                    continue;

                v[nx][ny] = true;
                q.add(new Node(nx,ny,cur.c + 1));
            }
        }

        people[idx] = new Node(rX,rY,0);
        tmpBoard[rX][rY] = true;
    }

    static void print(boolean[][] v){
        for(int i = 1 ; i<= n ; i++){
            for(int j =1 ;j <=n ; j++){
                if(v[i][j])
                    System.out.print(1+" ");
                else
                    System.out.print(0+" ");
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

    static class Point{
        int x;
        int y;
        int d;
        int c;

        Point(int x,int y,int d,int c){
            this.x = x;
            this.y = y;
            this.d = d;
            this.c = c;
        }
    }
    static class Node{
        int x;
        int y;

        int c;
        Node(int x,int y,int c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
