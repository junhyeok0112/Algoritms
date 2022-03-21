package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P8393_합 {

    static int hap(int n){
        int result = 0;
        for(int i = 1; i<=n;i++){
            result+=i;
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(hap(N));
    }
}
