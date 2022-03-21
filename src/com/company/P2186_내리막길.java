package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//항상 높이가 더 낮은 지점으로만
public class P2186_내리막길 {

    static int n,m,h;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];
        for(int i = 0 ; i<n ;i++) Arrays.fill(dp[i], -1);

        for(int i = 0 ; i<n ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0,0,n-1,m-1));

    }

    static int dfs(int curX, int curY , int endX ,int endY){
        if(curX == endX && curY == endY){
            dp[curX][curY] = 1;
            return 1;
        }

        if(dp[curX][curY] != -1) return dp[curX][curY];

        dp[curX][curY] = 0;     //방문 체크
        for(int i = 0 ; i<4 ;i++){
            int nx = curX + dx[i];
            int ny = curY + dy[i];
            if(nx>=0 && nx<n && ny>=0 && ny<m && map[curX][curY] > map[nx][ny]){
                dp[curX][curY] += dfs(nx,ny,endX,endY);
            }
        }
        return dp[curX][curY];

    }

}
