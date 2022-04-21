package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 실습07_최장증가부분수열_최준혁 {

    static int tc,n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        int t = 0 ;
        while (t++<tc){
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[n];
            dp = new int[n];
            dp[0] = 1;      //처음은 무조건 1
            int max = -1;
            for(int i = 0 ; i<n ;i++){
                arr[i] = Integer.parseInt(st.nextToken());      //값 입력하기
            }

            //dp 배열 채우기
            for(int i =0 ; i<n ;i++){
                dp[i] = 1;          //1로 전부 초기화
                for(int j = 0 ; j<i; j++){
                    if(arr[j] <=arr[i] && dp[i] < 1 + dp[j]){       //앞에 것들 전부 보면서 i보다 작은 값 봤을 때 j의 수열 +1로 갱신 -> max값으로 갱신 됨
                        dp[i] = 1 +dp[j];
                    }
                }
                max = Math.max(max,dp[i]);
            }

            sb.append(max).append("\n");
        }
        System.out.println(sb.toString());
    }
}
