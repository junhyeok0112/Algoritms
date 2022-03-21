package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9095_123더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[12];
        dp[1] = 1;          //1
        dp[2] = 2;          //1+1 , 2
        dp[3] = 4;          //1+1+1 , 1+2 , 2+1, 3  순서 바뀌면 다른 거
        for(int i =4 ; i<=11;i++){      //i-1 , i-2 i-3 이 되는 경우의 수를 모두 더하면된다.
            //1,2,3 을 고정시키고 3이되는 경우 + 2가되는경우  + 1이 되는경우 라고 생각
            dp[i]= dp[i-1] +dp[i-2] + dp[i-3];

        }

        int T = Integer.parseInt(br.readLine());
        while(T>0){
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num]).append("\n");
            T--;
        }
        System.out.println(sb);
    }
}
