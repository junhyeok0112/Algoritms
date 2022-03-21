package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2749_피보나치3 {

    //피사노 주기라는 것이 존재한다 . -> 피보나치 수를 어떤 K로 나눌때 나머지는 항상 주기를 가진다
    //주기는 나누는 수 M = 10^k 일때 주기는 15*10^(k-1)이다
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        int pissano = 1500000;  //주기
        N %= pissano;
        int[] dp = new int[pissano+1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 2; i<=pissano; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000;
        }
        //N번째 수라는 것은 0번 부터 N개의 수를 나타내라는 것 -> 즉 N-1 번째 수를출력해야한다
        System.out.println(dp[(int) N -1]);

    }
}
