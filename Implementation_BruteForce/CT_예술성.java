package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_예술성 {

    static int n;
    static int[][] board;
    static int[][] groupBoard;
    static int[][] groupStatus = new int[901][2];
    static int groupNum = 0;

    static int[][] groupR;

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        groupBoard = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++) {

            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= n ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int k = 1 ; k <= 4 ; k++) {
            findAllGroup();
            result += makeGroupR();
            turn();
        }

        System.out.println(result);

        br.close();
    }

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};


    static int makeGroupR() {
        int result = 0;
        groupR = new int[groupNum + 1][groupNum + 1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                int curG = groupBoard[i][j];

                for(int k = 0 ; k < 4 ; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(isOut(nx,ny))
                        continue;

                    if(groupBoard[nx][ny] == curG)
                        continue;

                    groupR[curG][groupBoard[nx][ny]] += 1;
                }
            }
        }

        for(int i = 1 ; i < groupNum ; i++) {
            for(int j = i + 1 ; j <= groupNum ; j++) {
                int tmpNum = (groupStatus[i][1] + groupStatus[j][1]) * groupStatus[i][0] * groupStatus[j][0] * groupR[i][j];
                result += tmpNum;
            }
        }

        return result;
    }

    static void init() {
        for(int i = 1 ; i <= 900 ; i++) {
            Arrays.fill(groupStatus[i], 0);
        }
    }

    static void findAllGroup() {

        init();

        groupNum = 0;
        groupBoard = new int[n+1][n+1];
        int idx = 1;

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                if(groupBoard[i][j] == 0) {
                    int tmpCount = findGroup(i,j,idx);
                    groupStatus[idx][0] = board[i][j];
                    groupStatus[idx][1] = tmpCount;
                    idx++;
                }
            }
        }

        groupNum = idx - 1;

        //printGroup();
    }

    static void turn() {
        int[][] tmpBoard = new int[n+1][n+1];

        // 반시계
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                tmpBoard[i][j] = board[j][n - i + 1];
            }
        }

        // 구간 시계
        semiTurn(tmpBoard,1,1);
        semiTurn(tmpBoard,1,n/2 + 2);
        semiTurn(tmpBoard,n/2 + 2,1);
        semiTurn(tmpBoard,n/2 + 2,n/2 + 2);

        board = tmpBoard;
    }

    static void semiTurn(int[][] tmpBoard,int sx,int sy) {
        for(int i = 0; i < n/2; i++) {
            for(int j = 0; j < n/2; j++) {
                tmpBoard[sx + j][sy + n/2 - i - 1] = board[sx + i][sy + j];
            }
        }
    }

    static int findGroup(int x,int y,int gNum) {

        int count = 1;

        Queue<Node> q = new ArrayDeque<>();
        groupBoard[x][y] = gNum;
        q.add(new Node(x,y));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(isOut(nx,ny) || groupBoard[nx][ny] != 0)
                    continue;

                if(board[nx][ny] == board[x][y]) {
                    groupBoard[nx][ny] = gNum;
                    q.add(new Node(nx,ny));
                    count++;
                }
            }
        }


        return count;
    }

    static boolean isOut(int x,int y) {
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }

    static void print(int[][] board) {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                System.out.print(board[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }

    static void printGroup() {
        for(int i = 1 ; i <= groupNum ; i++) {
            System.out.println(groupStatus[i][0]+" "+groupStatus[i][1]);
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
