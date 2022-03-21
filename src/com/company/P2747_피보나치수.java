package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2747_피보나치수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[46];
        arr[0] = 0 ;
        arr[1] = 1;
        for(int i= 2; i<=45;i++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        int N = Integer.parseInt(br.readLine());
        System.out.println(arr[N]);
    }
}
