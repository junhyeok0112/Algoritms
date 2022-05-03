package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class P16434{
    int num;        //1이면 몹 , 2이면 포션
    int a;
    int h;
    P16434(int num ,int a, int h){
        this.num = num;
        this.a = a;
        this.h = h;
    }
}
public class P16434_드래곤앤던전 {

    static int n,atk;
    static P16434[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());
        arr = new P16434[n+1];          //n번째 방까지 존재
        for(int i = 1; i<=n ;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            arr[i] = new P16434(num ,a ,h);
        }

        long left = 0 ;
        long right = n * 1000000;
        long ans = 0;
    }
}
