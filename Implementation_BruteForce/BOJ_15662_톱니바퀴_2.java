package Implementation_BruteForce;

import java.io.*;
import java.util.*;

// BOJ_2159_톱니바퀴(2) G5
// 2024_02_07
// time : 208 ms
// memory : 19616 KB

public class BOJ_15662_톱니바퀴_2 {
    static int n = 0;
    static int m = 0;
    static int[] chain; // 톱니바퀴 정보 저장
    static int[] turnTable; // 해당 순차에 각 톱니바퀴의 회전 여부를 담는 배열 (시계 , 반시계 구분)
    static boolean[] v;

    static void printChain(){for(int i=1;i<=n;i++){
        System.out.println(Integer.toBinaryString(chain[i]));
    }}

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        chain = new int[n+1];
        turnTable = new int[n+1];
        v = new boolean[n+1];

        for(int i=1; i <= n ; i ++){
            char[] arr = br.readLine().toCharArray();

            int idx = 0;

            for(int j= arr.length - 1 ;j >= 0;j--){

                if(arr[j] == '1') {
                    chain[i] |= (1 << idx); // 톱니바퀴의 정보를 비트마스킹
                }

                idx++;
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        for(int i=1; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int num = Integer.parseInt(st.nextToken());

            // 1 시계 , 2 반시계
            int dir = Integer.parseInt(st.nextToken());

            Arrays.fill(turnTable,0); // 회전 여부 배열 초기화
            Arrays.fill(v,false); // 방문 배열 초기화

            findAll(num,dir); // 해당 순차에 회전해야 할 톱니바퀴를 전부 구한다.

            for(int j= 1; j <= n ; j++){

                if(turnTable[j] == 1){
                    turn(true,j); // 시계 방향 회전
                }else if(turnTable[j] == -1){
                    turn(false,j); // 반시계 방향 회전
                }
            }

        }

        int count = 0; // 12시 방향이 S극인 개수 찾기


        for(int i=1 ; i<=n; i++){
            int tmpCount = (chain[i] & ( 1 << 7)) != 0 ? 1 : 0;
            count += tmpCount;
        }

        System.out.println(count);
        br.close();
    }

    static void turn(boolean isTime,int cur){

        if(isTime){
            // 시계 방향
            int zero = (chain[cur] & (1 << 0)); // 12시 방향

            if(zero == 0){
                chain[cur] >>= 1; // 12시 방향이 0이면 그냥 >> 연산으로 이동을 구현 가능
            }else{
                chain[cur] >>= 1;
                chain[cur] |= (1 << 7); // 1일 경우 마지막 위치를 1로 설정
            }

        }else{
            // 반시계 방향
            int last = (chain[cur] & (1 << 7)); // 11시 방향 극
            chain[cur] &= ~(1 << 7); // 0으로 설정

            if(last == 0){
                chain[cur] <<= 1; // 그대로 << 연산
            }else{
                chain[cur] <<= 1;
                chain[cur] |= (1 << 0); // 12시 극 재설정
            }
        }

    }

    static void findAll(int num , int dir){
        turnTable[num] = dir;
        v[num] = true;

        // 현재 자신의 3시와 오른쪽의 9시 비교
        if(num + 1 <= n && !v[num+1]){
            v[num+1] = true;
            int curRight = (chain[num] & (1 << 5)) != 0 ? 1 : 0;
            int tmpLeft = (chain[num + 1] & (1 << 1)) != 0 ? 1 : 0;

            if(curRight != tmpLeft){
                findAll(num + 1, changeDir(dir));
            }
        }

        // 현재 자신의 9시와 왼쪽의 3시 비교
        if(num -1 >= 1 && !v[num-1]){
            v[num-1] = true;
            int curLeft = (chain[num] & (1 << 1)) != 0 ? 1 : 0;
            int tmpRight = (chain[num - 1] & (1 << 5)) != 0 ? 1 : 0;

            if(curLeft != tmpRight){
                findAll(num - 1, changeDir(dir));
            }
        }

    }

    static int changeDir(int dir){
        if(dir == 1)
            return -1;

        return 1;
    }
}
