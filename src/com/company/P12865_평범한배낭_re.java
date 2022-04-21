package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class P12865{
    int w;
    int v;
    P12865(int w ,int v){
        this.w = w;
        this.v = v;
    }
}

public class P12865_평범한배낭_re {

    static int n,k;
    static ArrayList<P12865> arr = new ArrayList<>();
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n+1][k+1];
        for(int i = 0 ; i< n;i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());   //무게
            int v = Integer.parseInt(st.nextToken());   //가치
            arr.add(new P12865(w,v));           //i+1번 물건
        }

        //dp배열 0번 물건일떄 0 , 0무게일떄 0 으로 초기화
        for(int i = 0 ; i<=n ;i++){
            dp[i][0] = 0;   //0만큼의 무게 있을때
            dp[0][i] = 0;   //0번 무게일때
        }

        //dp 배열 채우기 , i번 물건의 무게 여유 있으면 비교하고 아니면 이전값가져오기
        for(int i =1 ; i<=n;i++){   //1번 물건부터
            P12865 cur = arr.get(i-1);  //저장은 0번 부터이므로
            for(int j= 1; j<=k; j++){   //1kg 부터 k 까지
                if(j<cur.w){        //cur 물건을 넣을 공간이 없으면
                    dp[i][j] = dp[i-1][j];  //이전 가치 그대로 가져옴 ->안넣었을때
                } else{             //cur 물건을 넣을 수 있으면
                    //넣을 것인지 말것인지 고려해서 그중 최적값 (max) 값 갱신
                    //앞에는 j-cur.w 는 이전에 cur.w만큼 넣기전 무게의 가치
                    //앞에는 물건넣을때 , 뒤에는 물건 안넣었을때
                    dp[i][j] = Math.max(cur.v + dp[i-1][j-cur.w] , dp[i-1][j]);

                }
            }
        }
        //n번물건을 고려하면서 K무게까지 채웠을떄의 최대값 출력
        System.out.println(dp[n][k]);

    }
}
