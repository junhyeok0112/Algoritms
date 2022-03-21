package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class P14002_가장긴증가하는부분수열4 {

    static int n;
    static int[] arr;
    static ArrayList<Integer> ansArr = new ArrayList<>() ;
    static int[] dp ;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n =Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=n ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //초기화
        for(int i = 1; i<=n ; i++){
            dp[i] = 1;
        }

        for(int i =2 ;i<= n;i++){
            for(int j = 1; j<i ;j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[j] + 1 , dp[i]);
                }
            }
        }

        int max = -1;
        int maxIndex = -1;
        for(int i = 1 ;i<=n ;i++){
            if(max < dp[i]){
                max = Math.max(dp[i] , max);
                maxIndex = i;
            }
        }

        //스택과 부분수열의 길이를 이용해서 전체 수열에서 역추적한다.
        Stack<Integer> sta = new Stack<>();
        int value = max;
        for(int i= n; i>=1 ;i--){
            //만약 부분수열의 길이에 해당하는 dp 만나면 그 값을 stack에 저장
            if(value == dp[i]){
                sta.push(arr[i]);
                //저장 후 그보다 수열에서 다음으로 작은 값을 구하기 위해 길이 감소
                //즉 , 4짜리 이후 3짜리 길이 중 3에 해당하는 갓을 보기 위해 길이 감소시킴
                value--;
            }
        }



        System.out.println(max);
        int staSize = sta.size();
        for(int i = 0; i< staSize ;i++){
            System.out.print(sta.pop()+" ");

        }
    }
}
