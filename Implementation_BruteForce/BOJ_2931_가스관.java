package Implementation_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2931_가스관 {

    static int n = 0;
    static int m = 0;

    static int mX,mY,zX,zY,mDir;

    static char[][] board;

    // -> <- ^  v
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static Queue<Node> q = new LinkedList<>();
    static boolean[][][] v;

    static int findX,findY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];
        v = new boolean[n+1][m+1][4];

        for(int i= 1; i <= n ;i++){
            char[] line = br.readLine().toCharArray();

            for(int j = 1; j <= m ; j++){

                board[i][j] = line[j-1];

                if(board[i][j] == 'M'){
                    mX = i;
                    mY = j;
                }

                if(board[i][j] == 'Z'){
                    zX = i;
                    zY = j;
                }
            }
        }

        // 모스크바에서 인접한 한 좌표를 찾는다. 그리고 해당 좌표로 가기 위한 방향을 함께 저장한다.
        for(int i=0;i<4;i++){
            int nx = mX + dx[i];
            int ny = mY + dy[i];

            if(nx < 1 || nx > n || ny < 1 || ny > m)
                continue;


            if(board[nx][ny] != '.') {
                mDir = i;
                q.add(new Node(mX, mY, mDir));
                v[mX][mY][i] = true;
            }
        }

        find();
        System.out.println(findX+" "+findY+" "+board[findX][findY]);
        br.close();
    }


    // -> <- ^  v
    static void make(int come){

        // 비어있는 좌표에서 인접한 좌표에 가스관이 있는 경우 보내야 하는 방향
        boolean[] need = new boolean[4];

        need[come] = true;

        for(int i=0; i <4; i++){

            if(i == come)
                continue;

            int nx = findX + dx[i];
            int ny = findY + dy[i];

            if(nx < 1 || nx > n || ny < 1 || ny > m)
                continue;

            if(board[nx][ny] == '.')
                continue;

            if(i == 0){
                if(board[nx][ny] == '-' || board[nx][ny] == '+' || board[nx][ny] == '3' || board[nx][ny] == '4'){
                    need[i] = true;
                }
            }else if(i == 1){
                if(board[nx][ny] == '-' || board[nx][ny] == '+' || board[nx][ny] == '1' || board[nx][ny] == '2'){
                    need[i] = true;
                }
            }else if(i == 2){
                if(board[nx][ny] == '|' || board[nx][ny] == '+' || board[nx][ny] == '1' || board[nx][ny] == '4'){
                    need[i] = true;
                }
            }else if(i == 3){
                if(board[nx][ny] == '|' || board[nx][ny] == '+' || board[nx][ny] == '2' || board[nx][ny] == '3'){
                    need[i] = true;
                }
            }
        }

        // 보내야 하는 방향에 따라 해당 가스관을 채운다.
        if(need[0] && need[1] && need[2] && need[3]){
            board[findX][findY] = '+';
        }else if(need[0] && need[1]){
            board[findX][findY] = '-';
        }else if(need[2] && need[3]){
            board[findX][findY] = '|';
        }else if(need[0] && need[3]){
            board[findX][findY] = '1';
        }else if(need[0] && need[2]){
            board[findX][findY] = '2';
        }else if(need[1] && need[2]){
            board[findX][findY] = '3';
        }else if(need[1] && need[3])
            board[findX][findY] = '4';
    }

    // -> <- ^  v

    static void find(){

        while(!q.isEmpty()){
            Node cur = q.poll();

            int nx = cur.x + dx[cur.dir];
            int ny = cur.y + dy[cur.dir];

            if(nx < 1 || nx > n || ny < 1 || ny > m)
                continue;

            // 비어있다면 해당 좌표가 해커가 지운 좌표이다.
            if(board[nx][ny] == '.'){
                findX = nx;
                findY = ny;

                // 비어있는 좌표에 해당하는 가스관을 찾는 메서드
                make(opposite(cur.dir));
                return;
            }

            // 자그레브에 도착한 경우
            if(board[nx][ny] == 'Z')
                continue;

            // 해당 가스관에서 움직여야 하는 방향
            int nDir = 0;

            if(board[nx][ny] == '|'){
                nDir = sero(cur.x,nx);
            }else if(board[nx][ny] == '-'){
                nDir = garo(cur.y,ny);
            }else if(board[nx][ny] == '1'){
                nDir = one(cur.y,ny);
            }else if(board[nx][ny] == '2'){
                nDir = two(cur.y,ny);
            }else if(board[nx][ny] == '3'){
                nDir = three(cur.y,ny);
            }else if(board[nx][ny] == '4'){
                nDir = four(cur.y,ny);
            }else if (board[nx][ny] == '+') {
                nDir = plus(cur.x,cur.y,nx,ny);
            }

            if(!v[nx][ny][nDir]){
                v[nx][ny][nDir] = true;
                q.add(new Node(nx,ny,nDir));
            }
        }

    }

    static int sero(int curX,int nX){
        if(curX < nX){
            return 3;
        }

        else if(curX > nX)
            return 2;

        return -1;
    }

    static int garo(int curY,int nY){
        if(curY > nY){
            return 1;
        }

        else if(curY < nY)
            return 0;

        return -1;
    }

    static int one(int curY,int nY){
        if(curY > nY){
            return 3;
        }

        else if(curY == nY)
            return 0;

        return -1;
    }

    static int two(int curY,int nY){
        if(curY > nY){
            return 2;
        }

        else if(curY == nY)
            return 0;

        return -1;
    }

    static int three(int curY,int nY){
        if(curY < nY){
            return 2;
        }
        return 1;
    }

    static int four(int curY,int nY){
        if(curY < nY){
            return 3;
        }

        return 1;
    }

    static int plus(int curX,int curY,int nX,int nY){
        if(curX < nX)
            return 3;

        else if(curX > nX)
            return 2;

        if(curY > nY)
            return 1;

        return 0;
    }

    static int opposite(int cur){
        if(cur == 0)
            return 1;

        if(cur == 1)
            return 0;

        if(cur == 2)
            return 3;

        return 2;
    }
}
class Node{
    int x;
    int y;

    int dir;

    Node(int x,int y,int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
