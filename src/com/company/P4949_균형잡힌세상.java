package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P4949_균형잡힌세상 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String s = br.readLine();
            if(s.equals(".")){
                break;
            }
            pro(s);
        }
    }

    static void pro(String str){
        Stack<Character> st = new Stack<>();
        for(int i = 0 ; i<str.length();i++){
            if(str.charAt(i) == '[' || str.charAt(i) =='(' ){   //여는 괄호면
                st.push(str.charAt(i));
                continue;
            }
            if(str.charAt(i) == ']' || str.charAt(i) ==')' ){   //닫는 괄호면
                if(st.isEmpty()){                               //닫는 괄호인데 stack 이 비었으면 no
                    System.out.println("no");
                    return;
                }
                if(str.charAt(i) == ']'){
                    if(st.peek() == '['){                 //같으면 pop
                        st.pop();
                    } else{
                        System.out.println("no");
                        return;
                    }
                } else{
                    if(st.peek() == '('){
                        st.pop();
                    } else{
                        System.out.println("no");
                        return;
                    }
                }
            }
        }
        if(st.isEmpty()){                   //stack 이 비어 있으면 균형을 이루는 거
            System.out.println("yes");
        } else{
            System.out.println("no");
        }
    }
}
