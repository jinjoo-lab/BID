package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2666_벽장문의_이동 {

    static int n = 0;
    static int m = 0;

    static int open1,open2;

    static int[] use;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        open1 = Integer.parseInt(st.nextToken());
        open2 = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        use = new int[m+1];

        for(int i=1 ; i<= m ; i++){
            use[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[21][n+1][n+1];

        for(int i=0;i<=m;i++){
            for(int j=1;j<=n;j++){
                for(int k=1;k<=n;k++){
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        dp[0][open1][open2] = 0;
        dp[0][open2][open1] = 0;

        // dp[i][j][k] : i번째 차례에 j와 k문이 열려있을 때 최소 이동 횟수

        int result = Integer.MAX_VALUE;

        for(int k=1; k<=m; k++){
            int curUse = use[k]; // 열어야 할 문

            for(int i=1 ; i<=n ; i++){
                for(int j=1 ;j<=n ; j++) {

                    if (dp[k - 1][i][j] == Integer.MAX_VALUE)
                        continue; // 이전에 열린적이 없으면 pass

                    int minNum = Math.min(i, j);
                    int maxNum = Math.max(i, j); // 작은값과 큰값

                    // 해당 차례의 문이 이미 열려있다면
                    if (curUse == minNum || curUse == maxNum) {
                        dp[k][minNum][maxNum] = Math.min(dp[k][minNum][maxNum], dp[k - 1][i][j]);

                        if (k == m) {
                            result = Math.min(result, dp[k][minNum][maxNum]);
                        }

                        continue;
                    }

                    // 작은 번호의 문보다 앞쪽에 있다면
                    if (curUse < minNum) {
                        dp[k][curUse][maxNum] = Math.min(dp[k][curUse][maxNum], dp[k - 1][i][j] + Math.abs(curUse - minNum));

                        if (k == m) {
                            result = Math.min(result, dp[k][curUse][maxNum]);
                        }

                    }

                    // 큰 번호의 문보다 오른쪽에 있다면
                    else if(maxNum < curUse){
                        dp[k][minNum][curUse] = Math.min(dp[k][minNum][curUse],dp[k-1][i][j] + Math.abs(curUse - maxNum));

                        if(k == m){
                            result = Math.min(result,dp[k][minNum][curUse]);
                        }
                    }

                    // 열려있는 두 문 사이에 있다면
                    else {

                        dp[k][minNum][curUse] = Math.min(dp[k][minNum][curUse], dp[k - 1][i][j] + Math.abs(curUse - maxNum));
                        dp[k][curUse][maxNum] = Math.min(dp[k][curUse][maxNum], dp[k - 1][i][j] + Math.abs(curUse - minNum));

                        if (k == m) {
                            result = Math.min(result, Math.min(dp[k][minNum][curUse], dp[k][curUse][maxNum]));
                        }
                    }
                }
            }
        }

        System.out.println(result);
        br.close();
    }

}
