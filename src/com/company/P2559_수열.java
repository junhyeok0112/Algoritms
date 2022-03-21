package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2559_수열 {

    static int N,K;
    static int[] map ;
    static int res = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i= 1 ; i<=N ;i++) map[i] = Integer.parseInt(st.nextToken());

        int sum = 0;
        int R = 0;
        for(int L = 1 ; L<=N ;L++){
            while(R <N && (R-L+1) < K){ //차이가 K일때까지 , R이 딱 L보다 K-1만큼 떨어져있음
                R++;
                sum+=map[R];
            }
            res = Math.max(res , sum);
            if(R == N) break;   //R을 끝까지 봤으면 더이상 볼 필요 없으므로 종료
            sum -= map[L];  //다음을 위해 sum에서 현재 해당하는 L위치 빼주고 다음것부터봄
        }
        System.out.println(res);

    }
}
