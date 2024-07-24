package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_27085_최대_점수 {

    static int n,s;
    static long[] board;
    static HashSet<Point> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        board = new long[n+1];
        st = new StringTokenizer(br.readLine()," ");

        for(int i = 1 ; i <= n ; i++) {
            board[i] = Long.parseLong(st.nextToken());
        }

        long[] dp = new long[n+1];

        int l = s -1;
        int r = s + 1;

        long lMax = 0;
        long rMax = 0;

        set.add(new Point(s,s));

        while(l <= r) {

            dp[r] = Math.max(dp[r],lMax + dp[r]);
            dp[l] = Math.max(dp[l],rMax + dp[l]);

            if(r <= n) {
                long tmpRV = dp[r - 1] + board[r];
                if(tmpRV >= 0) {
                    if(tmpRV >= rMax) {
                        rMax = tmpRV;
                    }
                    dp[r] = tmpRV;
                    r = r + 1;
                }
            }

            int tmpL = l - 1;

            if(l >= 1) {
                long tmpLV = dp[l - 1] + board[l];
                if(tmpLV >= 0) {
                    if(tmpLV >= lMax) {
                        lMax = tmpLV;
                    }
                    dp[l] = tmpLV;
                    l = l - 1;
                }
            }

            if(set.contains(new Point(l,r))) {
                break;
            }

            set.add(new Point(l,r));
        }

        System.out.println(Math.max(lMax,rMax));
        br.close();
    }

    static class Point {
        int l;
        int r;

        Point(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return this.l == p.l && this.r == p.r;
        }

        @Override
        public int hashCode() {
            return Objects.hash(l, r);
        }
    }
}
