package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2512_예산 {

    static int n,m; //n 지방 수 , m 총 예산
    static int[] arr ;      //요청한 예산
    static int ans = 0 ;        //배정된 예산들 중 최대값

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tempMax = -1;       //만약 M금액 안으로 배정 가능할때 최대값
        int sum = 0;
        for(int i = 0 ; i<n ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum+=arr[i];
            tempMax = Math.max(tempMax, arr[i]);
        }
        m = Integer.parseInt(br.readLine());
        //요청한 예산액들을 전부 처리 가능한 경우
        if(m >= sum){
            System.out.println(tempMax);
            return;
        }

        //요청 금액이 처리 불가능한 경우 -> 상한액이 최대 값 , 적절한 상한 액을 구해야함
        //상한액을 임의의 값으로 정한 뒤 만족하는지 확인 -> 매개변수 탐색법으로
        int left = 1;
        int right = 100000;     //금액의 최대값이 10만
        while(left <= right){
            int mid = (left + right) / 2;
            int tempSum = 0 ;           //상한액을 정했을 때 필요한 예산 -> 10억 이하이므로 int 형
            for(int i = 0 ; i<n ;i++){
                if(arr[i] <= mid) tempSum+=arr[i];
                else tempSum += mid;
            }
            if(tempSum <= m){   //가능한 경우 이므로 값 좀더 증가시켜봄
                left = mid + 1;
                ans = Math.max(ans, mid);      //가능한 정답
            } else{
                right = mid -1;
            }
        }
        System.out.println(ans);
    }
}
