package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P9012_괄호 {

    static int t;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t =Integer.parseInt(br.readLine());
        for(int i = 0; i<t; i++){
            String st = br.readLine();
            pro(st);
        }
    }

    public static void pro(String str){
        Stack<Character> sta = new Stack<>();
        for(int i = 0; i<str.length(); i++){
            if(str.charAt(i) == '('){
                sta.push(str.charAt(i));
            } else{
                if(!sta.isEmpty() && sta.peek() == '('){
                    sta.pop();
                } else{
                    System.out.println("NO");
                    return;
                }
            }
        }
        if(sta.isEmpty()){
            System.out.println("YES");
        } else{
            System.out.println("NO");
        }
    }
}
