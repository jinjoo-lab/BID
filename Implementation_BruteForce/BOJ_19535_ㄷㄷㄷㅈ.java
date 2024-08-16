package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_19535_ㄷㄷㄷㅈ {

    static int tt;
    static long money;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        tt = Integer.parseInt(st.nextToken());

        for(int t = 1 ; t <= tt ; t++) {
            st = new StringTokenizer(br.readLine()," ");
            money = Long.parseLong(st.nextToken());
        }


        br.close();
    }
}
