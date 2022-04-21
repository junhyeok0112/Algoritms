package com.company;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2630_색종이만들기 {

    static int n;
    static int[][] map;
    static int ansW = 0;        //0이면 하얀색
    static int ansB = 0;        //1이면 파란색
    public static void main(String[] arsg) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i = 0 ; i<n ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<n ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rec(0,0,n);
        System.out.println(ansW);
        System.out.println(ansB);

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
            if(map[x][y] == 1) ansB++;
            else ansW++;
            return;
        }

        rec(x,y,n/2);
        rec(x+n/2,y,n/2);
        rec(x,y+n/2,n/2);
        rec(x+n/2, y+n/2 ,n/2);
    }
}
