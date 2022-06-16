package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15732_도토리숨기기 {

    static int n,k,d;
    static int[][] rules;
    static int[] box;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        rules = new int[k][3];
        box = new int[n+1];
        for(int i = 0 ; i<k ;i++){
            st = new StringTokenizer(br.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }
        //N개의 상자들에 하나 당 몇개씩 들어가는지 계산해야함

    }
}
