package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2217_로프 {

    static int n ;
    static int[] arr;
    static int ans = 0;
    static int total =  0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for(int i = 0 ; i<n ;i++){
            arr[i] = Integer.parseInt(br.readLine());
            ans = Math.max(ans,arr[i]);     //하나만 사용했을 때 들 수 있는 최대무게 셋팅
            total += arr[i];
        }
        Arrays.sort(arr);
        //최 대 중 량 구 하 기..
        //정렬 한 뒤 젤 작은 중량 * 본인 이상의 중량들 갯수 -> 하면 가장 작은 중량을 포함한 상태에서 최대의 중량이다
        //이 식을 모든 중량들을 기준으로 보면된다.
        for(int i = 0 ; i<n ;i++){
            int temp = arr[i] * (n-i);      // 가장 작은애를 N-i개만큼 써보자
            ans = Math.max(ans , temp);           //
        }

        System.out.println(ans);


    }
}
