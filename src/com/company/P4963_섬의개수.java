package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4963_섬의개수 {

    static int w,h,t;           //map[h][w] 로 헤야함
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,1,0,0,1,1,-1,-1};
    static int[] dy = {0,0,1,-1,-1,1,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        while(!str.equals("0 0")){
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new int[h+1][w+1];
            visit = new boolean[h+1][w+1];
            int cnt = 0;
            for(int i = 1 ; i<= h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j =1 ; j<=w ;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i = 1 ; i<=h ;i++){
                for(int j =1 ; j<=w ; j++){
                    if(map[i][j] == 1 && !visit[i][j]){ //땅이고 방문 안했을 때
                        bfs(i,j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
            str = br.readLine();
            st = new StringTokenizer(str);
        }
    }

    static void bfs(int x , int y ){
        Queue<Integer> q = new LinkedList<>();
        visit[x][y] = true;
        q.add(x);
        q.add(y);
        while(!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0; i<8 ;i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx>=1 && nx <=h && ny >=1 && ny<=w){
                    if(!visit[nx][ny] && map[nx][ny] == 1){
                        visit[nx][ny] = true;
                        q.add(nx);
                        q.add(ny);
                    }
                }
            }
        }
    }
}
