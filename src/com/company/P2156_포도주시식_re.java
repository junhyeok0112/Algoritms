package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2156_포도주시식_re {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[] dp = new int[n+1];
        for(int i = 1 ; i<=n ;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = arr[1];     //첫번째 마셨을 때 최대값
        if( n>1){
            dp[2] = arr[1]+arr[2];  //두번째 마셨을때 최대값
        }
        for(int i = 3; i<=n ;i++){
            //2개전꺼 마시고 지금꺼 마셨을때 vs 바로 이전꺼 마셨는데 2개전꺼 안마시고 지금꺼 마셨을때 최대값
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-3]+arr[i-1], dp[i-2] ) + arr[i]);       //내꺼 안마셨을경우도 계산
        }

        int ans = -1;
        for(int i =1 ;i<=n ;i++){
            ans = Math.max(ans,dp[i]);
        }
        System.out.println(ans);

    }
}
