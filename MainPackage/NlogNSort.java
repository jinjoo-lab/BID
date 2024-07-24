package MainPackage;

import java.util.*;
import java.io.*;

public class NlogNSort {

    static int n;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");

        board = new int[n];
        for(int i = 0; i < n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(0,n-1);
        print();

        br.close();
    }

    static void quickSort(int l,int h) {
        int part = partition(l,h);

        if(l < part - 1)
            quickSort(l,part - 1);

        if(h > part)
            quickSort(part , h);
    }

    static void print() {
        for(int i = 0; i < n; i++) {
            System.out.print(board[i]+" ");
        }
        System.out.println();
    }

    static int partition(int l, int h) {

        int pivot = board[(l+ h)/2];

        while(l <= h) {
            while(board[l] < pivot) {
                l++;
            }

            while(board[h] > pivot) {
                h--;
            }

            if(l <= h) {
                swap(l,h);
                l++;
                h--;
            }
        }

        return l;
    }

    static void swap(int i,int j) {
        int tmp = board[i];
        board[i] = board[j];
        board[j] = tmp;
    }
}
