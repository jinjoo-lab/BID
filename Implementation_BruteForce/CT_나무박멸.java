package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_나무박멸 {

    static int n,m,k,c;

    static int result = 0;
    static int[][] tmpBoard;
    static int[][] tree; // 나무들
    static int[][] turn; // 제초제 지속 시간

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[] rx = {1,1,-1,-1};
    static int[] ry = {1,-1,1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        tree = new int[n+1][n+1];
        turn = new int[n+1][n+1];
        tmpBoard = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 1 ; j <= n ; j++){
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i <= m ; i++){
            grow();
            child(i);
            findMax(i);
        }

        System.out.println(result);
        print(turn);

        br.close();
    }

    static void init(){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                tmpBoard[i][j] = 0;
            }
        }
    }

    static void add(){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                tree[i][j] += tmpBoard[i][j];
            }
        }
    }

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static void print(int[][] arr){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void grow(){
        init(); // 임시 배열 초기화

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                if(tree[i][j] >= 1){

                    int count = 0;

                    for(int k = 0 ; k < 4 ; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(isOut(nx,ny))
                            continue;

                        if(tree[nx][ny] >= 1){
                            count++;
                        }
                    }

                    tmpBoard[i][j] += count;
                }
            }
        }

        //print(tree);

        add();

        //print(tree);
    }

    static void child(int curTurn){
        init(); // 임시 배열 초기화

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
                if(tree[i][j] >= 1){

                    int count = 0;

                    for(int k = 0 ; k < 4 ; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(isOut(nx,ny))
                            continue;

                        if(tree[nx][ny] == -1 || tree[nx][ny] >= 1)
                            continue;

                        if(turn[nx][ny] >= curTurn)
                            continue;

                        count++;
                    }

                    if(count == 0)
                        continue;

                    int plus = tree[i][j] / count;

                    for(int k = 0 ; k < 4 ; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(isOut(nx,ny))
                            continue;

                        if(tree[nx][ny] == -1 || tree[nx][ny] >= 1)
                            continue;

                        if(turn[nx][ny] >= curTurn)
                            continue;

                        tmpBoard[nx][ny] += plus;
                    }
                }
            }
        }

        //print(tree);

        add();

        //print(tree);
    }

    static void findMax(int curTurn){

        int max = -1;
        int rx = 101;
        int ry = 101;

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){
               if(tree[i][j] >= 1){
                   int tmpCount = count(i,j,curTurn);

                   if(max < tmpCount){
                       max = tmpCount;
                       rx = i;
                       ry = j;
                   }else if(max == tmpCount){
                       if(rx > i){
                           max = tmpCount;
                           rx = i;
                           ry = j;
                       }else if(rx == i){
                           if(ry > j){
                               max = tmpCount;
                               rx = i;
                               ry = j;
                           }
                       }
                   }
               }
            }
        }


        //print(tree);

        if(rx != 101 && ry != 101){
            kill(rx,ry,curTurn);
        }

        //print(tree);
    }

    static int count(int x,int y,int curTurn){
        boolean[] dir = new boolean[4];
        int count = tree[x][y];

        for(int idx = 1 ; idx <= k ; idx++){
            for(int i = 0 ; i < 4 ; i++){

                if(dir[i])
                    continue; // 이미 전에 뿌리기가 종료된 경우

                int nx = x + (idx*rx[i]);
                int ny = y + (idx*ry[i]);

                if(isOut(nx,ny) || tree[nx][ny] == -1){
                    dir[i] = true;
                    continue;
                }

                if(tree[nx][ny] == 0){
                    dir[i] = true;
                    continue;
                }

                if(tree[nx][ny] > 0){
                    count += tree[nx][ny];
                }
            }
        }

        return count;
    }

    static void kill(int x,int y,int curTurn){
        boolean[] dir = new boolean[4];
        result += tree[x][y];
        tree[x][y] = 0;
        turn[x][y] = curTurn + c;

        for(int idx = 1 ; idx <= k ; idx++){
            for(int i = 0 ; i < 4 ; i++){

                if(dir[i])
                    continue; // 이미 전에 뿌리기가 종료된 경우

                int nx = x + (idx*rx[i]);
                int ny = y + (idx*ry[i]);

                if(isOut(nx,ny) || tree[nx][ny] == -1){
                    dir[i] = true;
                    continue;
                }

                if(tree[nx][ny] == 0){
                    turn[nx][ny] = curTurn + c;
                    dir[i] = true;
                    continue;
                }

                if(tree[nx][ny] > 0){
                    result += tree[nx][ny];
                    tree[nx][ny] = 0;
                    turn[nx][ny] = curTurn + c;
                }
            }
        }
    }
}
