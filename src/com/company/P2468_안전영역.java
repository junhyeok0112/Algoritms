package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2468_안전영역 {

    static int n;
    static int[][] map;
    static int ans = 1; //최소 1
    static int max_Height = 0;      //높이는 1 ~ 100
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i = 0 ; i<n ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max_Height = Math.max(max_Height , map[i][j]);
            }
        }

        int cur_height = 1;
        while (cur_height <= max_Height){
            boolean[][] visit = new boolean[n][n];
            //cur_height 이하인 지점들 채우기
            for(int i = 0 ; i<n ;i++){
                for(int j =0; j<n; j++){
                    if(map[i][j] <= cur_height) visit[i][j] = true;
                }
            }
            int cnt = 0 ; //영역의 갯수
            for(int i = 0 ; i<n ;i++){
                for(int j = 0 ;j<n; j++){
                    if(!visit[i][j]){
                        bfs(i,j , visit);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans , cnt);
            cur_height++;       //물 높이 증가
        }
        System.out.println(ans);


    }

    private static void bfs(int i, int j , boolean[][] visit) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        q.offer(j);
        visit[i][j] = true;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int t = 0 ; t<4 ; t++){
                int nx = rx + dx[t];
                int ny = ry + dy[t];
                if (nx < 0 || nx >=n || ny <0 || ny>= n ||visit[nx][ny]) continue;
                visit[nx][ny] = true;
                q.offer(nx);
                q.offer(ny);
            }
        }
    }
}
