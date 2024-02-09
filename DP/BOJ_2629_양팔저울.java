package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ_2629_양팔저울 G3
// 2024_02_09
// time : 148 ms
// memory : 15984 KB

public class BOJ_2629_양팔저울 {
    static int n = 0;
    static int m = 0;
    static int[] choo; // 추의 무게 저장
    static int[] go; // 구슬의 무게 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        choo = new int[n];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < n ; i++){
            choo[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        go = new int[m];
        for(int i= 0 ; i< m ; i++){
            go[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j] : i번째 추를 추가해서 j의 무게를 측정할 수 있는가 ?
        boolean[][] dp = new boolean[n+1][40001];

        dp[0][0] = true; // 무게 0은 잴수있다.
        dp[0][choo[0]] = true; // 첫번째 추의 무게를 잴 수 있다.

        for(int i = 1; i < n; i ++){

            for(int j=0; j <= 40000; j ++){

                dp[i][j] = dp[i-1][j] || dp[i][j]; // 이전의 값 가져오기

                // 추를 왼쪽이나 오른쪽에 다는것은 + , - 연산을 의미한다.
                if (dp[i-1][j]){

                    if(j + choo[i] <= 40000){
                        dp[i][j + choo[i]] = true;
                    }

                    dp[i][Math.abs(j - choo[i])] = true;
                }
            }
        }

        // 판단
        for(int i= 0 ; i < m ; i++){
            if(dp[n-1][go[i]])
                sb.append("Y ");

            else
                sb.append("N ");
        }
        System.out.println(sb);

        br.close();
    }
}
