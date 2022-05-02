package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3079_입국심사 {

    static int n,m;
    static int[] arr;           //얼마나 걸리는게 저장되어 있음
    static int[] time;          //해당 인덱스에 있는 사람이 처리되기까지 남은 시간
    static long ans = -1;
    static int max_time = -1;   //최대 걸리는 시간 값

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        time = new int[n];
        for(int i = 0 ; i<n ;i++){
            arr[i] = Integer.parseInt(br.readLine());
            max_time = Math.max(max_time, arr[i]);            //최대 걸리는 시간 값
        }

        //시간을 기준으로 이분탐색합니다. 즉  0초 ~ 최대 걸리는 시간(max_time * 10억) 범위 내에서 m명을 처리할 수 있는 최적의 시간을 찾는 것입니다.
        //이 때 최적의 시간을 이분탐색을 이용해서 찾습니다. -> mid 초 내에 m명 이상을 처리할 수 있으면 더 짧은 시간에도 가능할 수 있다는 뜻이므로 right = mid -1로 시간 범위를 줄여서 다시 한번 탐색해 봅니다.
        //만약 mid 초 내에 m명을 처리할 수 없다면 -> 더 큰 가능한 시간대가 있다는 뜻이므로 left = mid +1로 범위를 다시 셋팅해서 가능한 시간대를 구해야합니다.
        //범위가 엄청 커질 수 있는게 시간이므로 시간을 기준으로 이분탐색한다 ! 라고 생각해보자.
        long left = 0;
        long right = (long) max_time * m;       //최대 걸릴 수 있는 시간 -> 심사원이 걸리는 최대 시간 * 최대 인원수(m)

        while (left <= right){
            long mid = (left + right) / 2;      //걸리는 시간
            long cnt = 0;                       //mid 초가 걸렸을 때 몇명을 처리할 수 있는지 세어주는 cnt
            for(int i = 0 ; i<n ;i++){          //각 심사원들이 걸리는 시간으로 mid초 를 나눈 몫이 mid 초 동안 작업하면 처리할 수 있는 사람의 수이다
                cnt +=(mid/arr[i]);
            }
            //처리할 수 있는 사람(cnt)가 처리해야할 사람 (m) 보다 크거나 같으면 mid 초 보다 더 짧게도 처리 가능 -> right = mid -1 , 이 때 우선 처리 가능한 경우이므로 ans = mid로 갱신
            //이런식으로 쪼개다보면 제일 적합한 최소의 mid 값을 찾을 수 있다. 즉 그게 정답 -> 이분탐색
            if(cnt >= m){
                ans = mid;
                right = mid -1;
            } else{     //처리할 수 있는 사람(cnt)가 처리해야할 사람(m)보다 작으면 시간이 더 걸린다 -> 즉 left = mid +1 로 mid 보다 큰 범위에서 가능한 시간 재탐색
                left = mid +1;
            }
        }

        System.out.println(ans);        //최적의 시간이 찾아진다. -> 범위가 엄청 크고 최적의 조건을 찾아야한다 -> 이분탐색.

    }
}
