package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_2112_보호_필름 {

    static int t = 0;
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int[][] board;

    static int result;

    static int[] change;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            board = new int[n+1][m+1];
            change = new int[n+1];
            result = n;

            for(int i = 1 ; i <= n ; i++){
                st = new StringTokenizer(br.readLine(), " ");

                for(int j = 1 ; j <= m ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken()) + 1;
                }
            }

            go(1,0);
            sb.append("#"+a+" "+result+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void go(int idx,int changeNum){

        if(result < changeNum){
            return;
        }

        if(idx > n){
            boolean tmp = isComplete();

            if(tmp){
                result = Math.min(result,changeNum);
            }

            return;
        }

        go(idx + 1,changeNum);
        change[idx] = 1;
        go(idx + 1 , changeNum + 1);
        change[idx] = 2;
        go(idx + 1 , changeNum + 1);
        change[idx] = 0;

    }

    static boolean isComplete(){

        for(int j = 1 ; j <= m ; j++){

            boolean tmp = false;

            for(int i = 1 ; i <= n ; i++){

                int start = change[i] == 0 ? board[i][j] : change[i];

                int end = i + k - 1;

                if(end > n)
                    break;

                tmp = oneRow(i,j,end,start);

                if(tmp)
                    break;
            }

            if(!tmp)
                return false;
        }

        return true;
    }

    static boolean oneRow(int i,int j,int e,int start){
        for(int go = i ; go <= e ; go++){

            int tmpNum = change[go] == 0 ? board[go][j] : change[go];

            if(tmpNum != start){
                return false;
            }
        }

        return true;
    }
}
