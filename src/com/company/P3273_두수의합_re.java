package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3273_두수의합_re {

    static int n,x;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ;i<n ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int ans = 0;
        int left = 0;
        int right = n-1;
        while (left < right){
            int val = arr[left] + arr[right];
            if(val == x){
                ans++;
                left++;
                right--;
            } else if(val < x){
                left++;
            } else{
                right--;
            }
        }
        System.out.println(ans);
    }
}
