package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_28471_W키가_빠진_성원이 {

    static int[] dx = {0,0,-1,1,1,-1,-1};
    static int[] dy = {1,-1,0,1,-1,1,-1};

    static int[][] board;
    static boolean[][] v;

    static int n,sx,sy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        board = new int[n+1][n+1];
        v = new boolean[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {
            char[] line = br.readLine().toCharArray();

            for(int j = 1 ; j <= n ; j++) {
                if(line[j-1] == '#') {
                    board[i][j] = -1;
                }else if(line[j-1] == 'F') {
                    board[i][j] = '1';

                    sx = i;
                    sy = j;
                }
            }
        }

        int count = 0;
        Queue<int[]> q = new ArrayDeque();
        q.add(new int[]{sx,sy});
        v[sx][sy] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i = 0 ; i < 7 ; i ++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(isOut(nx,ny) || v[nx][ny] || board[nx][ny] != 0)
                    continue;

                v[nx][ny] = true;
                count++;
                q.add(new int[]{nx,ny});
            }
        }

        System.out.println(count);

        br.close();
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }
}
