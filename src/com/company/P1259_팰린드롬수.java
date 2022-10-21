package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1259_팰린드롬수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String cur = br.readLine();
        while (!cur.equals("0")){
            int len = cur.length();
            int start = 0 ;
            int end = len-1;
            boolean flag = true;   //다 돌고 나왓을 경우
            while (start<=end){
                if(start == end){
                    sb.append("yes").append("\n");
                    flag = false;
                    break;
                }
                if(cur.charAt(start) == cur.charAt(end)){
                    start++;
                    end--;
                } else{
                    sb.append("no").append("\n");
                    flag = false;
                    break;
                }
            }
            if(flag) sb.append("yes").append("\n");
            cur = br.readLine();
        }
        System.out.println(sb.toString());
    }
}
