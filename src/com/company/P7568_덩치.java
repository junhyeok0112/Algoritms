package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P7568_덩치 {

    static int n;
    static int[][] arr;
    static int[] rank;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];        //몸무게 , 키 저장
        rank = new int[n];
        Arrays.fill(rank , 1);
        StringTokenizer st ;
        for(int i = 0 ; i< n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i<n ;i++){
            for(int j = 0; j<n ;j++){
                if(i == j) continue;
                if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]){
                    rank[i]++;
                }
            }
        }

        for(int ans : rank){
            System.out.print(ans + " ");
        }
    }
}
