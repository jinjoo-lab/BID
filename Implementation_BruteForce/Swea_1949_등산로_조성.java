package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_1949_등산로_조성 {
    static int t = 0;
    static int n = 0;

    static int k = 0;
    static int max = 0;
    static int[][] board;

    static boolean[][] v;

    static int result = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for (int a = 1; a <= t; a++) {

            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            board = new int[n+1][n+1];
            v = new boolean[n+1][n+1];
            max = 0;
            result = 0;

            for(int i = 1;  i <= n ; i++){
                st = new StringTokenizer(br.readLine(), " ");

                for(int j = 1 ; j<= n ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());

                    max = Math.max(max, board[i][j]);
                }
            }


            for(int i =1 ; i <=n ; i++){
                for(int j = 1 ; j <= n ; j ++){
                    if(board[i][j] == max){
                        v[i][j] = true;
                        dfs(i,j,true,1);
                        v[i][j] = false;
                    }
                }
            }

            sb.append("#" + a + " "+result+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void dfs(int x,int y,boolean keep,int count){

        result = Math.max(result,count);

        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 1 || nx > n || ny < 1 || ny > n)
                continue;

            if(v[nx][ny])
                continue;

            if(board[nx][ny] < board[x][y]){
                v[nx][ny] = true;
                dfs(nx,ny,keep,count + 1);
                v[nx][ny] = false;
            }else if(keep){
                for(int j = 1 ; j <= k ; j++){
                    if(board[nx][ny] - j < board[x][y]){
                        board[nx][ny] -= j;
                        v[nx][ny] = true;
                        dfs(nx,ny,false,count + 1);
                        v[nx][ny] = false;
                        board[nx][ny] += j;
                    }
                }
            }
        }
    }

}

