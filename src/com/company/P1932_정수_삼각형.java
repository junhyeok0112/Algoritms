package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1932_정수_삼각형 {

    static int n;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for(int i = 0; i<n ;i++){
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            for(int j = 0 ; j<=i ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];
        if(n != 1 ){
            dp[1][0] = map[0][0] + map[1][0];
            dp[1][1] = map[0][0] + map[1][1];
        }else{
            System.out.println(map[0][0]);
            return;
        }

        for(int i = 2; i< n;i++){
            for(int j = 0; j<=i; j++){
                if(j == 0){
                    dp[i][j] = dp[i-1][j] + map[i][j];  //끝에 있으면 그 값을 더함
                } else if(j == i){
                    dp[i][j] = dp[i-1][j-1] + map[i][j];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j] + map[i][j] ,dp[i-1][j-1] + map[i][j]);
                }

            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i<n ;i++){
            max = Math.max(max, dp[n-1][i]);        //마지막 경로중 최대값 찾기
        }

        System.out.println(max);

    }
}
