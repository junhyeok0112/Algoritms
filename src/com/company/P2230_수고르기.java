package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2230_수고르기 {

    static int N,M;
    static int[] arr ;
    static int ans = Integer.MAX_VALUE;     //M 이상이면서 가장 작은 차이 ->MIN값으로 계속 계산


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        for(int i =1 ; i<=N ;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr,1,N+1);       //정렬 , 마지막은 index는 포함하지않음
        //두 수를 골랐을 때 이므로 앞에서부터 투포인터로 계싼
        //두 수의 차이가 M보다 커지는 순간 보다 R을 더 증가시킬 필요가 없다 -> 왜냐면 어짜피 그 순간 차이보다 크므로 의미없음
        //따라서 M보다 커지면 L을 증가시켜서 차이 줄여주는식으로 봐준다
        //M이면 return
        int R = 1;
        for(int L = 1 ;L<=N;L++){
            while (R <N && arr[R] - arr[L] <M) R++; //dif 가 M보다 커지는 지점까지 R증가 시킴
            if (arr[R] - arr[L] == M) {
                System.out.println(arr[R] - arr[L]) ;
                return;
            }
            if(arr[R] - arr[L] >M){
                ans = Math.min(ans , arr[R] - arr[L]);
            }

        }
        System.out.println(ans);


    }
}
