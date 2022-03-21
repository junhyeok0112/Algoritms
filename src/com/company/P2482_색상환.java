package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2482_색상환 {
    static int n,k;
    static int mod = 1000000003;
    static int[][] dp ;

    //벽돌과 비슷한 문제라고 생각
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        dp = new int[1001][1001];
        if(2*k >n){
            System.out.println(0);
            return;
        }


        //값 초기화 dp[i][j] 가 의미하는 바는 j개의 색 중에서 k개를 뽑는 경우
        //이 때 dp 값은 직선이다
        for(int i = 1; i<1001; i++){
            dp[i][1] = i;
            dp[i][0] = 1;
        }

        for(int i = 3; i<=n; i++){
            for(int j = 2 ;j<=(i+1) /2 ; j++){
                dp[i][j] = (dp[i-2][j-1] % mod + dp[i-1][j] % mod) % mod;
            }
        }

        //정답은 원형으로 생각해야한다 . 따라서 N을 선택했을때(n-3,k-1)
        //N을 선택안했을 때 2가지로 나눠야한다.
        System.out.println((dp[n-3][k-1] % mod + dp[n-1][k] % mod) % mod);

    }
}
