package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1152_단어의개수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String temp = br.readLine();
        if(temp.equals(" ")) {
            System.out.println(0);
            return;
        }
        String[] ans = temp.trim().split(" ");
        System.out.println(ans.length);
    }
}
