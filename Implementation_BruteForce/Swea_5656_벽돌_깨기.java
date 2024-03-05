package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_5656_벽돌_깨기 {

    static int result;
    static int t = 0;
    static int k = 0;
    static int n = 0;
    static int m = 0;

    static int[][] board;

    static int[][] tmpBoard;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[] shot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine(), " ");
            k = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            board = new int[n+1][m+1];
            tmpBoard = new int[n+1][m+1];

            for(int i =1 ; i <= n ; i++){
                st = new StringTokenizer(br.readLine(), " ");

                for(int j =1 ; j <= m ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());

                    if(board[i][j] >= 1){
                        result += 1;
                    }
                }
            }

            shot = new int[k];
            per(0);
            sb.append("#"+a+" "+result+"\n");
        }


        System.out.print(sb);
        br.close();
    }

    static int count(){

        int tmpResult = 0;

        for(int i =1 ; i <= n ; i++){
            for(int j=1 ; j<=m ; j++){
                if(tmpBoard[i][j] != 0)
                    tmpResult += 1;
            }
        }

        return tmpResult;
    }

    static void per(int depth){
        if(depth == k){
            go();
            return;
        }

        for(int i = 0; i < m ; i++){
            shot[depth] = i + 1;
            per(depth + 1);
        }
    }

    static void go(){
        arrCopy();
        Queue<int[]> q = new ArrayDeque<>();

        for(int idx = 0 ; idx < k ; idx++) {
            int curCol = shot[idx];
            int curRow = 0;
            for (int i = 1; i <= n; i++) {
                if (tmpBoard[i][curCol] != 0) {
                    curRow = i;
                    break;
                }
            }
            if (curRow == 0)
                continue;

            boolean[][] v = new boolean[n + 1][m + 1];
            v[curRow][curCol] = true;
            q.add(new int[]{curRow, curCol});

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = tmpBoard[cur[0]][cur[1]];
                tmpBoard[cur[0]][cur[1]] = 0;

                for (int k = 1; k <= r - 1; k++) {
                    for (int i = 0; i < 4; i++) {
                        int nx = cur[0] + k * dx[i];
                        int ny = cur[1] + k * dy[i];

                        if(nx < 1 || nx > n || ny < 1 || ny > m)
                            continue;

                        if(tmpBoard[nx][ny] == 0)
                            continue;

                        if (!v[nx][ny]) {
                            v[nx][ny] = true;
                            q.add(new int[]{nx, ny});
                        }
                    }
                }
            }

            force();
        }

        result = Math.min(result,count());
    }

    static void force(){
        for(int j = 1 ; j <= m ; j++){
            for(int i = n ; i >= 1 ; i--){
                if(tmpBoard[i][j] != 0)
                    continue;

                int tmpIdx = i - 1;

                for(int k = tmpIdx ; k >= 1 ; k--){
                    if(tmpBoard[k][j] != 0){
                        tmpBoard[i][j] = tmpBoard[k][j];
                        tmpBoard[k][j] = 0;
                        break;
                    }
                }
            }
        }
    }

    static void print(){
        for(int i =1 ; i <= n ; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(tmpBoard[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void arrCopy(){
        for(int i =1 ; i <= n ; i++) {
            for (int j = 1; j <= m; j++) {
                tmpBoard[i][j] = board[i][j];
            }
        }
    }
}
