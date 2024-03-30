package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_15903_카드_합체_놀이 {

    static long result = 0;
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(int i = 1 ; i <= n ; i++){
            pq.add(Long.parseLong(st.nextToken()));
        }

        for(int i = 1 ; i <= m ; i++){
            long f = pq.poll();
            long s = pq.poll();

            long sum = f + s;

            pq.add(sum);
            pq.add(sum);
        }

        while(!pq.isEmpty()){
            result += pq.poll();
        }


        System.out.println(result);
        br.close();
    }
}
