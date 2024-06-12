package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_5710_전기_요금 {

    static int A,B;

    static long totalUse = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine()," ");

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if(A == 0 && B == 0)
                break;

            totalUse = calTotal();

            long tmp = bs(totalUse);

            sb.append(tmp+"\n");
        }


        System.out.print(sb.toString());
        br.close();
    }

    static long bs(long tmp) {
        long l = 0;
        long r = tmp / 2;
        long mid = 0;

        while(l <= r) {
            mid = (l + r) / 2;

            long sang = cal(mid);
            long neighbor = cal(tmp - mid);

            if(Math.abs(sang - neighbor) == B){
                return sang;
            }

            if(Math.abs(sang - neighbor) > B) {
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }

        return 0;
    }
    static long cal(long use) {
        if(use >= 1 && use <= 100) {
            return 2 * use;
        }else if(use >= 101 && use <= 10000) {
            return 200 + 3 *(use - 100);
        }else if(use >= 10001 && use <= 1000000) {
            return 200 + (9900 * 3) + 5 *(use - 10000);
        }else if(use > 1000000) {
            return 200 + (9900 * 3) + (5 * 990000) + (7 * (use - 1000000));
        }

        return 0;
    }

    static long calTotal() {
        long tmpA = A;
        long result = 0;

        if(tmpA <= 200) {
            return tmpA / 2;
        }else{
            result += 100;
            tmpA -= 200;

            if(tmpA <= (9900 * 3)) {
                result += tmpA / 3;
                return result;
            }else {
                result += 9900;
                tmpA -= (9900 * 3);

                if(tmpA <= (990000 * 5)) {
                    result += tmpA / 5;
                    return result;
                }else {
                    result += 990000;
                    tmpA -= (990000 * 5);

                    result += tmpA / 7;
                    return result;
                }
            }
        }
    }
}
