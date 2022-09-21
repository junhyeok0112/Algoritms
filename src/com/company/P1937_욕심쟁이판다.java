package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1937_욕심쟁이판다 {

    static int n;
    static int[][] map;
    static int[][] dp;      //해당 지점에서 출발했을 때 이동할 수 있는 최대 값
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][] visit;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for(int i = 0 ; i<n ;i++) Arrays.fill(dp[i] , -1);
        for(int i = 0 ; i<n ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<n ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //visit 배열 생성 시 시간초과
        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ; j<n ;j++){
                dfs(i,j);   //시작 위치
            }
        }
        int max = -1;
        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ; j<n ;j++){
               max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(max);
    }

    static int dfs(int x, int y){

        //방문 한 곳이 이전에 더 많이 이동해서 방문 가능한 곳이면 그만 봄
        if(dp[x][y] != -1) return dp[x][y];
        else dp[x][y] = 1;

        for(int i = 0 ; i<4 ;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 ||nx>=n ||ny <0 ||ny>=n ) continue;
            if(map[x][y] >= map[nx][ny]) { //못가는 곳이면
                continue;
            }
            dp[x][y] = Math.max(dp[x][y] ,1 + dfs(nx,ny));     //갈 수 있는 곳이면 감
        }

        return dp[x][y];
    }
}
