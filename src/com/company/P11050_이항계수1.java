package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11050_이항계수1 {

    static int N,K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[11][11];
        for(int i =0;i<=10;i++){
            arr[i][0] = arr[i][i] = 1;
        }

        //파스칼의 삼각형 이용
        //nCr = n-1Cr-1 + n-1Cr 이용
        for(int i = 2; i<= 10;i++){
            for(int j = 1; j<i ; j++){
                arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
            }
        }
        System.out.println(arr[N][K]);
    }
}
