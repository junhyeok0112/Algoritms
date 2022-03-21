package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P1300_K번째수 {

    //배열을 만들어줄 필요도 없음

    static int N,K;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        //이분 탐색을 위해 1과 K로 양끝을 잡는다. K인 이유는 K번째 수를 찾는 것은 반드시 1~K사이에 존재하기 때문이다. -> 숫자들이 중복되어 나와서 반드시 1~K 사이에 있다
        //예를들어 K = 7 인 수(7번째 수)를 찾는다 하면 7번째 수는 반드시 1~7 사이의 숫자일 것이다.
        //시간 절약을 위해 K까지로 잡느다
        long left = 1;
        long right = K;


        //찾는수 B[K] = mid 라고 생각하자
        System.out.println(biSearch(left, right));

    }

    static long biSearch(long left , long right){
        long cnt = 0;
        long mid = (left + right) / 2;
        if(left > right) return res;
        for(int i = 1 ; i<=N ;i++){         //cnt가 mid에 해당하는 값이 몇번째에 있는지 계산
            cnt += Math.min(mid/ i ,N);
        }

        if(K <= cnt){
            res = mid;                      //더 작은 범위에 있다는 뜻이므로 일단 적어둠
            return biSearch(left , mid - 1);
        } else{
            return biSearch(mid +1 , right);
        }

    }
}
