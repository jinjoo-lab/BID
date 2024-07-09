package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_24526_전화_돌리기 {
    static int n,m;

    static int[] count;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        count = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            count[v2]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1 ; i <= n ; i++) {
            if(count[i] == 0) {
                q.add(i);
            }
        }

        br.close();
    }
}
