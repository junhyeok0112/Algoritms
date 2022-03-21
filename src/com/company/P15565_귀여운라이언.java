package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15565_귀여운라이언 {

    static int N,K;
    static int[] map ;
    static int res = Integer.MAX_VALUE; //최소값


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i =1 ;i<=N ;i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        int R = 0;
        int cnt = 0;
        for(int L = 1; L <=N ;L++){
            if(map[L] == 2) {   //처음이면 볼필요 없음
                continue;
            }
            while (R < N && cnt <K){
                R++;
                if(map[R] == 1) cnt++;

            }
            if( res == Integer.MAX_VALUE && cnt<K){         //갱신 안되었는데 끝까지 봤으면
                System.out.println(-1);
                return;
            }
            if(cnt >=K) {
                int size = R - L + 1;
                res = Math.min(res, size);
                if (R == N) break;
                cnt--;
                while (true) {
                    L++;
                    if (map[L] == 1) break;      //L이 1인 지점까지 L줄임
                }
                L--;
            }
        }
        if(R == 0){         //전부 2일떄
            System.out.println(-1);
            return;
        }

        System.out.println(res);


    }
}
