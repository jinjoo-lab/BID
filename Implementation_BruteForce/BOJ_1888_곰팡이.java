package Implementation_BruteForce;

import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class BOJ_1888_곰팡이 {
    static int[][] v;
    static int n,m;
    static int[][] board;
    static int[] root;
    static int gNum = 1;
    static int[] speed = new int[100_01];
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        v = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] arr = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = arr[j-1] - '0';
            }
        }

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                if(board[i][j] != 0 && v[i][j] == 0) {
                    makeGroup(i,j);
                    speed[gNum] = board[i][j];
                    gNum++;
                }
            }
        }

        root = new int[gNum];
        gNum -= 1;

        for(int i = 1 ; i <= gNum ; i++) {
            root[i] = i;
        }




        br.close();
    }

    static int find(int x) {
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y) {
        x = find(x);
        y = find(y);

        if(x < y) {
            root[y] = x;
        }else {
            root[x] = y;
        }
    }

    static void makeGroup(int x,int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,y});
        v[x][y] = gNum;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(isOut(nx,ny) || v[nx][ny] != 0)
                    continue;

                if(board[nx][ny] == board[x][y]) {
                    v[nx][ny] = gNum;
                    q.add(new int[]{nx,ny});
                }
            }
        }


    }

    static void print() {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > m)
            return true;

        return false;
    }
}
