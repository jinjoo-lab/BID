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
    static int[][] tmpV;
    static Queue<Node> q = new ArrayDeque<>();

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

                if(board[i][j] != 0) {
                    q.add(new Node(i,j));
                }
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

        int sec = 0;

        if(gNum == 1) {
            System.out.println(0);
        }else {
            while (true) {
                boolean tmp = go();
                sec++;

                if (tmp)
                    break;
            }

            System.out.println(sec);
        }
        br.close();
    }

    static boolean go() {
        tmpV = new int[n+1][m+1];

        int size = q.size();

        while(size != 0) {
            Node cur = q.poll();
            spread(cur.x,cur.y,speed[v[cur.x][cur.y]]);
            size--;
        }

        copy();

        HashSet<Integer> set = new HashSet<>();
        for(int i = 1 ; i <= gNum ; i++) {
            set.add(find(i));
        }

        return set.size() == 1;
    }


    static void printParent() {
        for(int i = 1 ; i <= gNum ; i++) {
            System.out.print(find(i)+" ");
        }
        System.out.println();
    }

    static void copy() {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                if(v[i][j] == 0)
                    v[i][j] = tmpV[i][j];
            }
        }
    }

    static void spread(int x,int y,int d) {

        int curNum = speed[v[x][y]];

        for(int i = -d ; i <= d ; i++) {
            for(int j = -d ; j <= d ; j++) {
                int nx = x + i;
                int ny = y + j;

                if(!isOut(nx,ny) && board[nx][ny] != 0 && curNum >= speed[v[nx][ny]]) {
                    board[nx][ny] = Math.max(curNum,board[nx][ny]);
                    q.add(new Node(nx,ny));
                }
            }
        }

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

    static void print(int[][] v) {
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

    static class Node {
        int x;
        int y;

        Node(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }
}
