package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3184_양 {

    static int R,C;
    static char[][] map;
    static boolean[][] visit;
    static int wolf = 0 , sheep = 0;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visit = new boolean[R][C];
        for(int i = 0 ; i <R ; i++){
            String s = br.readLine();
            for(int j = 0 ; j<C ; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'v'){
                    wolf++;
                }
                if(map[i][j] == 'o' ){
                    sheep++;
                }
            }
        }

        for(int i = 0 ; i<R ; i++){
            for(int j = 0 ; j<C ; j++){
                if(map[i][j] != '#'  && !visit[i][j]) {
                    bfs(i, j);
                }
            }
        }


        System.out.println(sheep + " " + wolf);
    }

    static void bfs(int x , int y){
        Queue<Integer> q = new LinkedList<>();
        int curW = 0 ;          //영역 안에 있는 늑대 수
        int curS = 0;           //영역 안에 있는 양 수
        q.add(x);
        q.add(y);
        visit[x][y] = true;
        if(map[x][y] == 'v') curW++;
        if(map[x][y] == 'o') curS++;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0 ; i<4 ; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx <0 || nx>=R || ny<0 ||ny >=C) continue;
                if(!visit[nx][ny] && map[nx][ny] != '#'){     //방문 안한 점이면
                    visit[nx][ny] = true;
                    q.add(nx);
                    q.add(ny);
                    if(map[nx][ny] == 'v') curW++;
                    if(map[nx][ny] == 'o') curS++;
                }
            }
        }

        if(curW < curS){        //양이 늑대보다 많음
            wolf -= curW;
        } else{
            sheep -= curS;
        }
    }

}
