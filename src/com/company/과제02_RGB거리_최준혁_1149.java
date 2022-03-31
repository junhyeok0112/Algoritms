package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 과제02_RGB거리_최준혁_1149 {

    static int n;
    static int[][] map;
    static int[][] dp ;       //n번째에 집을 칠할떄 필요한 비용의 최소값 , 이때 N번째를 R,G,B 로 칠하는 경우를 나눠줌
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][3];      //0 R ,1 G ,2 B
        dp = new int[n+1][3];
        for(int i = 1 ; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        dp[1][0] = map[1][0];
        dp[1][1] = map[1][1];
        dp[1][2] = map[1][2];
        for(int i =2 ; i<=n ;i++){
            dp[i][0] = map[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);    //빨간색 칠하려면 i번째 빨간색 비용 + 이전 집에 파란 or 초록색 값중 최소
            dp[i][1] = map[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);    //파란색 칠하려면 i번째 파란색 비용 + 이전 집에 빨간 or 초록색 값중 최소
            dp[i][2] = map[i][2] + Math.min(dp[i-1][0] ,dp[i-1][1]);    //초록색 칠하려면 i번째 초록색 비용 + 이전 집에 파란 or 빨간색 값중 최소
        }

        //이제 R,G,B 칠한 값 중 최소값 출력
        int ans = dp[n][0];
        for(int i =1 ; i<=2; i++){
            ans = Math.min(ans , dp[n][i]);
        }
        System.out.println(ans);
    }
}
