package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_2115_벌꿀_채취 {

    static int t = 0;

    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int result1 = 0;
    static int result2 = 0;
    static int result = 0;
    static int[][] board;

    static int[][] v;

    static Integer[] l1;
    static Integer[] l2;

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

            board = new int[n+1][n+1];
            v = new int[n+1][n+1];
            result = 0;

            for(int i = 1 ; i <= n ; i++){
                st = new StringTokenizer(br.readLine(), " ");

                for(int j = 1; j <= n ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 1 ; i <= n ; i++){
                for(int j = 1 ; j <= n ; j++){
                    if(j + m - 1 <= n){

                        l1 = new Integer[m];
                        int idx = 0;

                        for(int k = j ; k <= j + m - 1; k++){
                            v[i][k] = 1;
                            l1[idx++] = board[i][k];
                        }
                        find(i + 1);
                        for(int k = j ; k <= j + m - 1; k++){
                            v[i][k] = 0;
                        }
                    }
                }
            }

            sb.append("#"+a+" "+result+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static boolean[] isSelected;
    static void go(int idx,Integer[] num,boolean first){

        if(idx == m) {
            int tmp = 0;
            int tmpSum = 0;
            for (int i = 0; i < m; i++) {
                if (isSelected[i]) {
                    tmp += num[i];
                    tmpSum += num[i] * num[i];
                }
            }

            if(tmp <= k){
                if(first) result1 = Math.max(result1,tmpSum);
                else
                    result2 = Math.max(result2,tmpSum);
            }

            return;
        }

        isSelected[idx] = true;
        go(idx + 1,num,first);
        isSelected[idx] = false;
        go(idx + 1, num,first);
    }

    static void find(int row){

        for(int i = row ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j++){

                boolean keepIt = true;

                if(j + m - 1 > n)
                    continue;

                for(int k = j ; k <= j + m - 1; k++){
                    if(v[i][k] == 1){
                        keepIt = false;
                        break;
                    }
                }

                if(!keepIt) continue;

                if(j + m - 1 <= n){

                    l2 = new Integer[m];
                    int idx = 0;

                    for(int k = j ; k <= j + m - 1 ; k++){
                        v[i][k] = 2;
                        l2[idx++] = board[i][k];
                    }
                    result1 = 0 ;
                    result2 = 0;
                    isSelected = new boolean[m];
                    go(0,l1,true);
                    isSelected = new boolean[m];
                    go(0,l2,false);

                    result = Math.max(result,result1+result2);
                    for(int k = j ; k <= j + m - 1 ; k++){
                        v[i][k] = 0;
                    }
                }
            }
        }
    }

    static void print(){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j<= n ; j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
