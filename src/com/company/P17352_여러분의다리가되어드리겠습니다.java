package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class P17352_여러분의다리가되어드리겠습니다 {

    static int n;
    static int[]p;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[n+1];
        Arrays.fill(p ,-1);
        StringTokenizer st ;
        for(int i = 0 ; i<n-2; i++){
            st = new StringTokenizer(br.readLine());
            int start =Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            merge(start ,end);
        }
            //다리가 하나만 끊어져 있으므로 원래 Spanning Tree였다가 2개의 그룹으로 나뉘어짐
        HashSet<Integer> hs = new HashSet<>();  //연결되어 있는 것들을 저장할 Hash
        for(int i = 1; i<=n ;i++){
            if(p[i] == -1){
                System.out.print(i+" ");        //-1이면 대표 섬 -> 따라서 대표섬 2개 연결하면됨.
            }
        }
    }

    static int find(int x){
        if(p[x] == -1 ) return x;
        return p[x] = find(p[x]);
    }

    static void merge(int x, int y){
        x = find(x);
        y = find(y);
        if( x == y) return;
        if(x > y){      //더 작은걸로
            int temp = x;
            x = y;
            y = temp;
        }
        p[y] = x;
    }
}
