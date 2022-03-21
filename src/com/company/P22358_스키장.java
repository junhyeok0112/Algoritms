package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class ski {
    int b;
    int t;
    ski( int b, int t){
        this.b = b;
        this.t = t;
    }
}


public class P22358_스키장 {

    static int N,M,K,S,T;
    static int[] dist ;
    static int[] visit ;
    static int[] dp;
    static ArrayList<ArrayList<ski>> adj;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        adj = new ArrayList<>();
        for(int i = 0; i<=N ;i++){
            adj.add(new ArrayList<>());
        }
        // S -> T로
        for(int i =0 ; i<M ;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            adj.get(num1).add(new ski(num2, time));

        }
        if(T<S){//도착 지점이 더 높아서 리프팅 타야만할 때
            System.out.println(0);
            return;
        }



    }
}
