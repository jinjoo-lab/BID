package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_21606_아침_산책 {

    static boolean[] v;
    static long result = 0;
    static int n;
    static boolean[] isIt;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        isIt = new boolean[n+1];
        v = new boolean[n+1];
        graph = new ArrayList[n+1];


        char[] arr = br.readLine().toCharArray();

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '1') {
                isIt[i+1] = true;
            }else {
                isIt[i+1] = false;
            }

            graph[i+1] = new ArrayList<>();
        }

        for(int i = 1; i <= n - 1; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);

            if(isIt[v1] && isIt[v2]) {
                result += 2;
            }
        }


        for(int i = 1 ; i <= n ; i++) {

            long tmp = 0;

            if(!isIt[i] && !v[i]) {
                v[i] = true;
                tmp += search(i);
                result += (tmp * (tmp - 1));
            }
        }

        System.out.println(result);
        br.close();
    }

    static long search(int idx) {

        long tmpCount = 0;

        for(int next : graph[idx]) {
            if(isIt[next]) {
                tmpCount++;
            }else if(!isIt[next] && !v[next]) {
                v[next] = true;
                tmpCount += search(next);
            }
        }

        return tmpCount;
    }
}
