package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD
//1249
class P1249 implements Comparable<P1249>{
    int x ;
    int y;
    int val;
    P1249(int x,int y ,int val){
        this.x= x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(P1249 o) {
        return this.val - o.val;
    }
}

public class 실습12_보급로_최준혁 {

    static int t,n;
    static int[][] map;
    static int[][] dist;
    static boolean[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        int tc = 0;
        StringBuilder sb = new StringBuilder();
        while (tc++<t){
            sb.append("#").append(tc).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];            //0,0 에서 n-1,n-1까지
            dist = new int[n][n];
            visit = new boolean[n][n];
            for(int i = 0 ;i <n; i++){
                String str = br.readLine();
                for(int j=0; j<n;j++){
                    map[i][j] = str.charAt(j) - '0';
                }
            }
            for(int i = 0 ; i<n;i++) Arrays.fill(dist[i] , 100000000);

            dist[0][0] = 0;     //시작점은 0
            PriorityQueue<P1249> pq = new PriorityQueue<>();
            pq.add(new P1249(0,0,map[0][0]));
            while (!pq.isEmpty()){
                P1249 r = pq.poll();
                int rx = r.x;
                int ry = r.y;
                if(visit[rx][ry]) continue;
                visit[rx][ry] = true;
                for(int i = 0 ;i<4; i++){
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if (nx<0 ||nx>= n ||ny<0 || ny>=n) continue;
                    if(dist[nx][ny] > dist[rx][ry] + map[nx][ny]){
                        dist[nx][ny] = dist[rx][ry] + map[nx][ny];
                        pq.add(new P1249(nx,ny,dist[nx][ny]));
                    }
                }
            }
            sb.append(dist[n-1][n-1]).append("\n");
        }
        System.out.println(sb);
    }
}
