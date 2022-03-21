package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class P14888_연산자끼어넣기 {

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int n;
    static int[] arr;
    static int[] command = new int[4];
    static ArrayList<Integer> cal = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for(int i = 0 ;i<n ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i= 0; i<4; i++){
            command[i] = Integer.parseInt(st.nextToken()); // + -  * % 순으로 들어가 있음
        }
        bac(0);

        System.out.println(max);
        System.out.println(min);
    }
    //큐 이용하기
    static void bac(int cnt){       //cnt 가 연산자를 몇개 사용했는지
        if(cnt == n-1){               //n-1개의 연산자를 전부 사용했으면
            int temp = arr[0];      //계산 초기값
            for(int i = 1 ; i<n;i++){
                if(cal.get(i-1) == 0){
                    temp+=arr[i];
                } else if(cal.get(i-1) == 1){
                    temp-=arr[i];
                } else if(cal.get(i-1) == 2){
                    temp*=arr[i];
                } else if(cal.get(i-1) == 3){
                    temp/=arr[i];
                }
            }
            max = Math.max(max, temp);
            min = Math.min(min ,temp);
            return;
        }
        for(int i = 0 ; i<4 ;i++){
            if(command[i] == 0) continue;       //그자리에 연산자 없으면 건너뜀
            cal.add(i);                         //ArrayList로 안하고 그냥 n-1개의 배열 선언해서 하면 됨
            command[i]--;
            bac(cnt+1);
            command[i]++;
            cal.remove(cnt);
        }
    }
}
