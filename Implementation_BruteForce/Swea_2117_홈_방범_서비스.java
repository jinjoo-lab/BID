package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_2117_홈_방범_서비스 {

    static int t = 0;
    static int n = 0;
    static int m = 0;
    static int result = 0;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new int[n+1][n+1];


            for(int i = 1 ; i <= n ; i++){
                st = new StringTokenizer(br.readLine(), " ");

                for(int j = 1 ; j <= n ; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = 0;

            for(int i =1 ; i <= n ; i++){
                for(int j =1 ; j<=n ;j++){
                    go(i,j);
                }
            }

            sb.append("#"+a+" "+result+"\n");
        }
        System.out.print(sb);
        br.close();
    }

    static class Node{
        int x;
        int y;

        Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};

    static void go(int x,int y){
        int[][] v = new int[n+1][n+1];
        Queue<Solution.Node> q = new ArrayDeque<>();
        v[x][y] = 1;
        int count = (board[x][y] == 1) ? 1 : 0;
        q.add(new Solution.Node(x,y));

        int find = 1;

        while(!q.isEmpty()){
            Solution.Node cur = q.poll();

            for(int i = 0; i < 4 ; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || ny < 1 || nx > n || ny > n)
                    continue;

                if(v[nx][ny] != 0)
                    continue;

                v[nx][ny] = v[cur.x][cur.y] + 1;


                if(v[nx][ny] > find){

                    int tmpCost = (count * m) - getRunCost(find);
                    if(tmpCost >= 0){
                        result = Math.max(result,count);
                    }
                    find += 1;
                }

                if(board[nx][ny] == 1)
                    count += 1;

                q.add(new Solution.Node(nx,ny));
            }
        }

        int tmpCost = (count * m) - getRunCost(find);
        if(tmpCost >= 0){
            result = Math.max(result,count);
        }

    }

    static int getRunCost(int c){
        return (c * c) + ((c - 1) * (c - 1));
    }

}
