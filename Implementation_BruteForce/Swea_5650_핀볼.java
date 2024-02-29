package Implementation_BruteForce;

import java.util.*;
import java.io.*;
public class Swea_5650_핀볼 {

    static int t = 0;
    static int n = 0;
    static int[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[][][] v;

    static Node[][] holl = new Node[11][2];
    // R L U D

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++) {

            st = new StringTokenizer(br.readLine()," ");

            n = Integer.parseInt(st.nextToken());

            board = new int[n+2][n+2];
            v = new int[n+2][n+2][4];
            holl = new Node[11][2];

            for(int i = 1 ; i <= n ; i++) {
                st = new StringTokenizer(br.readLine()," ");

                for(int j = 1 ; j <= n ; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());

                    if(board[i][j] >= 6 && board[i][j] <= 10) {
                        if(holl[board[i][j]][0] == null) {
                            holl[board[i][j]][0] = new Node(i,j,0,0);
                        }else {
                            holl[board[i][j]][1] = new Node(i,j,0,0);
                        }
                    }
                }
            }


            result = 0;

            for(int i = 1 ; i <= n ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    for(int k = 0 ; k < 4 ; k++) {

                        if(board[i][j] == 0)
                            go(i,j,k);
                    }
                }
            }

            sb.append("#"+a+" "+result+"\n");

        }

        System.out.print(sb);
        br.close();
    }

    static void go(int x,int y,int d) {
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(x,y,d,0));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            int nx = cur.x + dx[cur.d];
            int ny = cur.y + dy[cur.d];

            if(nx == x && ny == y) {
                result = Math.max(result, cur.c);
                return;
            }


            if(nx < 1 || nx > n || ny < 1 || ny > n) {
                result = Math.max(result, (cur.c * 2) + 1);
                return;
            }


            if(board[nx][ny] == -1) {
                result = Math.max(result, cur.c);
                return;
            }


            if(board[nx][ny] == 0) {
                q.add(new Node(nx,ny,cur.d,cur.c));
            }

            else if(board[nx][ny] >= 1 && board[nx][ny] <= 5) {
                int nextD = changeDir(cur.d, board[nx][ny]);

                if(nextD == cur.d) {
                    result = Math.max(result, (cur.c * 2) + 1);
                    return;
                }

                q.add(new Node(nx,ny,nextD,cur.c + 1));
            }

            else if(board[nx][ny] >= 6 && board[nx][ny] <= 10) {
                int num = board[nx][ny];
                int nextNum = 0;

                if(holl[num][0].x == nx && holl[num][0].y == ny) {
                    nextNum = 1;
                }

                q.add(new Node(holl[num][nextNum].x,holl[num][nextNum].y,cur.d,cur.c));
            }

        }
    }

    // R L U D

    static int changeDir(int cur,int pos) {
        if(pos == 1) {
            if(cur == 1) {
                return 2;
            }else if(cur == 3) {
                return 0;
            }

            return cur;
        }else if(pos == 2) {
            if(cur == 1) {
                return 3;
            }else if(cur == 2) {
                return 0;
            }

            return cur;
        }else if(pos == 3) {
            if(cur == 0) {
                return 3;
            }else if(cur == 2) {
                return 1;
            }

            return cur;
        }else if(pos == 4) {
            if(cur == 0) {
                return 2;
            }else if(cur == 3) {
                return 1;
            }

            return cur;
        }else {
            return cur;
        }
    }

    static class Node{
        int x;
        int y;
        int d;
        int c;

        Node(int x,int y,int d,int c){
            this.x = x;
            this.y = y;
            this.d = d;
            this.c = c;
        }
    }
}
