package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_싸움땅 {

    static int[] result;
    static int n,m,k;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static PriorityQueue<Integer>[][] board;

    static Node[] player;

    public static void main(String[] args) throws Exception {
        input();

        for(int a = 1 ; a <= k ; a++) {
            for (int i = 1; i <= m; i++) {
                move(i);
            }


        }
        printPoint();
    }

    static void print(){
        int[][] board = new int[n+1][n+1];

        for(int i = 1 ; i <= m ; i++){
            board[player[i].x][player[i].y] = i;
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j =1 ; j <= n ; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int findEnemy(int idx,int x,int y){
        int result = -1;

        for(int i = 1 ; i <= m ; i++){
            if(idx == i)
                continue;

            if(player[i].x == x && player[i].y == y)
                return i;
        }

        return result;
    }


    static void walk(int idx){
        int nx = player[idx].x + dx[player[idx].d];
        int ny = player[idx].y + dy[player[idx].d];
        int curD = player[idx].d;

        if(isOut(nx,ny)){
            curD = changeDir(curD);
            nx = player[idx].x + dx[curD];
            ny = player[idx].y + dy[curD];
        }

        player[idx].x = nx;
        player[idx].y = ny;
        player[idx].d = curD;
    }

    static void move(int idx) {
        // 이동
        walk(idx);
        int nx = player[idx].x;
        int ny = player[idx].y;

        int enemyNum = findEnemy(idx,nx,ny); // 적을 찾는다

        if(enemyNum == -1) {
            // 적이 없는 경우
            if(!board[nx][ny].isEmpty()){
                if(player[idx].g < board[nx][ny].peek()){

                    if(player[idx].g > 0) {
                        board[nx][ny].add(player[idx].g);
                    }
                    player[idx].g = board[nx][ny].poll();
                }
            }
        }else{
            // 적이 있는 경우
            int winner = fight(idx,enemyNum);
            int loser = idx + enemyNum - winner;

            loseMove(nx,ny,loser);

            if(!board[nx][ny].isEmpty()){
                if(player[winner].g < board[nx][ny].peek()){

                    if(player[winner].g > 0){
                        board[nx][ny].add(player[winner].g);
                    }
                    player[winner].g = board[nx][ny].poll();
                }
            }
        }
    }

    static void loseMove(int x,int y,int idx){
        if(player[idx].g > 0){
            board[x][y].add(player[idx].g);
            player[idx].g = 0;
        }

        int curD = player[idx].d;
        int nx = -1;
        int ny = -1;

        while(true){
            nx = x + dx[curD];
            ny = y + dy[curD];

            if(isOut(nx,ny) || findEnemy(idx,nx,ny) != -1){
                curD = nextDir(curD);
                continue;
            }

            player[idx].x = nx;
            player[idx].y = ny;
            player[idx].d = curD;

            if(!board[nx][ny].isEmpty()){
                player[idx].g = board[nx][ny].poll();
            }
            break;
        }
    }

    static void printPoint(){
        for(int i =1 ; i <= m ; i++){
            System.out.print(result[i]+" ");
        }
        System.out.println();
    }

    // 승자 반환
    static int fight(int idx1,int idx2){
        int sum1 = player[idx1].s + player[idx1].g;
        int sum2 = player[idx2].s + player[idx2].g;
        int plus = Math.abs(sum1 - sum2);


        if(sum1 < sum2){
            result[idx2] += plus;
            return idx2;
        }else if(sum1 > sum2){
            result[idx1] += plus;
            return idx1;
        }else if(sum1 == sum2){
            if(player[idx1].s > player[idx2].s){
                result[idx1] += plus;
                return idx1;
            }else if(player[idx1].s < player[idx2].s){
                result[idx2] += plus;
                return idx2;
            }
        }

        return -1;
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static int nextDir(int cur){
        if(cur + 1 > 3)
            return 0;

        return cur + 1;
    }
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new PriorityQueue[n+1][n+1];
        player = new Node[m+1];
        result = new int[m+1];

        for(int i = 1 ; i <= n ; i++){

            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++){
                board[i][j] = new PriorityQueue<>(
                        (x,y) -> y - x
                );

                int tmpNum = Integer.parseInt(st.nextToken());

                if(tmpNum != 0){
                    board[i][j].add(tmpNum);
                }
            }
        }

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            player[i] = new Node(x,y,d,s,0);
        }

        br.close();
    }

    static int changeDir(int cur){
        if(cur == 0)
            return 2;

        if(cur == 1)
            return 3;

        if(cur == 2)
            return 0;

        return 1;
    }
    static class Node{
        int x;
        int y;
        int d;
        int s;
        int g;

        Node(int x,int y,int d,int s,int g){
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.g = g;
        }
    }
}
