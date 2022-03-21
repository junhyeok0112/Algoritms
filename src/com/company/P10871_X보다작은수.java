package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10871_X보다작은수 {
    static int N,X;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i<N ;i++){
            if(arr[i]<X){
                System.out.print(arr[i]+" ");
            }
        }
    }
}
