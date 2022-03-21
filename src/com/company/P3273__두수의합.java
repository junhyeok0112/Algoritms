package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3273__두수의합 {

    static int N,X;
    static int[] arr;
    static long res = 0;    //순서쌍 개수 출력
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        X = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        int R = N;
        int L = 1;
        while(L<=N && R >= 1 && L<R){       //범위 안일때
            if(arr[L] + arr[R] == X){
                res++;
                L++;
                R--;
            } else if(arr[L] + arr[R] >X){
                R--;
            } else if(arr[L] + arr[R] <X){
                L++;
            }
        }
        System.out.println(res);

    }
}
