package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B04_한빈이와SpotMart_최준혁 {

    static int tc ,n,m;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st ;
        int t = 0;
        tc = Integer.parseInt(br.readLine());
        while(t++<tc){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i< n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            //완탐으로 찾기 -> N이 1000이라 1000^2 해도 시간안에 가능
            int max = -1;
            for(int i = 0 ; i<n-1;i++){
                for(int j = i+1; j<n;j++){
                    int total = arr[i] + arr[j];
                    if(total<=m){
                        max = Math.max(max,total);
                    }
                }
            }
            sb.append("#"+t+" ");
            sb.append(max).append("\n");
        }
        System.out.println(sb.toString());
    }
}
