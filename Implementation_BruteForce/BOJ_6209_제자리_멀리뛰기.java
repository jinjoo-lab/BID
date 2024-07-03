package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_6209_제자리_멀리뛰기 {

    static int d,n,m;
    static int[] rock;
    static int[] dis;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        rock = new int[n + 2];

        rock[0] = 0;

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            rock[i] = Integer.parseInt(st.nextToken());
        }

        rock[n + 1] = d;

        Arrays.sort(rock);

        int l = 0;
        int r = d;
        int mid = 0;

        while(l <= r) {
            mid = (l + r) / 2;

            boolean keep = find(mid);

            if(keep) {
                result = Math.max(result,mid);
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }

        System.out.println(result);
        br.close();
    }

    static boolean find(int target) {
        int count = 0;
        int l = 0;
        int r = 1;

        while(r <= n + 1 && l <= r) {
            int tmpDis = rock[r] - rock[l];

            if(tmpDis < target) {
                count++;
            }else {
                l = r;
            }
            r++;
        }

        return count <= m;
    }
}
