package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1717_집합의표현 {

    static int n,m;
    static int[] p;         //집합을 저장할 배열

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n+1];
        Arrays.fill(p , -1);
        for(int i = 0 ; i< m ; i++){
            st = new StringTokenizer(br.readLine());
            int com = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(com == 0){
                merge(x,y);
            }else{
                if(find(x) == find(y)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static int find(int x ){
        if(p[x] == -1 ) return x;
        return p[x] = find(p[x]);
    }

    static void merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y) return;      //같으면 돌려놓고
        p[y] = x;               //다르면 y의 부모를 x로 셋팅팅
    }
}
