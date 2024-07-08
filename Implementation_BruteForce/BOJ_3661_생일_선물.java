package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3661_생일_선물 {
    static int t;
    static int n,m;
    static Node[] arr;
    static int total;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int tt = 1 ; tt <= t ; tt++) {
            st = new StringTokenizer(br.readLine()," ");

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            arr = new Node[n];
            result = new int[n];
            total = 0;

            st = new StringTokenizer(br.readLine()," ");
            for(int i = 0 ; i < n ; i++) {
                int v = Integer.parseInt(st.nextToken());
                arr[i] = new Node(i+1,v);

                total += v;
            }

            if(total < m) {
                sb.append("IMPOSSIBLE\n");
                continue;
            }

            Arrays.sort(arr, (x, y) -> {
                if(x.v == y.v) {
                    return y.idx - x.idx;
                }
                return x.v - y.v;
            });

            int tmpTotal = m;

            for(int i = 0 ; i < n ; i++) {
                int tmp = tmpTotal / (n - i);
                Node cur = arr[i];

                if(cur.v > tmp) {
                    result[cur.idx - 1] = tmp;
                    tmpTotal -= tmp;
                }else {
                    result[cur.idx - 1] = cur.v;
                    tmpTotal -= cur.v;
                }
            }

            for(int i = 0 ; i < n ; i++) {
                sb.append(result[i]).append(" ");
            }sb.append("\n");
        }

        System.out.print(sb);

        br.close();
    }

    static class Node {
        int idx;
        int v;

        Node(int idx,int v) {
            this.idx = idx;
            this.v = v;
        }
    }
}
