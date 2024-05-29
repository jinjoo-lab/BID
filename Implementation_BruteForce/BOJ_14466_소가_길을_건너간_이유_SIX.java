package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_14466_소가_길을_건너간_이유_SIX {

    static int n,k,r;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] cow;
    static ArrayList<Point> cross[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        cow = new int[k+1][2];
        cross = new ArrayList[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                cross[i][j] = new ArrayList<>();
            }
        }

        for(int i = 1 ; i <= r ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            cross[x1][y1].add(new Point(x2,y2));
            cross[x2][y2].add(new Point(x1,y1));
        }

        for(int i = 1 ; i <= k ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            cow[i][0] = x;
            cow[i][1] = y;
        }

        int tmpSum = 0;

        for(int i = 1 ; i <= k ; i++) {
            tmpSum += findFirst(i);
        }

        tmpSum /= 2;

        System.out.println(tmpSum);

        br.close();
    }

    static int findFirst(int idx) {
        boolean[][] v = new boolean[n+1][n+1];
        Queue<Point> q = new ArrayDeque<>();
        v[cow[idx][0]][cow[idx][1]] = true;
        q.add(new Point(cow[idx][0],cow[idx][1]));

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny) || v[nx][ny])
                    continue;

                boolean canGo = true;

                for(Point next : cross[cur.x][cur.y]) {
                    if(next.x == nx && next.y == ny) {
                        canGo = false;
                        break;
                    }
                }

                if(canGo) {
                    v[nx][ny] = true;
                    q.add(new Point(nx,ny));
                }
            }
        }

        int count = 0;

        for(int i = 1 ; i <= k ; i++) {

            if(i == idx)
                continue;

            int curX = cow[i][0];
            int curY = cow[i][1];

            if(!v[curX][curY])
                count++;
        }

        return count;
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static class Point {
        int x;
        int y;

        Point(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void print(boolean[][] v) {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(v[i][j])
                    System.out.print(1+" ");

                else
                    System.out.print(0+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
