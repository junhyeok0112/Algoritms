package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P10942_팰린드롬 {

    static int N,M;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1][N+1];     //i~j까지 팰린드롬인지 알려주는것
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //dp 사용
        for(int i = 1; i<=N; i++){
            dp[i][i] = 1;       //자기 자신만 있을 경우 팰린드롬
        }

        for(int i =1 ; i<N ;i++){
            if(arr[i] == arr[i+1]){     //연속된 2개가 같으면 팰린드롬이므로 1로 처리
                dp[i][i+1] = 1;
            }
        }

        //보려는 dp의 처음과 끝이 같고 , 그 사이가 팰린트롬이면 보려는 dp도 팰린트롬이다
        for(int len = 3 ; len<=N ; len++){      //길이 3짜리부터 보자 -> 2짜리까지는 위에서 다채움
            for(int i = 1; i<=N ;i++){
                int j = i + len - 1;
                if(j >N ) continue; //범위 넘어가면 넘어가
                if(arr[i] == arr[j] && dp[i+1][j-1] == 1){  //처음과 끝이 같은 값이고 i+1 ~ j-1 즉 그 사이가 펠린트롬이면
                    dp[i][j] = 1;
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            //sb.append(pro(s,e)).append("\n");
            sb.append(dp[s][e]).append("\n");
        }
        System.out.println(sb);
    }

    //판단하는 함수
    static int pro(int s , int e){
        while(s <= e){
            if(arr[s] != arr[e]){
                return 0;
            }
            s++;
            e--;
        }
        return 1;
    }
}
