package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class Swea_4008_숫자_만들기 {
    static int t = 0;
    static int n = 0;

    static int[] op;

    static int[] data;

    static char[] opData;

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for (int a = 1; a <= t; a++) {
            st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());

            data = new int[n];
            op = new int[4];
            opData = new char[n-1];

            st = new StringTokenizer(br.readLine(), " ");

            int idx = 0;

            for(int  i = 0 ; i < 4 ; i ++){
                int cur = Integer.parseInt(st.nextToken());

                op[i] = cur;
            }

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0 ; i < n ; i++){
                data[i] = Integer.parseInt(st.nextToken());
            }

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            per(0);

            sb.append("#"+a+" "+(max - min)+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int go(){
        int v = data[0];
        int dataIdx = 1;
        int opIdx = 0;

        while(opIdx < n - 1){

            v = cal(v,data[dataIdx],opData[opIdx]);

            dataIdx++;
            opIdx++;
        }


        return v;
    }

    static int cal(int v1,int v2,char op){
        if(op == '+'){
            return v1 + v2;
        }else if(op == '-'){
            return v1 - v2;
        }else if(op == '*'){
            return v1 * v2;
        }else
            return v1 / v2;
    }

    static void per(int idx){

        if(idx == n - 1){
            int tmp = go();

            max = Math.max(max,tmp);
            min = Math.min(min,tmp);
            return;
        }

        for(int i = 0 ; i < 4; i ++){

            if(op[i] == 0)
                continue;

            op[i] -= 1;
            opData[idx] = get(i);
            per(idx + 1);
            op[i] += 1;
        }

    }

    static char get(int cur){
        if(cur == 0)
            return '+';

        else if(cur == 1)
            return '-';

        else if(cur == 2)
            return '*';

        return '/';
    }
}

