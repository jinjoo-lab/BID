package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_2477_차량_정비소 {

    static int t = 0;
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int A = 0;
    static int B = 0;

    static int[] arr1;
    static int[] arr2;

    static People[] people;

    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            arr1 = new int[n+1];
            arr2 = new int[n+1];
            result = new int[k+1][2];

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 1 ; i <= n ; i++){
                arr1[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 1 ; i <= m ; i++){
                arr2[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine(), " ");
            people = new People[k];

            for(int i = 0 ; i < k ; i++){
                people[i] = new People(i+1,Integer.parseInt(st.nextToken()));

            }

            go();
            int re = 0;
            for(int i = 1; i <= k ; i++){
                if(result[i][0] == A && result[i][1] == B){
                    re += i;
                }
            }

            if(re == 0)
                re = -1;

            sb.append("#"+a+" "+re+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static void go(){
        PriorityQueue<People2> repairWait = new PriorityQueue<>(
                (x,y) ->{
                    if(x.t == y.t)
                        return x.num - y.num;
                    return x.t - y.t;
                }
        );
        Queue<People> acceptWait = new LinkedList<>();
        boolean[] complete = new boolean[k+1];
        int[][] accept = new int[n + 1][2];
        int[][] repair = new int[m + 1][2];

        int t = 0;
        int pIdx = 0;

        while(true){
            // 정비 창구 업무 수행
            for(int i = 1 ; i <= m ; i++){
                if(repair[i][0] > 0){
                    if(repair[i][1] == 0){
                        repair[i][0] = 0;

                        if(!repairWait.isEmpty()){
                            People2 cur = repairWait.poll();
                            repair[i][0] = cur.idx;
                            repair[i][1] = arr2[i] - 1;
                            result[repair[i][0]][1] = i;
                            complete[repair[i][0]] = true;
                        }
                    }
                    else {
                        repair[i][1] -= 1;
                    }
                }else if(repair[i][0] == 0){
                    if(!repairWait.isEmpty()){
                        People2 cur = repairWait.poll();
                        repair[i][0] = cur.idx;
                        repair[i][1] = arr2[i] - 1;
                        result[repair[i][0]][1] = i;
                        complete[repair[i][0]] = true;
                    }
                }
            }

            if(complete(complete))
                break;

            // 접수 창구 업무 수행
            for(int i = 1 ; i <= n ; i++){
                if(accept[i][0] > 0){
                    if(accept[i][1] == 0){
                        result[accept[i][0]][0] = i;
                        repairWait.add(new People2(accept[i][0],t,i));
                        accept[i][0] = 0;

                        if(!acceptWait.isEmpty()){
                            People nowTurn = acceptWait.poll();
                            accept[i][0] = nowTurn.idx;
                            accept[i][1] = arr1[i] - 1;
                        }
                    }

                    else {
                        accept[i][1] -= 1;
                    }
                }else if(accept[i][0] == 0){
                    if(!acceptWait.isEmpty()){
                        People nowTurn = acceptWait.poll();
                        accept[i][0] = nowTurn.idx;
                        accept[i][1] = arr1[i] - 1;
                    }
                }
            }

            while(pIdx < k) {
                People cur = people[pIdx];

                if (cur.t > t) {
                    break;
                }


                int tmpIdx = 0;

                for (int i = 1; i <= n; i++) {
                    if (accept[i][0] == 0) {
                        tmpIdx = i;
                        break;
                    }
                }

                if (tmpIdx >= 1) {
                    accept[tmpIdx][0] = cur.idx;
                    accept[tmpIdx][1] = arr1[tmpIdx] - 1;
                } else {
                    acceptWait.add(cur);
                }
                pIdx++;
            }

            t++;
        }
    }

    static boolean complete(boolean[] complete){
        for(int i = 1 ; i<= k ; i++){
            if(!complete[i])
                return false;
        }

        return true;
    }

    static void print(int[][] arr,int num){
        for(int i = 1 ; i <= num ; i++){
            System.out.print(arr[i][0]+" ");
        }
        System.out.println();
    }
}
class People2{
    int idx;
    int t;
    int num;

    People2(int idx,int t,int num){
        this.idx = idx;
        this.t = t;
        this.num = num;
    }
}
class People{
    int idx;
    int t;

    People(int idx,int t){
        this.idx = idx;
        this.t = t;
    }
}
