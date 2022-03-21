package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class P2098{
    int x;
    int y;
    P2098(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class P2098_외판원순환 {

    static int n;
    static int[][] w;
    static int INF =  100000000;
    static int sum = 0;
    static int[][] dp ;
    static boolean[] visit;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = new int[n][n];
        dp = new int[n][(1<<n) -1 ];    //외판원 순환에서 dp[i][j] 가 나타내는 것은 현재 위치가  i이고 비트마스킹으로 j 도시들을 방문했을 때 , 시작점까지 돌아가기 위해 방문해야하는 거리의 최소값
        for(int i = 0 ; i<n ;i++) Arrays.fill(dp[i], INF);          //dp 값 초기화
        visit = new boolean[n];
        StringTokenizer st ;
        for(int i = 0 ; i< n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<n ;j++){
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //한점에서의 최소 싸이클 값만 구하면됨
        System.out.println(dfs(0,1));


    }

    //중복 계산을 피하기 위해 DP 사용
    //5->4->1 이라는 길을 또 다시 구하지 않기 위해 dp[5][10111(2)]라는 값을 구해놓은걸 사용한다.
    static int dfs(int cur, int bitVisit){
        if(bitVisit == (1<<n) -1 ){         //전부다 방문했을 경우 즉 시작점까지 가는 거리만 남았을 경우
            if(w[cur][0] == 0) return INF;  //만약 다시 못돌아가면 INF 리턴
            return w[cur][0];               //돌아갈 수 있으면 그 값 리턴
        }

        if(dp[cur][bitVisit] != INF){       //중복계산 안하기 위해 계산해놓은 값 리턴턴
           return dp[cur][bitVisit];
        }

        for(int i = 0 ; i<n ;i++){          //현재 지점에서 n개의 도시 방문할 수 있는지 체크
            if((bitVisit & (1<<i)) == 0 && w[cur][i] != 0){        //(bitVisit & (1<<n)) == 0 는 n 번 마을을 방문했는지 안했는지 체크 , 0이면 방문안함 ,1이면 방문 , 그리고 방문 가능한 곳인지 체크
                dp[cur][bitVisit] = Math.min(dp[cur][bitVisit] , dfs(i , bitVisit | (1<<i)) + w[cur][i]);
            }
        }

        return dp[cur][bitVisit];           //결국 최종적으로 시작점에서 시작점만 방문했을때 나머지 전부 방문하고 돌아오는 최소거리를 나타내야하므로 dp[cur][bitVisist]를 출력한다.
    }
}
