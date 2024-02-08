package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ_2159_케익_배달 G2
// 2024_02_08
// time : 464 ms
// memory : 46028 KB

public class BOJ_2159_케익_배달 {
    static int[] dx = {0, 1, -1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    static int n = 0;

    static final long MAX = Long.MAX_VALUE;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());

        board = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int tmpX = Integer.parseInt(st.nextToken());
            int tmpY = Integer.parseInt(st.nextToken());

            board[i][0] = tmpX;
            board[i][1] = tmpY;
        }

        // dp[i][j] = i번째 배달 위치에서 (중앙 , 하 , 상 , 우 , 좌)에 대한 최소 거리
        long[][] dp = new long[n + 1][5];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 5; j++) {
                dp[i][j] = MAX;
            }
        }

        for (int i = 0; i < 5; i++) {

            int nx = board[1][0] + dx[i];
            int ny = board[1][1] + dy[i];

            if (outOfRange(nx, ny))
                continue;

            dp[1][i] = calDis(sx, sy, nx, ny); // 출발지에서 첫 좌표까지

        }


        for (int i = 2; i <= n; i++) {

            int curX = board[i][0];
            int curY = board[i][1];

            for (int j = 0; j < 5; j++) {

                int nX = curX + dx[j];
                int nY = curY + dy[j]; // i번째 위치의 중앙 , 하 , 상 , 우 , 좌 위치

                if (outOfRange(nX, nY))
                    continue; // 범위를 벗어난 경우

                long min = MAX;

                for (int k = 0; k < 5; k++) {
                    if (dp[i - 1][k] == MAX)
                        continue; // 이전에 해당 지점에 도착하지 못한경우

                    int pastX = board[i - 1][0] + dx[k];
                    int pastY = board[i - 1][1] + dy[k]; // 이전 좌표

                    if (outOfRange(pastX, pastY))
                        continue;

                    min = Math.min(min, dp[i - 1][k] + calDis(pastX, pastY, nX, nY));
                }

                dp[i][j] = min;

            }
        }

        long result = dp[n][0];

        for (int i = 1; i < 5; i++) {
            result = Math.min(result, dp[n][i]);
        }

        System.out.println(result);

        br.close();
    }

    // 좌표계의 범위는 0부터 100_000까지이다. (구간을 벗어난 경우 처리)
    static boolean outOfRange(int nx, int ny) {
        if (nx < 0 || nx > 100_000 || ny < 0 || ny > 100_000)
            return true;

        return false;
    }

    // 거리 공식 : 맨허튼 거리 공식 ( | x1 - x2 | + | y1 - y2 | )
    static long calDis(int sx, int sy, int lx, int ly) {
        return Math.abs(sx - lx) + Math.abs(sy - ly);
    }
}
