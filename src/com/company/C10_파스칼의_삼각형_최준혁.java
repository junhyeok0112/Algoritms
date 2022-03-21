package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C10_파스칼의_삼각형_최준혁 {

    static int n , t;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[][] dp = new int[11][11];
        for(int i = 0 ; i<=10 ;i++){
            dp[i][0] = 1;
            dp[i][i] = 1;       //nC0 과 nCn 은 1로
        }

        for(int i = 2 ; i<=10 ;i++){
            for(int j = 1; j<i ; j++){
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }
        t = Integer.parseInt(br.readLine());
        int tc = 0;
        while (tc++<t){
            sb.append("#").append(tc).append("\n");
            n =Integer.parseInt(br.readLine());
            for(int i = 0 ; i<n; i++){
                for(int j = 0 ; j<=i; j++){
                    sb.append(dp[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());

    }
}
