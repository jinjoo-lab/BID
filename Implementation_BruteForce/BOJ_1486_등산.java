package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_1486_등산 {
    static int DIST_LIMIT = 52*52*25*25+1;
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int d = 0;

    static int[][] board;

    static int[][] tmpDis;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];
        tmpDis = new int[n+1][m+1];

        for(int i = 0 ; i < n ; i++){
            char[] arr = br.readLine().toCharArray();

            for(int j = 0 ; j < m ; j++){
                if(arr[j] >= 'A' && arr[j] <= 'Z'){
                    board[i][j] = arr[j] - 'A';
                }

                else{
                    board[i][j] = arr[j] - 'a' + 26;
                }
            }
        }

        int[][] tmp1 = bfs();
        int[][] tmp2 = bfs2();

        int result = 0;

        for(int i = 0 ; i <  n ; i++){
            for(int j = 0; j < m ; j++){

                if(tmp1[i][j] + tmp2[i][j] <= d){
                    result = Math.max(result,board[i][j]);
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    static int[][]  bfs(){
        PriorityQueue<int[]> q = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        boolean[][] v = new boolean[n][m];
        int[][] dis = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                dis[i][j] = DIST_LIMIT;
            }
        }

        dis[0][0] = 0;
        q.add(new int[]{0,0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(v[cur[0]][cur[1]])
                continue;

            v[cur[0]][cur[1]] = true;

            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if(Math.abs(board[nx][ny] - board[cur[0]][cur[1]]) > k)
                    continue;

                if(board[nx][ny] > board[cur[0]][cur[1]]){
                    int tmpDis = (board[nx][ny] - board[cur[0]][cur[1]]) * (board[nx][ny] - board[cur[0]][cur[1]]);

                    int next = cur[2] + tmpDis;

                    if(dis[nx][ny] > next){
                        dis[nx][ny] = next;
                        q.add(new int[]{nx,ny,dis[nx][ny]});
                    }

                }else{
                    if(dis[nx][ny] > cur[2] + 1) {
                        dis[nx][ny] = cur[2] + 1;
                        q.add(new int[]{nx,ny,dis[nx][ny]});
                    }
                }
            }
        }

        return dis;
    }

    static int[][] bfs2(){

        PriorityQueue<int[]> q = new PriorityQueue<>(
                (x,y) -> x[2] - y[2]
        );

        boolean[][] v = new boolean[n][m];
        int[][] dis = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                dis[i][j] = DIST_LIMIT;
            }
        }

        dis[0][0] = 0;
        q.add(new int[]{0,0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(v[cur[0]][cur[1]])
                continue;

            v[cur[0]][cur[1]] = true;


            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if(Math.abs(board[nx][ny] - board[cur[0]][cur[1]]) > k)
                    continue;

                if(board[nx][ny] < board[cur[0]][cur[1]]){
                    int tmpDis = (board[nx][ny] - board[cur[0]][cur[1]]) * (board[nx][ny] - board[cur[0]][cur[1]]);

                    int next = cur[2] + tmpDis;

                    if(dis[nx][ny] > next){
                        dis[nx][ny] = next;
                        q.add(new int[]{nx,ny,dis[nx][ny]});
                    }

                }else{
                    if(dis[nx][ny] > cur[2] + 1) {
                        dis[nx][ny] = cur[2] + 1;
                        q.add(new int[]{nx,ny,dis[nx][ny]});
                    }
                }
            }
        }

        return dis;
    }
}
