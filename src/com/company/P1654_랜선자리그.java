package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//랜선의 길이가 0~21억이므로 랜선의 길이를 이분탐색을 이용해서 정한다
public class P1654_랜선자리그 {

    static int k, n;
    static int[] arr;
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[k + 1];
        long max = -1;
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max =Math.max(max , arr[i]);
        }
        long left = 1;
//        long right = (int) Math.pow(2,31) ;
        long right = max;
        while (left <= right) {
            long mid = (left + right) / 2;
            int cnt = 0;
            for (int i = 0; i < k; i++) {
                cnt += arr[i] / mid;
            }
            if (cnt < n) { //n개를 못만들면 mid 값을 줄여야함
                right = mid - 1;
            } else {     //n개 이상을 만들면 정답 후보가 될 수 있음 , 최대값 구해야함
                left = mid + 1;
                ans = Math.max(ans,mid);          //정답 후보 설정
            }
        }
        System.out.println(ans);

    }
}
