package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class P2638 {
    int x, y;

    P2638(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class P2638_치즈 {

    static int n, m;
    static int[][] map;
    static int[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<P2638> cheese = new LinkedList<>();    //치즈들 위치
    static Queue<P2638> melt = new LinkedList<>();      //녹을 치즈들 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheese.offer(new P2638(i,j));
            }
        }
        int time = 0;
        int total = n*m;
        while (true){
            visit = new int[n][m];
            //전부다 녹았으면
            if(chkAir(0,0) == total ){
                System.out.println(time);
                return;
            }

            //녹일 치즈들 체크
            chkCheese();

            //치즈 녹이기
            melting();

            time++;
        }

    }

    private static void melting() {
        while (!melt.isEmpty()){
            P2638 r = melt.poll();
            map[r.x][r.y] = 0;
        }
    }

    private static void chkCheese() {
        int qsize = cheese.size();
        for(int i = 0 ; i<qsize; i++){
            int cnt = 0;
            P2638 r = cheese.poll();
            int rx = r.x;
            int ry = r.y;
            for(int j = 0 ; j<4 ;j++){
                int nx = rx + dx[j];
                int ny = ry + dy[j];
                if(nx < 0 || nx >=n ||ny <0 || ny>=m) continue;
                //해당 지점이 외부 (visit != 0) 공기 (map = 0 )일 때 증가
                if(map[nx][ny] == 0 && visit[nx][ny] != 0) cnt++;
            }
            if(cnt >=2 ) melt.add(new P2638(rx,ry));
            else cheese.add(new P2638(rx,ry));
        }
    }

    //외부 공기 체크
    private static int chkAir(int x ,int y) {
        visit[x][y] = 1;        //방문 체크 -> 외부 공기인지 1로 체크

        for(int i = 0 ; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            //범위 안이고 공기이고 방문 안 한곳이면 외부 공기
            if(nx>=0 && nx<n && ny>=0 && ny<m && map[nx][ny] == 0 && visit[nx][ny] == 0){
                visit[x][y] += chkAir(nx,ny);
            }
        }

        return visit[x][y];
    }
}
