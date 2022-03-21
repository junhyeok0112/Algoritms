package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17404_RGB거리2 {

    static int n;
    static int[][] color;
    static int[][] dp;
    static int INF = 10000001;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        color = new int[n+1][3];
        dp = new int[n+1][3];
        for(int i = 1 ; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j= 0 ;j<3; j++){            //i번쨰 집을 빨파초로 칠하는데 드는 비용
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int min = Integer.MAX_VALUE;
        //1번 집이 3가지 색으로 칠해질때를 나눠서 모두 계산해야합니다. N번과 달라야하므로
        //따라서 빨간집일 때 초록, 파란색집은 아예 선택이 안되게 매우 큰 수로 지정해놓습니다.
        //위 과정을 빨 초 파에 반복합니다
        for(int k = 0;  k<3 ;k++){

            for(int i = 0 ; i<3; i++){
                if(i == k) dp[1][i] = color[1][i];          //첫번째 집이 빨간색 ,파란색, 초록색 등으로 선택 후
                else dp[1][i] = INF;                        //나머지들은 선택 안되게 범위 값(1000)을 넘는 값을 셋팅
            }

            //dp배열 채우기 이전까지 최소값에 더해줌
            for(int i =2 ; i<=n ;i++){
                dp[i][0] = Math.min(dp[i-1][1] ,dp[i-1][2]) + color[i][0];
                dp[i][1] = Math.min(dp[i-1][0] ,dp[i-1][2]) + color[i][1];
                dp[i][2] = Math.min(dp[i-1][0] ,dp[i-1][1]) + color[i][2];

            }

            //최소값 갱신 , 이 때 첫번째 집을 K색으로 칠하지 않은 경우에 대해서만 모두 구한다.
            for(int i =0; i<3 ; i++){
                if(i != k){
                    min = Math.min(dp[n][i], min);
                }
            }
        }

        System.out.println(min);

    }
}
