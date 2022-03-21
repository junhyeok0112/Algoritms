package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1010_다리놓기 {

    static int t,n,m;
    static int[][] dp ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        dp = new int[30][30];       //조합을 구하는 문제
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;
        for(int i =2 ; i<30 ; i++){
            for(int j=0; j<30 ;j++){
                if(j == 0 ) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }


        while(t-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            System.out.println(dp[m][n]);
        }

    }
}
