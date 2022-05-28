package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2193_이친수_re {

    static int n;
    static long[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[(n+1)][2];
        dp[1][0] = 0;
        dp[1][1] = 1;

        for(int i = 2; i<=n ;i++){
            //0으로 끝났을땐 2뭐가 오든 상관 없음 ->2개 , 1로 끝났을땐  0밖에 못옴
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }
        System.out.println(dp[n][0] + dp[n][1]);
    }
}
