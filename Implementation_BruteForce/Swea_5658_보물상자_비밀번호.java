package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class Swea_5658_보물상자_비밀번호 {
    static int t = 0;
    static int n = 0;
    static int k = 0;
    static char[] board;
    static long result = 0;
    static TreeSet<Long> set = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++) {
            st = new StringTokenizer(br.readLine()," ");

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            board = new char[n+1];
            set = new TreeSet<>((x,y) -> Long.compare(y, x));

            char[] arr = br.readLine().toCharArray();

            for(int i = 1 ; i <= n ; i++) {
                board[i] = arr[i-1];
            }


            for(int i = 1 ; i <= n ; i++) {
                turn();

                getMax();
            }

            int count = 0;

            for(long tmpRe : set) {
                count++;


                if(count == k) {
                    sb.append("#"+a+" "+tmpRe+"\n");
                    break;
                }
            }

        }

        System.out.print(sb);
        br.close();
    }

    static int getValue(char cur) {
        if(cur >= 'A' && cur <= 'F') {
            return cur - 'A' + 10;
        }

        return cur - '0';
    }

    static void getMax() {
        int tmp = n / 4 - 1;

        long tmpNum = 0;
        int count = tmp;

        for(int i = 1 ; i <= n ; i++) {

            tmpNum += (long)Math.pow(16, count) * getValue(board[i]);

            count--;

            if(count < 0) {
                set.add(tmpNum);
                count = tmp;
                tmpNum = 0;
            }
        }

    }

    static void print() {
        int tmp = n / 4;

        for(int i = 1 ; i <= n ; i++) {
            System.out.print(board[i]+" ");

            if(i % tmp == 0)System.out.println(" | ");
        }System.out.println();
    }

    static void turn() {
        char tmp = board[n];

        for(int i = n ; i >= 2; i--) {
            board[i] = board[i-1];
        }

        board[1] = tmp;
    }

}
