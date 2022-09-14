package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class P14425_문자열집합 {

    static int n,m;
    static HashSet<String> hs = new HashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i<n; i++){
            hs.add(br.readLine());
        }
        int ans = 0 ;
        for(int i = 0 ;i<m; i++){
            if(hs.contains(br.readLine())) ans++;
        }
        System.out.println(ans);
    }
}
