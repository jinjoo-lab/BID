package Implementation_BruteForce;

import java.util.*;
import java.io.*;

public class CT_왕실의_기사_대결 {

    static int L,N,Q;
    static int[][] board;

    static int[] original;

    static int[][] pBoard;

    static Node[] player;

    static boolean[] isMove;

    // 위 , 오 , 아, 왼
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static Queue<Integer> moveList = new ArrayDeque<>();

    static int[][] tmpBoard;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        board = new int[L+1][L+1];
        pBoard = new int[L+1][L+1];
        tmpBoard = new int[L+1][L+1];
        player = new Node[N+1];
        isMove = new boolean[N+1];
        original = new int[N+1];

        for(int i = 1 ; i <= L ; i++){
            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= L ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= N ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());

            original[i] = hp;
            player[i] = new Node(x,y,h,w,hp);

            for(int r = x; r < x + h; r++){
                for(int c = y; c < y + w ; c++){
                    pBoard[r][c] = i;
                }
            }
        }

        for(int i = 1 ; i <= Q; i++){
            st = new StringTokenizer(br.readLine()," ");

            int idx = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(player[idx].hp <= 0)
                continue;

            if(d == 0){
                up(idx);
            }else if(d == 1){
                right(idx);
            }else if(d == 2){
                down(idx);
            }else if(d == 3){
                left(idx);
            }
        }

        int answer = 0;
        for(int i = 1 ; i <= N ; i++){
            if(player[i].hp > 0){
                answer += (original[i] - player[i].hp);
            }
        }

        System.out.println(answer);
        br.close();
    }

    // 위 , 오 , 아, 왼

    static void up(int idx){
        moveList.clear();
        moveList.add(idx);
        Arrays.fill(isMove,false);

        boolean keep = true;
        isMove[idx] = true;

        loop : while(!moveList.isEmpty()){
            int cur = moveList.poll();

            for(int c = player[cur].y ; c < player[cur].y + player[cur].w ; c++){
                int nx = player[cur].x + dx[0];

                if(isOut(nx,c) || board[nx][c] == 2){
                    keep = false;
                    break loop;
                }

                if(pBoard[nx][c] != 0 && !isMove[pBoard[nx][c]]){
                    isMove[pBoard[nx][c]] = true;
                    moveList.add(pBoard[nx][c]);
                }
            }
        }

        if(!keep)
            return;

        move(idx,0);
    }

    static void clear(){
        for(int i =1 ; i<= L ; i++){
            for(int j =1 ; j <= L ; j++){
                tmpBoard[i][j] = 0;
            }
        }
    }

    static void move(int idx,int dir){

        clear();

        for(int k = 1 ; k <= N; k++){
            int mx = 0, my = 0;

            if(isMove[k]){
                mx = dx[dir];
                my = dy[dir];
            }

            for(int i = player[k].x ; i < player[k].x + player[k].h ; i++){
                for(int j = player[k].y ; j < player[k].y + player[k].w ; j++){
                    tmpBoard[i + mx][j + my] = pBoard[i][j];
                }
            }

            player[k].x += mx;
            player[k].y += my;
        }



        for(int i = 1 ; i <= L ; i++){
            for(int j = 1 ; j <= L ; j++){
                pBoard[i][j] = tmpBoard[i][j];
            }
        }

        for(int k = 1; k <= N ; k++){

            if(k == idx)
                continue;

            int count = 0;

            if(isMove[k]){
                for(int i = player[k].x ; i < player[k].x + player[k].h ; i++){
                    for(int j = player[k].y ; j < player[k].y + player[k].w ; j++){
                        if(board[i][j] == 1)
                            count++;
                    }
                }
            }

            player[k].hp -= count;

            if(player[k].hp <= 0){
                for(int i = player[k].x ; i < player[k].x + player[k].h ; i++){
                    for(int j = player[k].y ; j < player[k].y + player[k].w ; j++){
                        pBoard[i][j] = 0;
                    }
                }
            }
        }

    }

    // 위 , 오 , 아, 왼

    static void right(int idx){
        moveList.clear();
        moveList.add(idx);
        Arrays.fill(isMove,false);

        boolean keep = true;
        isMove[idx] = true;

        loop : while(!moveList.isEmpty()){
            int cur = moveList.poll();

            for(int r = player[cur].x ; r < player[cur].x + player[cur].h ; r++){
                int ny = player[cur].y + player[cur].w - 1 + dy[1];

                if(isOut(r,ny) || board[r][ny] == 2){
                    keep = false;
                    break loop;
                }

                if(pBoard[r][ny] != 0 && !isMove[pBoard[r][ny]]){
                    isMove[pBoard[r][ny]] = true;
                    moveList.add(pBoard[r][ny]);
                }
            }
        }

        if(!keep)
            return;

        move(idx,1);
    }

    static void down(int idx){
        moveList.clear();
        moveList.add(idx);
        Arrays.fill(isMove,false);

        boolean keep = true;
        isMove[idx] = true;

        loop : while(!moveList.isEmpty()){
            int cur = moveList.poll();

            for(int c = player[cur].y ; c < player[cur].y + player[cur].w ; c++){
                int nx = player[cur].x + player[cur].h -1 + dx[2];

                if(isOut(nx,c) || board[nx][c] == 2){
                    keep = false;
                    break loop;
                }

                if(pBoard[nx][c] != 0 && !isMove[pBoard[nx][c]]){
                    isMove[pBoard[nx][c]] = true;
                    moveList.add(pBoard[nx][c]);
                }
            }
        }

        if(!keep)
            return;

        move(idx,2);
    }

    static void left(int idx){
        moveList.clear();
        moveList.add(idx);
        Arrays.fill(isMove,false);

        boolean keep = true;
        isMove[idx] = true;

        loop : while(!moveList.isEmpty()){
            int cur = moveList.poll();

            for(int r = player[cur].x ; r < player[cur].x + player[cur].h ; r++){
                int ny = player[cur].y + dy[3];

                if(isOut(r,ny) || board[r][ny] == 2){
                    keep = false;
                    break loop;
                }

                if(pBoard[r][ny] != 0 && !isMove[pBoard[r][ny]]){
                    isMove[pBoard[r][ny]] = true;
                    moveList.add(pBoard[r][ny]);
                }
            }
        }

        if(!keep)
            return;

        move(idx,3);
    }



    static boolean isOut(int x,int y){
        if(x < 1 || x > L || y < 1 || y > L)
            return true;

        return false;
    }

    static void print(int[][] arr){
        for(int i =1 ; i <= L ; i++){
            for(int j= 1 ; j <= L ; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Node{
        int x;
        int y;
        int h;
        int w;
        int hp;

        Node(int x,int y,int h,int w,int hp){
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
            this.hp = hp;
        }
    }
}
