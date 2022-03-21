package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P7579_앱 {

    static int N, M;
    static int[] memory ;   //사용중인 메모리
    static int[] cost;      //비용

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memory = new int[N+1];
        cost = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=N ;i++){
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1;  i<=N ;i++){
            cost[i] =Integer.parseInt(st.nextToken());
        }

        //dp[i][j] 에서 j를 메모리 기준으로 잡으면 배열이 너무 커서 메모리 초과가 납니다. 따라서 cost 기준으로 잡습니다.
        //즉 dp[i][j] 는 i까지 썻을떄 j비용을 채울 수 있는 최대 메모리 크기 라고 봅니다.
        //이러면 최대 j까지 채울 수 있는 용량의 최대 비용을 구하는 냅백 문제와 같아집니다. 즉 value가 메모리 값이 됩니다.
        int[][] dp = new int[N+1][10001];
        for(int i = 1 ; i<=N ;i++){
            for(int j = 1 ; j<10001 ;j++){
                dp[i][j] = dp[i-1][j];      //초기 값 채울 수 없는 경우
                if(j-cost[i] >=0){          //만약 지금 채워야하는 비용에서 지금 앱을 지워서 채울수 있는경우
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i-1][j-cost[i]] + memory[i]);   //i번째를 보기전에 j-cost[i]한 값 + memory[i] 한 값(i번째를 픽했을때)와 픽 안했을떄 dp[i-1][j] 를 비교
                }
            }
        }

        //구해야하는 것은 M바이트 이상일떄의 cost의 최소값 -> 즉 j의 최소값
        //N개까지 봤을떄 처음 M이상일때 나오는 j의 값이 정답
        for(int i = 1; i<10001 ;i++){
            if(dp[N][i] >=M){
                System.out.println(i);
                return;
            }
        }
    }
}
