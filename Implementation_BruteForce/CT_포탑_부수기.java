package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_포탑_부수기 {

    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int[][] board;
    static int[][] recentAttack;

    static boolean[][] v;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static int[] d2x = {0,0,0,-1,1,1,1,-1,-1};
    static int[] d2y = {0,1,-1,0,0,1,-1,1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        recentAttack = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= m ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= k ; i++){

            if(!keep())
                break;

            int[] tmpA = findAttack(i);

            if(tmpA[0] == -1 || tmpA[1] == -1)
                break;

            int[] tmpB = findShoot(tmpA[0],tmpA[1]);

            if(!attackR(tmpA[0],tmpA[1],tmpB[0],tmpB[1])){
                attackB(tmpA[0],tmpA[1],tmpB[0],tmpB[1]);
            }

            secure();

        }

        System.out.println(findMax());

        br.close();
    }

    static int findMax(){
        int maxNum = 0;
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                maxNum = Math.max(maxNum,board[i][j]);
            }
        }

        return maxNum;
    }

    static boolean keep(){

        int count = 0;

        for(int i = 1 ; i <= n ; i++){
            for(int j =1 ; j <=m ; j++){
                if(board[i][j] >= 1)
                    count++;
            }
        }

        return count > 1;
    }

    static void secure(){
        for(int i = 1 ; i <= n ; i++){
            for(int j =1 ; j <= m ; j++){

                if(board[i][j] == 0)
                    continue;

                if(v[i][j])
                    continue;

                board[i][j] += 1;
            }
        }
    }

    static void print(int[][] arr){
        for(int i = 1 ; i <= n ; i++){
            for(int j =1 ; j <=m ; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] findShoot(int x,int y){

        int max = 0;
        int rx = -1;
        int ry = -1;
        int rA = 0;

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1; j <= m ; j++){
                if(board[i][j] == 0 || (x == i && y == j))
                    continue;

                if(max < board[i][j]){
                    max = board[i][j];
                    rx = i;
                    ry = j;
                    rA = recentAttack[i][j];
                }else if(max == board[i][j]){
                    if(rA > recentAttack[i][j]){
                        rx = i;
                        ry = j;
                        rA = recentAttack[i][j];
                    }else if(rA == recentAttack[i][j]){
                        if(rx + ry > i + j){
                            rx = i;
                            ry = j;
                            rA = recentAttack[i][j];
                        }else if(rx + ry == i + j){
                            if(ry > j){
                                rx = i;
                                ry = j;
                                rA = recentAttack[i][j];
                            }
                        }
                    }
                }
            }
        }

        return new int[]{rx,ry};
    }

    static int[] findAttack(int turn){

        int x = -1;
        int y = -1;
        int count = 0;
        int minValue = 987654321;
        int minR = 0;

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(board[i][j] == 0)
                    continue;

                count++;

                if(minValue > board[i][j]){
                    minValue = board[i][j];
                    x = i;
                    y = j;
                    minR = recentAttack[i][j];
                }else if(minValue == board[i][j]){
                    if(minR < recentAttack[i][j]){
                        x = i;
                        y = j;
                        minR = recentAttack[i][j];
                    }else if(minR == recentAttack[i][j]){
                        if((x+y) < (i+j)){
                            x = i;
                            y = j;
                        }else if((x+y) == (i+j)){
                            if(y < j){
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }
        }

        if(count == 1){
            return new int[]{-1,-1};
        }

        board[x][y] += (n+m);
        recentAttack[x][y] = turn;

        return new int[]{x,y};
    }

    static boolean attackR(int sx,int sy,int lx,int ly){
        v = new boolean[n+1][m+1];
        Queue<Node> q = new ArrayDeque<>();
        Node start = new Node(sx,sy);
        q.add(start);
        v[sx][sy] = true;

        boolean find = false;
        Node result = null;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == lx && cur.y == ly){
                find = true;
                result = cur;
                break;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = moveX(cur.x + dx[i]);
                int ny = moveY(cur.y + dy[i]);

                if(board[nx][ny] == 0)
                    continue;

                if(!v[nx][ny]){
                    v[nx][ny] = true;
                    Node next = new Node(nx,ny);
                    next.list.addAll(cur.list);
                    next.list.add(new int[]{nx,ny});
                    q.add(next);
                }
            }
        }

        if(find){
            int damage = board[sx][sy];
            v = new boolean[n+1][m+1];
            v[sx][sy] = true;


            for(int[] next : result.list){
                int nX = next[0];
                int nY = next[1];

                v[nX][nY] = true;

                if(nX == sx && nY == sy)
                    continue;

                int nD = (nX == lx && nY == ly) ? damage : damage /2;

                board[nX][nY] -= nD;

                if(board[nX][nY] < 0)
                    board[nX][nY] = 0;
            }
        }


        return find;
    }

    static void attackB(int sx,int sy,int lx,int ly){
        v = new boolean[n+1][m+1];

        v[sx][sy] = true;

        int damage = board[sx][sy];

        for(int i = 0 ; i < 9 ; i++){
            int nx = moveX(lx + d2x[i]);
            int ny = moveY(ly + d2y[i]);

            v[nx][ny] = true;

            if(nx == sx && ny == sy)
                continue;

            board[nx][ny] -= (lx == nx && ly == ny) ? damage : damage / 2;

            if(board[nx][ny] < 0)
                board[nx][ny] = 0;
        }
    }

    static int moveX(int x){
        if(x < 1)
            return n;

        else if(x > n)
            return 1;

        return x;
    }

    static int moveY(int y){
        if(y < 1)
            return m;

        else if(y > m)
            return 1;

        return y;
    }

    static class Node{
        int x;
        int y;
        ArrayList<int[]> list;

        Node(int x,int y){
            this.x = x;
            this.y = y;
            this.list = new ArrayList<>();
        }
    }
}
