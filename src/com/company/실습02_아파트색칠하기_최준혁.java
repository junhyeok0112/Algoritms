package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 실습02_아파트색칠하기_최준혁 {

    static int n;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][2];       //0은 노란색으로 끝나는 경우 1 은 파란색으로 끝나는 경우

        dp[1][0] = 1;
        dp[1][1] = 1;
        int ans = 2;
        for(int i =2 ; i<= n ;i++){
            dp[i][0] = dp[i-1][0] + dp[i-1][1]; //노란색은 이전색에 관계 없이 올 수 있음
            dp[i][1] = dp[i-1][0];              //파란색은 이전에 노란색이여야만 올 수 있음
            if (i == n){
                ans = dp[i][0] + dp[i][1];
            }
        }

        System.out.println(ans);
        //노란색으로 끝나는 경우 -> 2배
        //파란색으로 끝나는 경우 -> 그대로
    }
}
