package Implementation_BruteForce;
import java.io.*;
import java.util.*;

public class BOJ_3101_토끼의_이동 {
    static int n = 0;
    static int k = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0}; // 4방 이동

    static long[] startNum; // 각 줄의 시작
    static int curX = 0; static int curY = 0; // 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        char[] move = br.readLine().toCharArray();

        startNum = new long[2*n+2];

        // 각 줄의 시작 숫자를 저장. 여기서 줄이란 대각선을 의미한다. 1부터 시작한다 가정
        startNum[1] = 1;

        int idx = 1;

        // n+1번째 대각선까지 시작 숫자는 전 숫자에 순차적으로 증가하는 idx값 만큼 증가한다. (idx : 1 , 2 , 3 ,4 , 5 , n)
        for(int i=2 ; i <= n;i++){
            startNum[i] = startNum[i-1] + idx++;
        }

        // n+2번째 대각선부터 끝까지는 idx값이 반대로 감소한다. (idx : n-1 , n-2 , ... 2, 1)
        for(int i=n+1; i <= 2*n-1; i++){
            startNum[i] = startNum[i-1] + idx--;
        }

        // 최종 결과
        long result = 1;


        for(int i = 0 ; i < k ; i++){
            char curMove = move[i];
            // R L U D
            if(curMove == 'D'){
                curX += dx[3];
                curY += dy[3];
            }else if(curMove == 'R'){
                curX += dx[0];
                curY += dy[0];
            }else if(curMove == 'L'){
                curX += dx[1];
                curY += dy[1];
            }else{
                curX += dx[2];
                curY += dy[2];
            }

            int totalNum = curX + curY;

            // 행과 열을 합한 숫자에 1을 더한 값이 해당 '대각선의 순번'이다
            long curStart = startNum[totalNum + 1];

            if(totalNum % 2 == 0){
                // 짝수 라인
                if(totalNum < n ){
                    result += curStart + curY;
                }else{
                    result += curStart + Math.abs(n - curX - 1);
                }

            }else{
                // 홀수 라인
                if(totalNum < n){
                    result += curStart + curX;
                }else{
                    result += curStart + Math.abs(n - curY - 1);
                }
            }
        }

        System.out.println(result);
        br.close();
    }
}
