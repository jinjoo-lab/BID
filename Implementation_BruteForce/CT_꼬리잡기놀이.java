package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_꼬리잡기놀이 {

    static int n,m,k;

    static int result = 0;
    static boolean[][] v;
    static int[][] arr;
    static int[][] turn;

    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n+1][n+1];
        v = new boolean[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // R U L D
        makeTurn();

        int idx = 1;
        for(int i = 1 ; i <= k ; i++){
            allMove();
            throwBall(idx);
            idx = nextTurn(idx);
        }
        System.out.println(result);

        br.close();
    }

    static void reverse(int x,int y){

        int[] one = new int[2];
        int[] three = new int[2];

        boolean[][] tmpV = new boolean[n+1][n+1];
        tmpV[x][y] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,y});
        if(arr[x][y] == 1){
            one[0] = x;
            one[1] = y;
        }else if(arr[x][y] == 3){
            three[0] = x;
            three[1] = y;
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(arr[cur[0]][cur[1]] == 1){
                one[0] = cur[0];
                one[1] = cur[1];
            }else if(arr[cur[0]][cur[1]] == 3){
                three[0] = cur[0];
                three[1] = cur[1];
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(isOut(nx,ny) || arr[nx][ny] == 0 || arr[nx][ny] == 4)
                    continue;

                if(tmpV[nx][ny])
                    continue;

                tmpV[nx][ny] = true;
                q.add(new int[]{nx,ny});
            }
        }

        arr[one[0]][one[1]] = 3;
        arr[three[0]][three[1]] = 1;
    }

    static int calCount(int x,int y){
        if(arr[x][y] == 1)
            return 1;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] tmpV = new boolean[n+1][n+1];
        tmpV[x][y] = true;
        q.add(new int[]{x,y,1});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(isOut(nx,ny) || arr[nx][ny] == 0 || arr[nx][ny] == 4 || arr[nx][ny] == 3)
                    continue;

                if(arr[cur[0]][cur[1]] == 3 && arr[nx][ny] == 1)
                    continue;

                if(tmpV[nx][ny])
                    continue;

                if(arr[cur[0]][cur[1]] == 2 && arr[nx][ny] == 1){
                    return cur[2] + 1;
                }

                tmpV[nx][ny] = true;
                q.add(new int[]{nx,ny,cur[2] + 1});
            }
        }

        return 0;
    }
    static void throwBall(int curTurn){
        int curDir = turn[curTurn][1];
        int curNum = turn[curTurn][0];

        if(curDir == 0){
            // R
            for(int j = 1 ; j <= n ; j++){
                if(arr[curNum][j] >= 1 && arr[curNum][j] < 4){

                    int tmpNum = calCount(curNum,j);

                    result += tmpNum * tmpNum;
                    reverse(curNum,j);
                    break;
                }
            }
        }else if(curDir == 1){
            // U
            for(int i = n ; i >= 1 ; i--){
                if(arr[i][curNum] >= 1 && arr[i][curNum] < 4){
                    int tmpNum = calCount(i,curNum);
                    result += tmpNum * tmpNum;
                    reverse(i,curNum);
                    break;
                }
            }
        }else if(curDir == 2){
            // L
            for(int j = n ; j >= 1 ; j--){
                if(arr[curNum][j] >= 1 && arr[curNum][j] < 4){
                    int tmpNum = calCount(curNum,j);
                    result += tmpNum * tmpNum;
                    reverse(curNum,j);
                    break;
                }
            }
        }else{
            // D
            for(int i = 1 ; i <= n ; i++){
                if(arr[i][curNum] >= 1 && arr[i][curNum] < 4){
                    int tmpNum = calCount(i,curNum);
                    result += tmpNum * tmpNum;
                    reverse(i,curNum);
                    break;
                }
            }
        }
    }

    static int nextTurn(int cur){
        if(cur + 1 > 4*n)
            return 1;

        return cur + 1 ;
    }

    static void printTurn(){
        for(int i = 1 ; i <= 4*n ; i++){
            System.out.print(turn[i][0]+" ");
        }
    }

    static void makeTurn(){
        turn = new int[4*n + 4][2];
        for(int i = 1 ; i <= n ; i++){
            turn[i][0] = i;
            turn[i][1] = 0;
        }

        for(int i = n + 1 ; i <= 2*n ; i++){
            turn[i][0] = i - n;
            turn[i][1] = 1;
        }

        int idx = n;
        for(int i = 2*n + 1 ; i <= 3*n ; i++){
            turn[i][0] = idx;
            turn[i][1] = 2;
            idx--;
        }

        idx = n;
        for(int i = 3*n + 1 ; i <= 4*n ; i++){
            turn[i][0] = idx;
            turn[i][1] = 3;
            idx--;
        }
    }

    static void allMove(){
        v = new boolean[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                if(!v[i][j] && arr[i][j] == 1){
                    move(i,j);
                }
            }
        }
    }

    static void move(int x,int y){
        v[x][y] = true;

        int tmpX = x;
        int tmpY = y;

        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isOut(nx,ny) || v[nx][ny])
                continue;

            if(arr[nx][ny] == 2){
                arr[x][y] = arr[nx][ny];
                tmpX = nx;
                tmpY = ny;
                v[nx][ny] = true;
                break;
            }
        }

        while(true){

            boolean find = false;

            for(int i = 0 ; i < 4 ; i++){
                int nx = tmpX + dx[i];
                int ny = tmpY + dy[i];

                if(isOut(nx,ny) || v[nx][ny] || arr[nx][ny] == 0)
                    continue;

                find = true;
                arr[tmpX][tmpY] = arr[nx][ny];
                tmpX = nx;
                tmpY = ny;
                v[nx][ny] = true;
                break;
            }

            if(!find){
                v[tmpX][tmpY] = true;
                arr[tmpX][tmpY] = 1;
                break;
            }

        }
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static void print(){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
