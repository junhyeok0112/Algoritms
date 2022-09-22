package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11659_구간합구하기4 {

    static int n,m;
    static int[] arr;
    static int[] prefix_sum;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        prefix_sum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<= n ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i<=n ;i++){
            prefix_sum[i] = arr[i] + prefix_sum[i-1];
        }

        for(int i=  0;  i<m ;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(prefix_sum[e] - prefix_sum[s-1]).append("\n");
        }
        System.out.println(sb);
    }
}
