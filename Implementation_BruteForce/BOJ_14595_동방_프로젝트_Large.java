package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_14595_동방_프로젝트_Large {

    static int n,m;

    static int root[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            root[i] = i;
        }

        int[][] process = new int[m][2];

        for(int i = 0 ; i < m ; i ++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            process[i][0] = v1;
            process[i][1] = v2;
        }

        Arrays.sort(process,(x,y) -> x[0] - y[0]);

        Queue<int[]> q = new ArrayDeque<>();


            int max = 0;
            int min = 0;


        for(int i = 0 ; i < m ; i++) {

            if(i == 0) {
                max = process[0][1];
                min = process[0][0];
            }

            if(process[i][0] <= max) {
                max = Math.max(process[i][1], max);
            }else {
                q.add(new int[]{min,max});
                min = process[i][0];
                max = process[i][1];
            }
        }
        q.add(new int[]{min,max});

        while(!q.isEmpty()) {
            int[] query = q.poll();

            int tmpRoot = find(query[0]);

            for(int i = query[0] ; i <= query[1] ; i++) {
                root[i] = tmpRoot;
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for(int i = 1 ; i <= n ; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());


        br.close();
    }

    static int find(int x) {
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y) {
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}
