package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1417_국회의원선거 {

    static int n,m;
    static int[] vote;
    static int max = 0 , ans = 0;
    static int max_idx = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        vote = new int[n+1];
        for(int i = 1 ; i<=n ;i++){
            vote[i] = Integer.parseInt(br.readLine());
            if(i != 1){
                if(max < vote[i]){      //다른 후보들 중 최대 투표자 찾음
                    max = vote[i];
                    max_idx = i;
                }
            }
        }

        if(n == 1 ) {
            System.out.println(0);
            return;
        }
        //다솜이 투표수가 제일 높아질 떄 까지 다른 최대 후보자들의 투표 수 1개씩 뺏어옴
        //PQ 이용하면 좀 더 짧게 가능
        while (vote[1] <= max){
            vote[1]++;              //득표 옮기기
            vote[max_idx]--;
            max = 0 ;               //옮기고 max값 갱신
            ans++;
            for(int i = 2; i<=n ;i++){
                if(max < vote[i]){      //다른 후보들 중 최대 투표자 찾음
                    max = vote[i];
                    max_idx = i;
                }
            }
        }

        System.out.println(ans);

    }
}
