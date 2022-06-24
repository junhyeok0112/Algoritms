package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14929_귀찮아 {

    static int n;
    static int[] arr;
    static int[] prefix_sum;
    static long ans = 0;
    //즉 i번째 idx 값 * 0~i-1 번째까지의 합을 계산하면 i번째 idx를 고려한 값을 구할 수 있습니다.
    //예를들어 1 -2 3 4 가 있으면
    //4를 포함한 xa*xb는 (1-2+3) * 4로 표현할 수 있습니다.
    //즉 0~i-1합 * i 값 들의 모든 합을 구하면됩니다.
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        prefix_sum = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i =1 ; i<n ;i++){
            prefix_sum[i] = arr[i-1] + prefix_sum[i-1];
            ans += prefix_sum[i] * arr[i];
        }
        System.out.println(ans);
    }
}
