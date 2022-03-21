package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11057_오르막수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N+1][10];  //dp[n][i] 는 n개의 숫자를 만드는데 끝에 i가 오는 오르막수의 개수
        for(int i= 0 ;i<10;i++){
            dp[1][i] = 1;
        }

        for(int i =2 ; i<=N ;i++){
            for(int j = 0 ; j<10;j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = (dp[i][j] + dp[i-1][k]) % 10007;
                }
            }
        }

        int res = 0;
        for(int i = 0 ; i<10;i++){
            res = (dp[N][i] + res ) % 10007;
        }
        System.out.println(res);

    }
}
