package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 실습01_P2636_치즈_최준혁 {

    static int n,m ;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int total = 0 ;          //원래 있던 치즈 개수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0 ; i<n ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<m ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) total++;
            }
        }

        //BFS가 어떻게 동작하는지 제대로 이해하지 못하여서 못풀었던 문제.
        //치즈가 전부 없어질때까지 BFS를 반복한다.
        //단 BFS를 모든 점을 탐색하는게 아니라, map[i][j] = 0 인 부분 즉 ,공기인 부분들만 큐에 넣어서 탐색한다.
        //이때 nx,ny 가 치즈면 녹이고 따로 저장하지 않는다 -> 이 과정이 끝나면 시간이 증가한 것이다.
        //위 과정을 반복하면 공기에 닿아있는 치즈들을 하나씩 녹일 수 있다.
        //나는 녹인 지점에서 다시 탐색하려고해서 계속 풀 수 없었다.
        int time = 0;
        int ans = 0;
        while (total !=0){
            time++;
            ans = total;        //이번 time에 녹이기 전까지의 갯수 저장
            bfs();  //치즈 녹임    녹인 후 total이 0이면 시간은 0이되었을때 , ans는 0이 되기 바로 이전 값들이 저장된다.
        }
        System.out.println(time);
        System.out.println(ans);

    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        q.add(0);
        visit = new boolean[n][m];
        visit[0][0] = true;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0 ; i<4; i++){
                int nx = rx +dx[i];
                int ny = ry +dy[i];
                if(nx<0 || nx>=n || ny< 0 || ny>=m || visit[nx][ny]) continue;
                if(map[nx][ny] == 1){       //치즈면 그냥 녹임
                    visit[nx][ny] = true;
                    map[nx][ny] = 0;
                    total--;
                }else {
                    visit[nx][ny] = true;
                    q.add(nx);
                    q.add(ny);
                }
            }
        }
    }
}

