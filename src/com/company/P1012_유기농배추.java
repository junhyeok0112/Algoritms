package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1012_유기농배추 {

    static int T,N,M,K;
    static int[][] map ;
    static boolean[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visit = new boolean[N][M];
            int cnt = 0;        //정답
            Queue<Integer> q = new LinkedList<>();
            for(int i = 0 ; i<K ;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y= Integer.parseInt(st.nextToken());
                map[x][y] = 1;
                q.add(x);
                q.add(y);
            }

            while (!q.isEmpty()){
                int x = q.poll();
                int y = q.poll();
                if(!visit[x][y]){       //어짜피 배추있는 지점이니까 봐야함
                    bfs(x,y);
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int x , int y){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visit[x][y] = true;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0 ; i<4 ;i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx < 0 || nx>=N || ny<0 || ny>=M) continue;
                if(!visit[nx][ny] && map[nx][ny] == 1){
                    visit[nx][ny] = true;
                    q.add(nx);
                    q.add(ny);
                }
            }
        }
    }
}
