package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P23080_스키테일암호 {

    static int K;
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        s = br.readLine();
        for(int i = 0 ; i<s.length(); i+=K){
            System.out.print(s.charAt(i));
        }
    }
}
