package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P20040_사이클게임 {

    static int n,m,x,y;
    static int[] p ;            //0 ~n-1까지

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n];
        Arrays.fill(p,-1);
        int ans = 0;
        for(int i = 1 ;i<=m ;i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if(merge(x,y)){
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    static int find(int x){
        if(p[x] == -1) return x;
        return p[x] = find(p[x]);
    }

    static boolean merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y) return true;      // 이미 연결되어있으면 ->싸이클이면
        p[y] = x;
        return false;
    }

}
