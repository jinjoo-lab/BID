package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_17612_쇼핑몰 {
    static int n,k;

    static Queue<Node> input = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int idx = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            input.add(new Node(idx,c));
        }


        br.close();
    }

    static class Node {
        int idx;
        int c;

        Node(int idx,int c) {
            this.idx = idx;
            this.c = c;
        }
    }
}
