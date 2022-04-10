package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9095_123더하기_re {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int[] dp = new int[12];
        dp[1] = 1;  //1
        dp[2] = 2;  //1+1 , 2
        dp[3] = 4;  //1+1+1, 1+2, 2+1
        for(int i =4 ;i<=11; i++){
            //1더하는 경우 , 2 더하는 경우, 3 더하는경우 -> 1+2 같은 경우는 2더하는 경우로 친다.
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }
        while (t-->0){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
