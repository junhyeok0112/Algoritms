package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 실습03_막대색칠하기_최준혁 {

    static int n;
    static int[] dp ;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        dp[1] = 2;
        dp[2] = 5;
        for(int i = 3 ; i<=n;i++){
            dp[i] = 2 * dp[i-1] + dp[i-2];
        }
        System.out.println(dp[n]);
    }
}
