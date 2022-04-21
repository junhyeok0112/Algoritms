package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class P17135 implements Comparable<P17135>{
    int x;
    int y;
    P17135(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(P17135 o) {
        return this.y - o.y;
    }
}

public class 실습15_캐슬디펜스_최준혁 {

    static int n,m,d;
    static int[][] map;
    static int[][] tempMap;
    static int enemy = 0;
    static int ans = -1;
    static int[] dx = {0,-1,0};
    static int[] dy = {-1,0,1};
    static Queue<P17135> removeQ = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n+1][m];              //n번째 행에는 궁수 존재
        tempMap = new int[n+1][m];
        for(int i = 0 ; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ;j<m ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                tempMap[i][j] = map[i][j];
            }
        }

        int firstEnemy = enemy;
        for(int i = 0 ; i<m-2;i++){
            map[n][i] = 2;      //궁수 배치
            for(int j=  i+1 ; j<m-1; j++){
                map[n][j] = 2;
                for(int k = j+1 ;k<m; k++){
                    map[n][k] = 2;
                    copy();
                    play();
                    ans = Math.max(ans, enemy);
                    enemy = firstEnemy;
                    map[n][k] = 0;
                }
                map[n][j] =0;
            }
            map[n][i] =0;
        }
        System.out.println(ans);

    }

    private static void copy() {
        for(int i = 0 ;i<=n ;i++){
            for(int j = 0 ;j<m; j++){
                tempMap[i][j] = map[i][j];
            }
        }
    }

    private static void play() {
        Queue<P17135> q = new LinkedList<>();
        while (check()){
            //궁수 위치 셋팅 후 공격
            for(int i = 0 ; i<m; i++){
                if(tempMap[n][i] == 2){
                    bfs(n,i);
                }
            }
            remove();
            //적군 전진
            move();
        }

    }

    //공격한 적 제거하는 함수
    private static void remove() {
        boolean[][] visit = new boolean[n+1][m];
        while (!removeQ.isEmpty()){
            P17135 r = removeQ.poll();
            int rx = r.x;
            int ry = r.y;
            if(visit[rx][ry]) continue;
            tempMap[rx][ry] =0;
            visit[rx][ry] =true;
            enemy++;
        }
    }

    private static void move() {
        for(int i = 0; i<m ;i++){
            for(int j= n-1 ; j>=0 ;j--){
                if(j == 0){
                    tempMap[j][i] = 0;
                } else{
                    tempMap[j][i] = tempMap[j-1][i];//땡기기
                }
            }
        }
    }

    private static void bfs(int n, int j) {
        Queue<P17135> q = new LinkedList<>();
        boolean[][] visit = new boolean[n+1][m];
        q.add(new P17135(n,j));
        int level = 1;
        while (!q.isEmpty()){
            int qsize = q.size();
            for(int t = 0 ;t<qsize; t++){
                P17135 r = q.poll();
                int rx = r.x;
                int ry = r.y;
                for(int i = 0 ; i<3 ;i++){
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if(nx<0 ||nx>=n+1 ||ny<0 ||ny>=m ||(visit[nx][ny])) continue;
                    if(tempMap[nx][ny] == 1) {          //적 만나면 쏘고 return
                        removeQ.add(new P17135(nx,ny));
                        return;
                    }
                    visit[nx][ny] =true;
                    q.add(new P17135(nx,ny));
                }
            }
            level++;
            if(level > d) return;
        }
    }

    private static boolean check() {
        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ; j<m ;j++){
                if(tempMap[i][j] == 1) return true;
            }
        }
        return false;
    }
}
