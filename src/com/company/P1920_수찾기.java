package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1920_수찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int M =Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<M ;i++){
            int num = Integer.parseInt(st.nextToken());
            System.out.println(find(N,num, arr));
        }
    }

    static int find(int N ,int num , int[] arr){
        int left = 0;
        int right = N-1;
        int mid = 0;
        while(left <= right){
            mid = (left + right) / 2;
            if(arr[mid] < num){//찾는 수가 기준점보다 오른쪽이면
                left = mid + 1;
            } else if(arr[mid] > num){ // 찾는 수가 기준점보다 왼쪽이면
                right = mid - 1;
            } else{ //찾았으면
                return 1;
            }
        }
        return 0;

    }
}
