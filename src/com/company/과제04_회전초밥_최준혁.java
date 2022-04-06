package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 과제04_회전초밥_최준혁 {

    static int n,d,k,c;
    static ArrayList<Integer> arr = new ArrayList<>();
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dp = new int[n];
        for(int i =0 ; i<n;i++){
            arr.add(Integer.parseInt(br.readLine()));
        }

        int l = 0 ;
        int r = k -1 ;          // 오른쪽 투 포인터 이용 ?
        //dp[l]을 채우자
        HashSet<Integer> hs = new HashSet<>();
        int cnt = 0;
        for(int i = l ;i<r ;i++){
            hs.add(arr.get(i));     //
        }
        dp[0] = hs.size();
    }
}
