package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1303_전쟁_전투 {

    static int n,m;
    static char[][] map;
    static boolean[][] visit;
    static int wsum = 0;
    static int bsum = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[m][n];
        visit = new boolean[m][n];
        for(int i = 0 ; i<m ;i++){
            String str = br.readLine();
            for(int j = 0 ; j<n; j++){
                map[i][j] = str.charAt(j);
            }
        }

        for(int i = 0 ; i <m ; i++){
            for(int j = 0 ; j<n ;j++){
                if(visit[i][j]) continue;
                bfs(i,j,map[i][j]);
            }
        }
        System.out.println(wsum + " " + bsum);
    }

    static void bfs(int x , int y, char color){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visit[x][y] = true;
        int cnt = 1;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0 ; i<4 ; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx<0 || nx>= m || ny <0 || ny>= n || visit[nx][ny] || map[nx][ny] != color) continue;
                q.add(nx);
                q.add(ny);
                cnt++;
                visit[nx][ny] = true;
            }
        }
        if(color == 'W') wsum += Math.pow(cnt , 2);
        else bsum += Math.pow(cnt, 2);
    }
}
