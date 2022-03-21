package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B07_줄세우기_최준혁 {

    static int n;
    static int[] arr;
    static LinkedList<Integer> ll = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ;i<= n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ll.add(1);      //첫번쨰 학생 셋팅
        for(int i =2 ; i<=n;i++){
            ll.add(i-1-arr[i] , i); //이 자리에다가 i를 넣어야함
        }
        for(int cur : ll){
            System.out.print(cur+" ");
        }
    }
}
