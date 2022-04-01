package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//
//class P4485 implements Comparable<P4485>{
//    int x;
//    int y;
//    int val;
//    P4485(int x, int y, int val){
//        this.x = x;
//        this.y = y;
//        this.val = val;
//    }
//
//    @Override
//    public int compareTo(P4485 o) {
//        return this.val - o.val;
//    }
//}

public class 실습05_녹색옷입은애가젤다지_최준혁 {

    static int n;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (true){
            n = Integer.parseInt(br.readLine());
            if(n == 0 ) break;
            sb.append("Problem").append(" ").append(++cnt).append(": ");
            map = new int[n][n];
            dist = new int[n][n];
            for(int i = 0 ; i<n ;i++) Arrays.fill(dist[i] , Integer.MAX_VALUE);
            for(int i = 0 ; i<n ;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j<n ;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<P4485> pq = new PriorityQueue<>();
            dist[0][0] = map[0][0];     //시작부터 가중치 존재
            pq.add(new P4485(0,0,dist[0][0]));  //pq에 값 넣기
            boolean[][] visit = new boolean[n][n];
            while (!pq.isEmpty()){
                P4485 r = pq.poll();
                int rx = r.x;
                int ry = r.y;
                if(visit[rx][ry] )continue;
                visit[rx][ry] = true;
                for(int i = 0 ; i<4 ;i++){
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if(nx<0 || nx>=n || ny<0 ||ny>=n) continue;
                    if(dist[nx][ny] > dist[rx][ry] + map[nx][ny]){
                        dist[nx][ny] = dist[rx][ry] + map[nx][ny];
                        pq.add(new P4485(nx,ny,dist[nx][ny]));
                    }
                }
            }
            sb.append(dist[n-1][n-1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
