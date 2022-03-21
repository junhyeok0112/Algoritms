package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A30_토마토_최준혁 {

    static int m,n;
    static int[][] map;
    static boolean[][] visit;
    static int ans = 0 ;
    static int total = 0 ;  //익지 않은 토마토 갯수 (0의 갯수)
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m];
        for(int i = 0 ; i <n ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0 ) total++;
                else if(map[i][j] == 1) {
                    q.add(i);
                    q.add(j);
                    visit[i][j] = true;
                }
            }
        }
        if(total == 0) {
            sb.append(0);
            System.out.println(sb.toString());
            return;
        }
        bfs();
        if(total == 0 ){
            sb.append(ans-1 );      //전부 1로 채워진 상태에서도 BFS가 추가로 1번 더 도므로 ans가 1이 더 증가되어있는 상태이다.
        } else {
            sb.append(-1);
        }
        System.out.println(sb.toString());

    }

    static void bfs(){
        while (!q.isEmpty()){
            int qsize = q.size() / 2;
            for(int t = 0; t<qsize; t++){
                int rx = q.poll();
                int ry = q.poll();
                for(int i = 0 ; i<4 ;i++){
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if(nx< 0 || nx>= n || ny<0 || ny>=m || visit[nx][ny] || map[nx][ny] != 0) continue; //이미 토마토가 있거나 토마토가 없는 곳이면 넘어감
                    visit[nx][ny] = true;
                    map[nx][ny] = 1;
                    q.add(nx);
                    q.add(ny);
                    total--;
                }
            }
            ans++;  //하루 지남
        }
    }
}
