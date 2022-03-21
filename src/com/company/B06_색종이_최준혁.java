package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class B06{
    int x;
    int y;
    B06(int x ,int y){
        this.x = x;
        this.y= y;
    }
}


public class B06_색종이_최준혁 {

    static int n;
    static ArrayList<B06> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int ans = 0;
        int[][] map = new int[101][101];
        for(int i = 0 ; i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for(int t = x; t<x+10;t++){
                for(int j = y; j<y+10;j++){
                    if(map[t][j] ==0 ){
                        map[t][j] =1 ;
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);




    }
}
