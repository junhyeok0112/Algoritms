package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B09_쿼드트리_최준혁 {

    static int n;
    static int[][] map;
    static String ans = "";
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i = 0 ; i< n; i++){
            String str = br.readLine();
            for(int j = 0 ;j < n; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }
        rec(0,0,n);
        System.out.println(sb.toString());
    }


    static void rec(int x , int y, int size){   //x,y 시작점에서 size 만큼
        if(isOneCheck(x,y,size)) {        //기저 조건
            sb.append(map[x][y]);
            return;
        }
        //전부 출력한 다음 , 4개 연속이면 변환시켜야함
        // n/2를 넣어줌
        sb.append("(");
        int newSize = size/ 2;
        rec(x,y,newSize);
        rec(x,y+newSize ,newSize);
        rec(x+newSize , y ,newSize);
        rec(x+newSize ,y+newSize , newSize);
        sb.append(")");
    }

    static boolean isOneCheck(int x, int y, int size){
        int start = map[x][y];  //시작 수
        int xlen = x+size;
        int ylen = y+size;
        for(int i = x; i<xlen ; i++){
            for(int j = y; j<ylen; j++){
                if(map[i][j] != start) return false;
            }
        }
        return true;
    }


}
