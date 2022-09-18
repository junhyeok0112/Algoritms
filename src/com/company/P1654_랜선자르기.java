package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1654_랜선자르기 {

    static int k,n;
    static ArrayList<Integer> arr = new ArrayList<>();  //랜선 길이 저장하는리스트

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int minLen = Integer.MAX_VALUE;    //K개의 랜선 중 최소 길이
        for(int i = 0 ; i<k ;i++){
            int cur = Integer.parseInt(br.readLine());
            arr.add(cur);
            minLen = Math.min(minLen , cur);

        }

        //이분 탐색을 이용해서 최대 길이 찾기
        long start = 1;
        long end = Integer.MAX_VALUE;       //랜선 길이가 인트 범위이면 오버플로우 발생
        long ans = 0;
        while(start <= end){
            long mid = (start + end) / 2;    //이 길이로 자르겠다
            long cnt = 0;    //같은 길이로 자를때 나오는 갯수
            for(int i = 0 ;i<k ;i++){
                cnt += (arr.get(i) / mid);
            }
            if(cnt < n){        //더 작은 길이로 잘라야할때
                end = mid-1;
            } else{             //더 큰 길이로 자를 수 있는지 판단해야할때
                start = mid+1;
                ans = Math.max(ans , mid);
            }
        }
        System.out.println(ans);
    }
}
