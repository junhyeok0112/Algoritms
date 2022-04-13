package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9251_LCS {

    static String s1,s2;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();
        int lenS1 = s1.length();
        int lenS2= s2.length();
        int[][] dp = new int[lenS1+1][lenS2+1];     //x,y 까지 봤을떄의 LCS 길이  ,정답은 dp[lenS1][lenS2] 출력
        for(int i = 1; i<=lenS1; i++){
            for(int j =1 ; j<=lenS2 ;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;        //X번째 원소와 Y번째 원소가 같다면 (x-1,y-1)의 LCS의 길이 +1 이 된다
                } else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);       //만약 다르면 이전열 or 이전 행 중 최대값
                }
            }
        }
        System.out.println(dp[lenS1][lenS2]);
    }
}
