package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class P12865_평범한배낭 {

    static int N,K;
    static int[][] dp;          //i무게에 담을 수 있는 j개 물건담을 수 있는 경우 최대 가치
    static int[] weight;
    static int[] value;

    //i번째에 있는 물건을 선택하냐 안하냐를 봄
    //첫번쨰 선택할 경우 ,안할경우 전부 , 두번째 선택할 경우 안할경우 전부... 이렇게 전부봄
    //따라서 배열에는 모든 경우가 저장됨

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        weight = new int[N+1];
        value = new int[N+1];
        dp = new int[N+1][K+1];
        for(int i = 1 ; i<=N ;i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            weight[i] = W;
            value[i] = V;
        }


        //4번째 물건을 넣을 차례인데 같은 무게에서 3번째 물건을 넣은 경우의 값이 존재한다하자
        //그러면 3개 넣었을때 무게가 꽉차있어서 4번째 물건을 추가 못할수도있으므로
        //기본적으로 이전(3개 물건 넣었을 때) 값을 받아온다
        //이때 만약 4번째 물건을 넣을 수 있는 무게 여유가 남아있으면 값을 비교해서 갱신
        for(int i =1 ; i<=N ; i++){
            for(int j = 1; j<=K ;j++){
                dp[i][j] = dp[i-1][j];  //j만큼 무게 에 i번째 물건 넣을까 말까 결정하는데 넣을 수 있을지 없을지 모르므로 일단 이전 값받아옴
                if(j - weight[i] >=0){  //현재 무게에서 i번째 무게 뻈을때 0보다 크거나 같으면 i번쨰 물건을 넣을 수 도 있다는 뜻
                    dp[i][j] = Math.max(dp[i-1][j-weight[i]] + value[i] , dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[N][K]);



    }
}
