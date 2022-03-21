package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A05_백설공주_최준혁 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        int[] arr = new int[9];
        int total = 0;
        for(int i = 0 ; i< 9; i++){
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }
        Arrays.sort(arr);
        int del1 = -1;
        int del2 = -1;
        for(int i = 0 ; i<8 ;i++){
            for(int j = i+1; j<9; j++){
                if(total - arr[i] -arr[j] == 100){
                    del1 = i;
                    del2 = j;
                }
            }
        }

        for(int i = 0 ; i<9 ;i++){
            if(i == del1 || i==del2) continue;
            System.out.println(arr[i]);
        }

    }
}
