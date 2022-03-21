package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class P11718_그대로출력하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;
        while((str = br.readLine()) != null){
            //입력이 안들어 올 떄까지 받음
            sb.append(str).append('\n');
        }
        System.out.println(sb.toString());
    }
}
