package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_21943_연산_최대로 {
    static int[] op;
    static int n;
    static int[] board;

    static boolean[] v;
    static int[] perBoard;
    static int p,q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];
        perBoard = new int[n+n+1];
        v = new boolean[n+1];
        op = new int[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        per(1);
        System.out.println(result);
        br.close();
    }
    static int result = 0;
    static void comb(int depth,int cnt){
        if(depth > 2*n - 2) {
            if (cnt == p) {
                int num = perBoard[1];
                int tmpResult = 1;

                for(int i = 2 ; i <= 2*n - 2 ; i+=2){
                    if(perBoard[i] == 0){
                        tmpResult *= num;
                        num = perBoard[i+1];
                    }else{
                        num += perBoard[i+1];
                    }
                }

                tmpResult *= num;
                result = Math.max(result,tmpResult);
            }
            return;
        }

        perBoard[depth] = 1;
        comb(depth + 2,cnt + 1);
        perBoard[depth] = 0;
        comb(depth + 2, cnt);

    }

    static void per(int idx){
        if(idx > 2*n - 1){
            comb(2,0);
            return;
        }

        for(int i = 1 ; i <= n ; i++){
            if(!v[i]){
                v[i] = true;
                perBoard[idx] = board[i];
                per(idx + 2);
                v[i] = false;
            }
        }
    }
}
