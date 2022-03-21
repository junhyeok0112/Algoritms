package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P12904_A와B {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        while(true){
            int tsize = t.length();
            if(t.charAt(tsize-1) == 'A'){       //마지막이 A일때
                t = t.substring(0,tsize-1);     //A버리기
            } else {                             //마지막이 B일때
                t = t.substring(0, tsize - 1);     //B버리고
                String temp = "";               //뒤집어야함
                for (int i = t.length() - 1; i >= 0; i--) {
                    temp += t.charAt(i);
                }
                t = temp;
            }
            if(s.equals(t)){
                System.out.println(1);
                return;
            }else{                      //길이가 같은데 다르면 더 이상 같을 수 없음
                if(s.length() == t.length()){
                    System.out.println(0);
                    return;
                }
            }
        }
    }
}
