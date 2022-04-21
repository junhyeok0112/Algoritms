package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1992_쿼드트리 {

    static int n;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i = 0;i <n; i++){
            String str = br.readLine();
            for(int j =0;j<n;j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }
        rec(0,0,n);
        System.out.println(sb.toString());
    }

    //하나의 색으로 이루어져있는지 확인하는 함수 필요
    static boolean check(int x, int y, int n){
        int start = map[x][y];
        for(int i = x ; i<x+n ;i++){
            for(int j= y; j<y+n ;j++){
                if(start != map[i][j]) return false;
            }
        }
        return true;
    }

    //분할 정복으로 나누는 함수 필요
    static void rec(int x, int y , int n){      //시작점

        if(check(x,y,n)){
            sb.append(map[x][y]);
            return;
        }
        sb.append("(");
        rec(x,y,n/2);
        rec(x,y+n/2,n/2);
        rec(x+n/2,y,n/2);
        rec(x+n/2, y+n/2 ,n/2);
        sb.append(")");
    }
}
