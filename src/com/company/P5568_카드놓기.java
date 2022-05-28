package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class P5568_카드놓기 {

    static int n,k;
    static int[] arr;
    static boolean[] selected;
    static HashSet<String> hs = new HashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        selected = new boolean[n+1];
        for(int i = 0 ; i<n ;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        pro("",0);

        System.out.println(hs.size());
    }

    static void pro(String temp, int cnt){
        if(cnt >=k){
            hs.add(temp);
            return;
        }

        for(int i = 0 ; i<n ;i++){
            if(selected[i]) continue;
            selected[i] = true;
            temp += String.valueOf(arr[i]);
            pro(temp,cnt+1);
            temp = temp.substring(0,temp.length() - String.valueOf(arr[i]).length());
            selected[i]= false;
        }
    }
}
