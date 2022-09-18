package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P3986_좋은단어 {

    static int n;
    static int ans = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        while(n-->0){
            String str = br.readLine();
            int len = str.length();
            Stack<Character> sta = new Stack<>();
            boolean flag = false;
            for(int i = 0 ; i<len;i++){
                if(sta.isEmpty()){
                    sta.push(str.charAt(i));
                    continue;
                } else{
                    if(sta.peek() == str.charAt(i)) sta.pop();
                    else {
                        sta.push(str.charAt(i));
                    }
                }
            }
            if(sta.isEmpty()) ans++;
        }
        System.out.println(ans);
    }
}
