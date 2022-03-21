package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2003_수들의합2 {
    public static void main(String[] args) throws IOException {
        //연속된 부분수열의 합 -> 투 포인터 고려 ->이런 식 접근
        //합이 L~R 까지 합이 M 일때 L-1 ~R 까지는 합이 M이 될수가 없다 이용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0 ;   //정답
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int R = 0 , sum = 0;
        for(int L = 1 ;L <=N; L++){
            sum -= arr[L -1];  //전에꺼 빼기
            while(R<N && sum <M){  //M보다 크거나 같을때까지
                R++;
                sum+=arr[R];
            }
            if(sum == M ){  //M과 같으면 증가
                cnt++;
                //continue;
            }
        }
        System.out.println(cnt);
    }
}
