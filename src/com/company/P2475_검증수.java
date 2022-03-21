package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2475_검증수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = 0;
        int res = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<5 ;i++){
            int num = Integer.parseInt(st.nextToken());
            total += Math.pow(num,2);
        }
        System.out.println(total % 10);

    }
}
