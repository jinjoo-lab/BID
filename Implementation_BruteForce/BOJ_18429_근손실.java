package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_18429_근손실 {

    static boolean[] v;
    static int[] per;
    static int n,k;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        per = new int[n+1];
        v = new boolean[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 1 ; i <= n ; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        makePer(1);
        System.out.println(count);
        br.close();
    }
    static void print(){
        for(int i = 1 ; i <= n ; i++){
            System.out.print(per[i]+" ");
        }
        System.out.println();
    }

    static int count = 0;
    static void makePer(int idx){

        if(idx > n){

            //print();

            int start = 500;

            for(int i = 1 ; i <= n ; i++){
                start += board[per[i]];
                start -= k;

                System.out.print(start+" ");

                if(start < 500){
                    return;
                }
            }
            System.out.println();

            count++;
            return;
        }

        for(int i = 1 ; i <= n ; i++){
            if(!v[i]){
                v[i] = true;
                per[idx] = i;
                makePer(idx + 1);
                v[i] = false;
            }
        }
    }
}
