package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1541_잃어버린괄호 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //마이너스에서 마이너스 나오기 전까지 괄호
        String st = br.readLine();
        int ans = 0;
        String temp = "";
        boolean flag = false;       //minus 해야하면 true 아니면 false
        for(int i = 0; i<st.length();i++){
            if(st.charAt(i) == '-' || st.charAt(i) == '+'){
                int num = Integer.parseInt(temp);
                temp ="";
                if(flag){
                    ans -= num;
                } else{
                    ans += num;
                }
                if(st.charAt(i) == '-') flag = true;
            } else{
                temp+=st.charAt(i);
            }
        }

        //마지막 숫자 계산
        int num = Integer.parseInt(temp);
        temp ="";
        if(flag){
            ans -= num;
        } else{
            ans += num;
        }
        System.out.println(ans);
    }
}
