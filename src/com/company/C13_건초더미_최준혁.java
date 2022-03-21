package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C13_건초더미_최준혁 {

    static int t,n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        int tc = 0;
        while (tc++<t){
            sb.append("#").append(tc).append(" ");
            n = Integer.parseInt(br.readLine());
            int avg = 0;
            int[] arr = new int[n];
            for(int i = 0 ; i<n ;i++){
                arr[i] = Integer.parseInt(br.readLine());
                avg += arr[i];
            }
            avg /= n;   //평균 값 구함
            int difSum = 0 ;    //평균과 건초더미의 차이
            //횟수를 세는게 핵심 -> 즉 한번 옮기면 2개의 건초더미가 평균에 가까워질것ㄱ이다.
            //단 왠지 모르지만 1회당 건초더미 1개밖에 못옮김
            //따라서 전부 옮겨야하는 값 /2 하면 된다 -> 하나 옮기면 하나는 증가 하나는 감소
            for(int i = 0 ; i<n ;i++){
                difSum += Math.abs(arr[i] - avg);
            }
            sb.append(difSum /2 ).append("\n");
        }
        System.out.println(sb.toString());
    }
}
