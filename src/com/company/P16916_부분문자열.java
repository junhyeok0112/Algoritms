package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P16916_부분문자열 {

    static String p , s;
    static ArrayList<Integer> table = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        p = br.readLine();
        s = br.readLine();
        int psize = p.length();
        int ssize = s.length();
        for(int i = 0 ;i<ssize; i++){
            table.add(0);           //값 초기화
        }
        makeTable();
        KMP();
    }

    public static void makeTable(){
        int ssize = s.length();
        int j = 0;
        for(int i =1 ; i<ssize; i++){
            while(j > 0 && s.charAt(j) != s.charAt(i)){
                j= table.get(j-1);
            }
            if(s.charAt(i) == s.charAt(j)){
                table.set(i, ++j);
            }
        }
    }

    public static void KMP(){
        int psize = p.length();
        int ssize = s.length();
        int j = 0;
        for(int i = 0; i<psize; i++){
            while (j>0 && p.charAt(i) != s.charAt(j)){
                j = table.get(j-1);
            }
            if(p.charAt(i) == s.charAt(j)){
                if(j == ssize -1){
                    j=table.get(j);
                    System.out.println(1);
                    return;
                } else{
                    j++;
                }
            }
        }
        System.out.println(0);
    }



}
