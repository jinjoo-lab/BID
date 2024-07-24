package MainPackage;

import java.util.*;
import java.io.*;

public class NpowSort {

    static int n;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        //selectionSort();

        //bubbleSort();

        br.close();
    }

    static void swap(int i,int j) {
        int tmp = board[i];
        board[i] = board[j];
        board[j] = tmp;
    }

    // O(N) , O(N^2)
    static void insertionSort() {
        for(int i = 1 ; i < n ; i++) {
            int key = board[i];
            int j = i - 1;

            while(j >= 0 && board[j] > key) {
                board[j+1] = board[j];
                j = j - 1;
            }

            board[j+1] = key;
        }

        print();
    }

    // O(N^2) O(N^2)
    static void selectionSort() {
        for(int i = 0 ; i < n - 1 ; i++) {
            int minIdx = i;

            for(int j = i + 1 ; j < n ; j++) {
                if(board[j] < board[minIdx]) {
                    minIdx = j;
                }
            }

            swap(i,minIdx);
        }

        print();
    }


    // O(N^2) O(N^2)
    static void bubbleSort() {
        for(int i = 0 ; i < n ; i++) {
            System.out.println("SKRR");
            boolean flag = false;

            for(int j = 0 ; j < n - i - 1 ; j++) {
                if(board[j] > board[j+1]) {
                    swap(j,j+1);
                    flag = true;
                }
            }

            if(!flag) {
                break;
            }
        }

        print();
    }

    static void print() {
        for(int i = 0 ; i < n ; i++) {
            System.out.print(board[i]+" ");
        }
        System.out.println();
    }
}
