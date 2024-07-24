package MainPackage;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cur = br.readLine();

        int n = cur.length();

        System.out.println(cur);
        System.out.println(shuffle(n,cur));


        br.close();
    }

    static String shuffle(int n,String cur) {
        char[] arr = cur.toCharArray();

        for(int i = 0 ; i < n ; i++) {
            // 0 ~ n -1
            int randomIdx = (int)(Math.random() * n);

            swap(arr,i,randomIdx);
        }

        return new String(arr);
    }

    static void swap(char[] arr, int i,int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
