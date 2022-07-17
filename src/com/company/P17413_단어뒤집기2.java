package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P17413_단어뒤집기2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        Stack<Character> sta = new Stack<>();
        int len = str.length();
        boolean chk = false;        //태그 인지 아닌지 체크
        for(int i = 0 ; i<len ;i++){
            if(str.charAt(i) == '<' || str.charAt(i) == '>'){
                if(str.charAt(i) == '<'){
                    while (!sta.isEmpty()){
                        sb.append(sta.pop());
                    }
                    chk = true;
                    sb.append(str.charAt(i));    //여는 태그
                } else{
                    chk = false;
                    sb.append(str.charAt(i));   //닫는 태그
                }
            } else if(str.charAt(i)==' ' && !chk){  //공백이고 태그 안에 내용이 아닐때
                //공백 만나면 스택에 있는거 전부 뱉음
                while (!sta.isEmpty()){
                    sb.append(sta.pop());
                }
                sb.append(" "); //공백
            } else{
                if(chk){
                    sb.append(str.charAt(i));
                } else{
                    sta.push(str.charAt(i));
                }
            }
        }
        //마지막
        while (!sta.isEmpty()){
            sb.append(sta.pop());
        }
        System.out.println(sb.toString());
    }
}
