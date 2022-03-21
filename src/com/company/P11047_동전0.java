package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11047_동전0 {

    static int n,k;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];            //[0][i] 는 금액 , [1][i]는 갯수
        for(int i = 0 ; i<n ;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;            //필요한 동전 갯수
        int total = 0 ;
        int now = n -1 ;        //지금 뽑을 동전 인덱스
        while (total != k){     //total = K 일때가지 반복
            total += arr[now];  //현재 뽑을수 있는거 뽑음
            cnt++;
            if(total > k){
                total -= arr[now];  //목표 금액보다 크면 뺌
                cnt--;              //동전 갯수 감소
                now--;              //더 작은 동전 추가
            }
        }

        System.out.println(cnt);

    }
}
