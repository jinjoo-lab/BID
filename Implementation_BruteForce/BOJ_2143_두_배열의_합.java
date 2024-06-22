package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_2143_두_배열의_합 {

    static long n;
    static int aLen, bLen;
    static long[] a,b;
    static long result = 0;
    static ArrayList<Long> aList = new ArrayList<>();
    static HashMap<Long,Long> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        aLen = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        a = new long[aLen + 1];
        for(int i = 1 ; i <= aLen ; i ++) {
            a[i] = Long.parseLong(st.nextToken()) + a[i-1];
        }

        st = new StringTokenizer(br.readLine()," ");
        bLen = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        b = new long[bLen + 1];
        for(int i = 1 ; i <= bLen ; i ++) {
            b[i] = Long.parseLong(st.nextToken()) + b[i-1];
        }

        for(int i = 1; i <= aLen ; i++) {
            for(int j = 0; j < i ; j++) {
                aList.add(a[i] - a[j]);
            }
        }

        for(int i = 1; i <= bLen ; i++) {
            for(int j = 0; j < i ; j++) {

                if(!map.containsKey(b[i] - b[j])) {
                    map.put(b[i] - b[j],1l);
                }else{
                    map.put(b[i] - b[j],map.get(b[i]-b[j]) + 1l);
                }
            }
        }

        Collections.sort(aList);

        for(int i = 0 , size = aList.size() ; i < size ; i++) {
            Long tmpA = aList.get(i);


            long target = n - tmpA;

            if(map.containsKey(target)) {
                result += map.get(target);
            }
        }

        System.out.println(result);
        br.close();
    }
}
