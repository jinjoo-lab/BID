package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7568_덩치 {

    static int n = 0;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        board = new int[n][3];

        for(int i = 0 ;i < n ;i++){
            st = new StringTokenizer(br.readLine()," ");

            board[i][0] = i + 1; // 번호
            board[i][1] = Integer.parseInt(st.nextToken()); // 몸무게
            board[i][2] = Integer.parseInt(st.nextToken()); // 키
        }

        Arrays.sort(board,(x, y) -> y[1] - x[1]); // 몸무게로 내림차순 정렬

        int[] result = new int[n+1];

        result[board[0][0]] = 1; // 1등은 부동의 1등

        for(int i=1; i<n ;i++){

            int count = 1;

            for(int j=i-1;j>=0;j--){
                if(board[i][1] < board[j][1] && board[i][2] < board[j][2]){
                    count += 1; // 몸무게로 정렬하였지만 몸무게가 같은 경우 생각 !
                }
            }

            result[board[i][0]] = count;
        }

        for(int i=1 ;i <=n ;i ++){
            sb.append(result[i]+" ");
        }
        System.out.println(sb);
        br.close();
    }
}
