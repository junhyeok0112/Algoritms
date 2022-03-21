package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class P14502{
    int x ;
    int y;
    P14502(int x,  int y ){
        this.x = x;
        this.y = y;
    }
}


public class P14502_연구소_re {

    static int n,m;
    static int ans = -1;
    static int zeroCnt = 0;
    static int[][] map;
    static boolean[][] visit;
    static ArrayList<P14502> q = new ArrayList<>();
    static ArrayList<P14502> zero = new ArrayList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m];
        for(int i = 0 ; i<n ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<m ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2 ){
                    q.add(new P14502(i,j));
                } else if(map[i][j] == 0){
                    zero.add(new P14502(i,j));
                    zeroCnt++;
                }
            }
        }
        int firstZero = zeroCnt;    //초기값 저장

        //3중 for문으로 벽세우기
        int zeroSize = zero.size();
        for(int i = 0 ; i<zeroSize; i++){
            int firstX = zero.get(i).x;
            int firstY = zero.get(i).y;
            map[firstX][firstY] = 1;  //벽 세우기
            zeroCnt--;
            for(int j = i+1; j<zeroSize; j++){
                int secondX = zero.get(j).x;
                int secondY = zero.get(j).y;
                map[secondX][secondY] = 1;  //벽 세우기
                zeroCnt--;
                for(int k = j+1 ; k<zeroSize; k++){
                    int thirdX = zero.get(k).x;
                    int thirdY = zero.get(k).y;
                    map[thirdX][thirdY] = 1;  //벽 세우기
                    zeroCnt--;                  //벽 세웠음
                    int qSize = q.size();   //바이러스 있는 위치
                    for(int t = 0 ; t<qSize; t++){
                        if(!visit[q.get(t).x][q.get(t).y]){
                            bfs(q.get(t).x , q.get(t).y);   //해당 위치에서 전파
                        }
                    }
                    ans = Math.max(ans , zeroCnt);      //최소 갯수갱신
                    zeroCnt = firstZero;                //zeroCnt 갱신
                    for(int t = 0 ; t<n ;t++){
                        Arrays.fill(visit[t], false);
                    }
                    map[thirdX][thirdY] = 0;    //계산후 되돌려 놓기
                }
                map[secondX][secondY] = 0;
            }
            map[firstX][firstY] = 0;
            zeroCnt = firstZero;
        }

        System.out.println(ans);

    }

    private static void bfs(int x , int y ) {
        Queue<P14502> temp = new LinkedList<>();
        temp.add(new P14502(x,y));
        visit[x][y] = true;
        while (!temp.isEmpty()){
            P14502 r = temp.poll();
            int rx = r.x;
            int ry =r.y;
            for(int i = 0 ; i<4 ;i++){
                int nx = rx+dx[i];
                int ny = ry + dy[i];
                if(nx<0 || nx>= n ||ny <0 ||ny>=m || visit[nx][ny] ||map[nx][ny]!=0 ) continue;
                visit[nx][ny] = true;
                temp.add(new P14502(nx,ny));
                zeroCnt--;          //갯수 감소
            }
        }
    }
}
