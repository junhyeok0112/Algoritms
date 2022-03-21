package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1149_RGB거리 {

    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][3];
        dp = new int[N+1][3];       //dp[n][0] -> 빨간색을 칠했을 때 최소값
        for(int i = 1 ; i<=N ;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());
            map[i][0] = num1;
            map[i][1] = num2;
            map[i][2] = num3;
        }

        //초기값 셋팅
        dp[1][0] = map[1][0];
        dp[1][1] = map[1][1];
        dp[1][2] = map[1][2];

        for(int i =2 ; i<=N ;i++){
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + map[i][0]; //i번쨰 집에 빨간색 칠했을 때의 값 이 때 앞에 집은 빨간색이 아니여야함
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + map[i][1]; //i번쨰 집에 초록색 칠했을 때의 값 이 때 앞에 집은 초록색이 아니여야함
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + map[i][2]; //i번쨰 집에 파란색 칠했을 때의 값 이 때 앞에 집은 파란색이 아니여야함
        }

        //마지막 집까지 다 칠했을 떄 최소값 구하기
        System.out.println(Math.min(Math.min(dp[N][0] , dp[N][1]) , dp[N][2]));

    }
}
