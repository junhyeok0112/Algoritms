package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B12_창용마을무리의개수_최준혁 {

    static int n ,t ,m;
    static int[] p ;        //집합
    static HashSet<Integer> hs = new HashSet<>();   //무리들 저장할 HashSet

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int tc = 0;
        while (tc++ <t){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            p = new int[n+1];
            for(int i = 1; i<= n ;i++){
                p[i] = i;
            }

            for(int i = 0 ; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                merge(x,y);
            }

            for(int i = 1 ;i<=n ;i++){
                hs.add(find(i));
            }

            sb.append("#").append(tc).append(" ").append(hs.size()).append("\n");
            hs.clear();
        }
        System.out.println(sb.toString());
    }

    static int find(int x){
        if(p[x] == x )return x;
        return p[x] = find(p[x]);
    }

    static boolean merge(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot) return false;
        if(y>x){
            p[yRoot] = xRoot;
        } else{
            p[xRoot] = yRoot;
        }
        return true;
    }
}
