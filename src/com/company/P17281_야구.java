package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17281_야구 {

    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][10];
        for(int i = 1; i<=n ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
}
