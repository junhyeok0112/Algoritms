package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2098_외판원순환_re {

    static int n;
    static int[][] w;
    static int[][] dp;      //[n+1][2^n -1 ] -> [i][j] i를 j의 경로로 방문했을때 다시 돌아갈 떄까지 걸리는 최소 비용
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = new int[n][n];
        dp = new int[n][(1<<n) - 1];
        for(int i = 0 ; i<n ;i++) Arrays.fill(dp[i] , -1);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //DFS와 비트마스킹을 이용해서 최소 경로 구하기
        //0에서 시작해서 돌아오게
        System.out.println(dfs(0, 1));
    }

    static int dfs(int cur, int bitVisit) {
        //만약 전부 방문하고 돌아갈 일만 남았으면
        if (bitVisit == (1 << n) - 1) {
            //만약 현재 모두 돌았으면
            if (w[cur][0] != 0) return w[cur][0];   //돌아갈 수 있으면 그 값 리턴
            else return INF;
        }

        if (dp[cur][bitVisit] != -1) return dp[cur][bitVisit];       //만약 계산된거면 리턴
        dp[cur][bitVisit] = INF;                                    //방문했다고 갱신??

        //현재 도시에서 다른 도시 방문할 수 있는지 확인해보기
        for (int i = 0; i < n; i++) {
            if ((bitVisit & (1 << i)) == 0 && w[cur][i] != 0) {    //다음에 방문할 곳을 방문 안했고 길이 있으면
                dp[cur][bitVisit] = Math.min(dp[cur][bitVisit], dfs(i, bitVisit | (1 << i)) + w[cur][i]);   //현재 계산된 값과 i에서 계산된 값 + 현재 ~ i 가는 값의 합중 작은거
            }
        }
        return dp[cur][bitVisit];
    }
}
