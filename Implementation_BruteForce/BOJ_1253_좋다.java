package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_1253_좋다 {

    static int n;
    static int[] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            board[i] =  Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);

        int result = 0;

        for(int i = 0 ; i < n ; i++) {
            result += find(i) ? 1 : 0;
        }

        System.out.println(result);

        br.close();
    }

    static boolean find(int idx) {

        int target = board[idx];

        int l = 0;
        int r = n - 1;

        while(l < r) {
            if(l == idx) {
                l = l + 1;
            }

            if(r == idx) {
                r = r - 1;
            }

            if(l == r)
                break;

            int tmpV = board[l] + board[r];

            if(tmpV == target) {
                return true;
            }

            if(tmpV > target) {
                r = r - 1;
            }

            else {
                l = l + 1;
            }


        }

        return false;
    }
}
