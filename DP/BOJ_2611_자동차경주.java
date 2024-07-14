package DP;

import java.util.*;
import java.io.*;

public class BOJ_2611_자동차경주 {

    static int n,m;

    static int result;

    static boolean[] v;

    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        v= new boolean[n+1];
        for(int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) * -1;

            graph[v1].add(new int[]{v2,c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x,y) -> x[1] - y[1]
        );

        int[] dis = new int[n+1];
        int[] path = new int[n+1];

        pq.offer(new int[]{1,0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(v[cur[0]])
                continue;

            v[cur[0]] = true;

            for(int[] next : graph[cur[0]]) {
                if(dis[next[0]] > cur[1] + next[1]) {
                    dis[next[0]] = cur[1] + next[1];
                    path[next[0]] = cur[0];

                    if(next[0] == 1)
                        continue;

                    pq.add(new int[]{next[0],dis[next[0]]});
                }
            }
        }

        sb.append(1).append(" ");

        int tmp = 1;

        while(true) {
            tmp = path[tmp];

            sb.append(tmp);

            if(tmp == 1)
                break;

            sb.append(" ");
        }


        System.out.println(dis[1] * -1);
        System.out.println(sb.reverse().toString());
        br.close();
    }
}
