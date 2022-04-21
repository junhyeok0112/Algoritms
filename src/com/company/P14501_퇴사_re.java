package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14501_퇴사_re {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n+2];
        int[] p = new int[n+2];
        int[] dp = new int[n+2];                //dp[i] 는 i일 일때 최대값
        for(int i = 1 ; i<=n ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        //i일에서 T[i]만큼 걸린 후 작업이 끝났을때의 날짜에 해당하는 dp 값을 갱신해주는 방식으로 풀어야한다.
        //현재 i에서 상담일(t[i])를 더해 범위를 초과하지 않는 경우 dp 배열에 금액(p[i])를 더해준다.
        //점화식 : if (i + t[i] <= n) dp[i] = max(dp[i + t[i]], dp[i + t[i]] + p[i])

        //또한 상담이 완료 되었을떄 돈을 받는다 즉 4일에 1일짜리가 완료되면 5일에 돈 갱신
        //즉 마지막 날에 일을 할 수 있으면 퇴사날인 n+1일날 돈을 받을 수 있다 . 따라서 dp[n+1]에 최대값이 저장될 것이다.
        //따라서 dp 배열도 n+2까지해야하고 , 체크도 n+1까지 해야한다 => dp[n+1]갱신
        int max = 0;
        for(int i = 1; i<=n+1 ;i++){
            dp[i] = Math.max(dp[i],max);    //만약 갱신이 안되는 경우 이전 값들 중 최대 값을 저장해야한다.
            if(i+t[i]-1<=n){            //i일에서 t[i]만큼 일할때 n 범위 안이면 단, i= 1부터 시작하므로 일이 범위안에 끝나는지 체크하려면 -1, dp[i+t[i]]의 기존 값과 , 시작날 dp[i] + 비용 p[i] 의 값중 큰 것이 일이 끝났을때 받을 수 있는 금액의 최대 값이다.
                dp[i+t[i]] = Math.max(dp[i+t[i]] , dp[i]+p[i]);
            }
            max = Math.max(dp[i],max);
        }
        System.out.println(max);

    }
}
