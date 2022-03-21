package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11052_카드구매하기 {

    static int N;
    static int[] p;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = new int[N+1];
        dp = new int[N+1];
        for(int i = 1 ; i<=N;i++){
            p[i] = Integer.parseInt(st.nextToken());
        }
        dp[1] = p[1];       //하나 구매하는 가격은 하나뿐
        for(int i =2 ; i<=N;i++){
            for(int j = 0 ; j<i; j++){
                dp[i] = Math.max(dp[i],p[i-j] + dp[j]);
                //i개 들어있는걸 한번에 사는 값과 j개를 살수 있는 최대값 + (i-j)개 살 수 있는 값들을 모두 비교하면
                //i개를 살 수 있는 최대값 구하기 가능
                //예를들면 4개를 살때 최대값 구하려면 p[4]로 한번에 4개 아니면 (1,3), (2,2) ,(3,1) 처럼 1개 살 수 있는 최대값 + 3개한번에 살수 있는 값
                //2개 살 수 있는 최대값 + 2개 한번에 살 때 최대값을 구한다 -> 즉 하나의 개수를 고정 시키고 다른걸 변경 ->dp 형식
            }
        }
        System.out.println(dp[N]);
    }
}
