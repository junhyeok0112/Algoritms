package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P10773_제로 {

    static Stack<Integer> s = new Stack<>();
    static int total =0 ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i<N ;i++){
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                s.pop();
            }else{
                s.push(num);
            }
        }
        while(!s.isEmpty()){
            int num = s.pop();
            total+= num;
        }
        System.out.println(total);
    }
}
