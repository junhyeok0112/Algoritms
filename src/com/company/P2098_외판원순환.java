package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

//시간을 줄이기 위해서 DP 사용 , 메모리를 줄이기 위해서 비트 마스킹 사용
//DP 값은 해당 지점에서 시작점까지 돌아가기 위해 방문해야하는 거리의 최소값
public class P2098_외판원순환 {

    static int n;
    static int[][] w;
    static int INF = 987654321;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = new int[n][n];
        dp = new int[n][(1 << n) - 1];    //외판원 순환에서 dp[i][j] 가 나타내는 것은 현재 위치가  i이고 비트마스킹으로 j 도시들을 방문했을 때 , 시작점까지 돌아가기 위해 방문해야하는 거리의 최소값
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);          //dp 값 초기화
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //한점에서의 최소 싸이클 값만 구하면됨
        System.out.println(dfs(0, 1));


    }

    //중복 계산을 피하기 위해 DP 사용
    //5->4->1 이라는 길을 또 다시 구하지 않기 위해 dp[5][10111(2)]라는 값을 구해놓은걸 사용한다.
    static int dfs(int cur, int bitVisit) {
        if (bitVisit == (1 << n) - 1) {         //전부다 방문했을 경우 즉 시작점까지 가는 거리만 남았을 경우
            if (w[cur][0] == 0) return INF;  //만약 다시 못돌아가면 INF 리턴
            else return w[cur][0];               //돌아갈 수 있으면 그 값 리턴
        }
        //이미 방문한 도시
        if (dp[cur][bitVisit] != -1) {       //중복계산 안하기 위해 계산해놓은 값 리턴턴
            return dp[cur][bitVisit];
        }

        //해당 도시에 출석 표시  -> 방문 표시때문에 계속 시간초과남
        dp[cur][bitVisit] = INF;

        for (int i = 0; i < n; i++) {          //현재 지점에서 n개의 도시 방문할 수 있는지 체크
            if ((bitVisit & (1 << i)) == 0 && w[cur][i] != 0) {        //(bitVisit & (1<<n)) == 0 는 n 번 마을을 방문했는지 안했는지 체크 , 0이면 방문안함 ,1이면 방문 , 그리고 방문 가능한 곳인지 체크
                dp[cur][bitVisit] = Math.min(dp[cur][bitVisit], dfs(i, bitVisit | (1 << i)) + w[cur][i]);   //현재 지점에서 남은 거리와 현재 + i 방문했을 때 남은 지점과의 거리 비교해서 더 작은걸로 갱신
            }
        }

        return dp[cur][bitVisit];           //결국 최종적으로 시작점에서 시작점만 방문했을때 나머지 전부 방문하고 돌아오는 최소거리를 나타내야하므로 dp[cur][bitVisist]를 출력한다.
    }
}
