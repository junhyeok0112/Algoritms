package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2798_블랙잭 {

    static int n,m;
    static int[] arr;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0 ; i<n ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i<n-2;i++){
            for(int j = i+1; j<n-1;j++){
                for(int t = j+1; t<n; t++){
                    int total = arr[i] + arr[j] + arr[t];
                    if(total > m) continue;
                    if(ans < total){
                        ans = total;
                    }
                }
            }
        }

        sb.append(ans);
        System.out.println(sb.toString());
    }


}
