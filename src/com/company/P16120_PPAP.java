package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


//문자열에서 PPAP를 모두 P로 치환할 수 있으면 PPAP 문자열이다.
public class P16120_PPAP {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        //끝났을 때 A가 남아있으면 NP
        //중간에 A 뒤에 A가 오거나 A앞에 P가 2개 없어도 NP
        Stack<Character> sta = new Stack<>();
        for(int i=  0; i<str.length();i++){
            if(!sta.isEmpty() && str.charAt(i) == 'P'){

            }
        }
    }
}
