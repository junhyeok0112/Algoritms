package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2491_수열 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[][] dp = new int[N][2];
        StringTokenizer st= new StringTokenizer(br.readLine());

        for(int i = 0 ; i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = 1;
        dp[0][1] = 1;
        for(int i =1 ;i<N; i++){
            if(arr[i]>=arr[i-1]){
                dp[i][0] = dp[i-1][0] + 1;
            }else{
                dp[i][0] = 1;
            }

            if(arr[i] <= arr[i-1]){
                dp[i][1] = dp[i-1][1] + 1;
            }else{
                dp[i][1] = 1;
            }
        }

        int maxUp = -1;
        int maxDown = -1;
        for(int i = 0 ; i<N ;i++){
            if(maxUp < dp[i][0]) maxUp = dp[i][0];
            if(maxDown < dp[i][1]) maxDown = dp[i][1];
        }
        System.out.println(Math.max(maxUp, maxDown));

    }

}

