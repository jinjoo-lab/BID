package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class Swea_1767_프로세서_연결하기 {

        static int t = 0;
        static int n = 0;
        static int [][] board;

        static boolean[][] v;

        static Node[] core = new Node[14];

        static int[] dir;

        static int tmpCount = 0;
        static int count = 1;

        static int result = 0;

        static int resultLen = 14 * 14;

        static int[] dx = {0, 0, 0, -1, 1};
        static int[] dy = {0, -1, 1, 0, 0};

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            StringBuilder sb = new StringBuilder();
            t = Integer.parseInt(st.nextToken());

            for(int a = 1 ; a <= t ; a++){
                st = new StringTokenizer(br.readLine(), " ");
                n = Integer.parseInt(st.nextToken());

                v = new boolean[n+1][n+1];
                board = new int[n+1][n+1];
                count = 1;
                result = 0;
                resultLen = 14 * 14;

                for(int i = 1 ; i <= n ; i++){
                    st = new StringTokenizer(br.readLine(), " ");

                    for(int j = 1 ; j <= n ; j++){
                        board[i][j] = Integer.parseInt(st.nextToken());

                        if(board[i][j] == 1){
                            if(i > 1 && i < n && j > 1 && j < n){
                                core[count] = new Node(i,j);
                                count++;
                            }else{
                                tmpCount++;
                            }
                        }
                    }
                }

                count -= 1;
                dfs(1,0,0);
                sb.append("#"+a+" "+resultLen+"\n");
            }

            System.out.print(sb);
            br.close();
        }

        static void dfs(int idx,int c,int num){
            if(idx > count){
                if(result < num){
                    result = num;
                    resultLen = c;
                }else if(result == num){
                    resultLen = Math.min(resultLen,c);
                }
                return;
            }

            for(int i = 0 ; i < 5 ; i++){
                if(i == 0){
                    dfs(idx + 1 , c, num);
                    continue;
                }
                boolean tmpC = go(idx,i);
                if(tmpC){
                    int tmpCount = make(idx,i);
                    dfs(idx+1,c + tmpCount, num + 1);
                    back(idx,i,tmpCount);
                }
            }
        }

        static boolean go(int cur,int d){
            Node tmp = core[cur];
            int idx = 1;
            while(true){
                int nx = tmp.x + (idx * dx[d]);
                int ny = tmp.y + (idx * dy[d]);

                if(nx < 1 || ny < 1 || nx > n || ny > n){
                    return true;
                }

                if(v[nx][ny]){
                    return false;
                }

                if(board[nx][ny] == 1){
                    return false;
                }

                idx++;
            }
        }

        static int make(int cur,int d){
            Node tmp = core[cur];
            int idx = 1;
            int count = 0;

            while(true){
                int nx = tmp.x + (idx * dx[d]);
                int ny = tmp.y + (idx * dy[d]);

                if(nx < 1 || ny < 1 || nx > n || ny > n){
                    return count;
                }

                v[nx][ny] = true;
                idx++;
                count++;
            }
        }

        static void back(int cur,int d,int count){
            for(int i = 1 ; i <= count; i++){
                int nx = core[cur].x + (i * dx[d]);
                int ny = core[cur].y + (i * dy[d]);

                v[nx][ny] = false;
            }
        }
    static class Node{
        int x;
        int y;

        Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}

