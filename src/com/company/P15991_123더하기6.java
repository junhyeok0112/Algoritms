package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P15991_123더하기6 {

    static int T , N;
    static long[] dp ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        dp = new long[100001];
        dp[0] = 1;      //1, 2, 3 2개씩으로 만들 수 있는 경우 즉 2 4 6 일때 그전 값들  + (2,2) 같은 값들
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        for(int i = 4 ; i<100001; i++){
            int cnt = 2;
            while(cnt <=6 && i-cnt >=0){        //최대 6까지빼고 i-cnt가 0보다 크거나 같아야함
                dp[i] +=dp[i-cnt] % 1000000009;
                cnt+=2;
            }
            dp[i] %= 1000000009;
        }
        for(int i = 0; i<T ;i++){
            N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
    }
}
