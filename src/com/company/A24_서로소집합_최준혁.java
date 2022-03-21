package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A24_서로소집합_최준혁 {

    static int n,m,t;
    static int[] p;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int tc = 0;
        while (tc++<t){
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            p = new int[n+1];
            for(int i = 1 ; i<=n ;i++) p[i] = i;
            for(int i = 0 ; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int command = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(command == 0){
                    merge(a,b);
                } else{
                    if(find(a) == find(b)){
                        sb.append(1);
                    } else{
                        sb.append(0);
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int find(int x){
        if(x == p[x]) return x;
        return p[x] = find(p[x]);
    }

    static void merge(int x , int y){
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot ) return;
        p[yRoot] = xRoot;
    }
}
