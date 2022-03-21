package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1120_문자열 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();
        int min = 51;           //최대 50개이므로
        int dif = b.length() - a.length();  //b와 a의 개수 차이만큼 반복
        for(int i = 0 ; i<=dif; i++){
            int cnt = 0;
            for(int j = i ; j<i+a.length(); j++){
                if(a.charAt(j-i) != b.charAt(j)) cnt++; //a는 0~ a길이까지로 값 고정
            }
            min = Math.min(cnt, min);
        }
        System.out.println(min);
    }
}
