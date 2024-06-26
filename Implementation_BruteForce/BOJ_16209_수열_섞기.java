package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class BOJ_16209_수열_섞기 {

    static int n;

    static PriorityQueue<Integer> ppq = new PriorityQueue<>(
            (x,y) -> y - x
    );

    static PriorityQueue<Integer> mpq = new PriorityQueue<>(
            (x,y) -> x - y
    );

    static Deque<Integer> minus = new ArrayDeque<>();
    static Deque<Integer> plus = new ArrayDeque<>();
    static Deque<Integer> zero = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        for(int i = 0 ; i < n ; i++) {
            int tmp = Integer.parseInt(st.nextToken());

            if(tmp == 0) {
                zero.add(0);
            }else if(tmp > 0) {
                ppq.add(tmp);
            }else {
                mpq.add(tmp);
            }
        }

        while(!ppq.isEmpty()) {
            int tmp = ppq.poll();

            if(plus.isEmpty() || plus.size() == 1) {
                plus.add(tmp);
            }else {
                if(plus.peekFirst() >= plus.peekLast()) {
                    plus.addFirst(tmp);
                }else {
                    plus.addLast(tmp);
                }
            }
        }

        while(!mpq.isEmpty()) {
            int tmp = mpq.poll();

            if(minus.isEmpty() || minus.size() == 1) {
                minus.add(tmp);
            }else {
                if(minus.peekFirst() * -1 >= minus.peekLast() * -1) {
                    minus.addFirst(tmp);
                }else {
                    minus.addLast(tmp);
                }
            }
        }

        StringBuilder sb = new StringBuilder();


        if(!minus.isEmpty()) {
            if(minus.peekFirst() <= minus.peekLast()) {
                while(!minus.isEmpty())
                    sb.append(minus.pollLast()).append(" ");
            }else {
                while(!minus.isEmpty())
                    sb.append(minus.pollFirst()).append(" ");
            }
        }while(!zero.isEmpty()) {
                sb.append(zero.poll()).append(" ");
        }
        if(!plus.isEmpty()) {
            if(plus.peekFirst() <= plus.peekLast()) {
                while(!plus.isEmpty())
                    sb.append(plus.pollFirst()).append(" ");
            }else {
                while(!plus.isEmpty())
                    sb.append(plus.pollLast()).append(" ");
            }
        }


        System.out.println(sb.toString());

        br.close();
    }

}
