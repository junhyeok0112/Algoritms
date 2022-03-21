package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2225_합분해 {

    static int N ,K;
    static int[][] dp = new int[201][201];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for(int i = 0 ;i <=200 ; i++){  //0을 만들 수 있는 가지수는 1개뿐이다 0 ,00, 000 등 또 1을 K개의 정수를 더해서 만들 수 있는 가지수는 K개뿐이다
            //001,010,100 등 -> 따라서 dp[K][N] 은 K개의 정수를 더해서 N을 만들 수 있는 가지수이다.
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        for(int i = 2; i<=200;i++){
            dp[1][i] = 1;
        }

        for(int i = 1; i<=200;i++){
            for(int j = 1 ; j<=200;j++){
                dp[i][j] = (dp[i][j-1]  + dp[i-1][j]) % 1000000000 ;
//                for(int k = 0; k<=j ;k++) {
//                    dp[i][j] += dp[i - 1][k] % 1000000000;
//                }

            }
        }

        System.out.println(dp[K][N]);       //저장을 K개 자리수 사용하는 걸 행 , 만들 수 N을 열로 했으므로 [K][N] 으로 출력


    }
}
