package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10870_피보나치수5 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[21];
        arr[0] = 0;
        arr[1] = 1;
        for(int i =2 ; i<=20 ; i++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        System.out.println(arr[N]);

    }
}
