package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1094_막대기 {

    static int x;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        x = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i = 0 ; i<8; i++){
            if((x & (1<<i)) == (1<<i) ) cnt++;
        }
        System.out.println(cnt);


    }
}
