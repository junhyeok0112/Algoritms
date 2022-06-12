package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P16967_배열복원하기 {

    static int h,w,x,y;
    static int[][] a;
    static int[][] b;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        a = new int[h][w];
        b = new int[h+x][w+y];
        for(int i = 0 ; i<h+x ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<w+y ;j++){
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //기본 배열 X,Y 만큼 받기
        for(int i = 0 ; i<h ; i++){
            for(int j = 0 ; j<w; j++){
                a[i][j] = b[i][j];
            }
        }

        for(int i = x; i <h ; i++){
            for(int j = y ; j<w ; j++){
                a[i][j] -= a[i-x][j-y];
            }
        }

        for(int i = 0; i<h ; i++){
            for(int j = 0 ; j<w ; j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
}
