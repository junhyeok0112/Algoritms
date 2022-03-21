package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11721_열개씩끊어서출력하기 {
    //indexOf : 특정문자열 위치 찾기 (앞에서부터)
    //lastIndexOf : 뒤에서 부터 특정 문자열 찾기기
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        int size = str.length();
        int mok = size / 10;
        int cut_lenght = size % 10;
        for(int i= 0 ; i<mok;i++){
            sb.append(str.substring((10*i),(10*i + 10))).append("\n");
        }
        //10단위로 안떨어지면 마지막 출력
        if(cut_lenght != 0) {
            sb.append(str.substring(10 * mok));
        }
        System.out.println(sb);
    }
}
