package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_2105_디저트_카페 {

    static int t = 0;
    static int n = 0;

    static boolean[] numArr;
    static boolean[][][][] v;
    static int[][] board;

    static int result = -1;

    static int sx;
    static int sy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken().trim());

            board = new int[n+1][n+1];

            for(int i = 1; i <= n ; i++){
                st = new StringTokenizer(br.readLine(), " ");

                for(int j = 1 ; j <= n ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken().trim());
                }
            }

            numArr = new boolean[101];
            v = new boolean[n+1][n+1][n+1][n+1];
            result = -1;

            for(int i =1 ; i <= n ; i++){
                for(int j= 1 ; j<=n ; j++){
                    for(int k = 1 ; k <= n ; k++){
                        for(int k2 = 1 ; k2 <= n ; k2++){
                            go(i,j,k,k2);
                        }
                    }
                }
            }

            sb.append("#"+a+" "+result+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int[] dx = {1,1,-1,-1};
    static int[] dy = {1,-1,-1,1};

    static boolean isOut(int x,int y){
        if(x < 1 || x > n || y < 1 || y > n)
            return true;

        return false;
    }
    static void go(int x,int y,int r1,int r2){
        int nx1 = x + (r1 * dx[0]);
        int ny1 = y + (r1 * dy[0]);

        if(isOut(nx1,ny1))
            return;

        int nx2 = nx1 + (r2 * (dx[1]));
        int ny2 = ny1 + (r2 * (dy[1]));

        if(isOut(nx2,ny2))
            return;

        int nx3 = nx2 + (r1 * dx[2]);
        int ny3 = ny2 + (r1 * dy[2]);

        if(isOut(nx3,ny3))
            return;

        numArr = new boolean[101];
        numArr[board[x][y]] = true;
        int count = 1;

        int nx = x;
        int ny = y;

        while(true){
            nx = nx + dx[0];
            ny = ny + dy[0];

            if(numArr[board[nx][ny]])
                return;

            numArr[board[nx][ny]] = true;
            count += 1;

            if(nx == nx1 && ny == ny1)
                break;
        }

        while(true){
            nx = nx + dx[1];
            ny = ny + dy[1];

            if(numArr[board[nx][ny]])
                return;

            numArr[board[nx][ny]] = true;
            count += 1;

            if(nx == nx2 && ny == ny2)
                break;
        }

        while(true){
            nx = nx + dx[2];
            ny = ny + dy[2];

            if(numArr[board[nx][ny]])
                return;

            numArr[board[nx][ny]] = true;
            count += 1;

            if(nx == nx3 && ny == ny3)
                break;
        }

        while(true){
            nx = nx + dx[3];
            ny = ny + dy[3];

            if(nx == x && ny == y)
                break;

            if(numArr[board[nx][ny]])
                return;

            numArr[board[nx][ny]] = true;
            count += 1;
        }

        result = Math.max(result,count);
    }

}
