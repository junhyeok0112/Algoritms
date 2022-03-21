package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C11_날짜계산기_최준혁 {

    static int[] arr = new int[4];
    static int[] month = {31,28,31,30,31,30,31,31,30,31,30,31};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int tc = 0;
        while (tc++ <t){
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i<4 ;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int ans = 0;
            for(int i = arr[0]+1 ; i<arr[2]; i++){
                ans+=month[i-1];
            }
            if(arr[0] == arr[2]){
                ans += Math.abs(arr[1] - arr[3]) + 1;
            }else{
                ans += Math.abs(month[arr[0]-1] - arr[1])+1 + arr[3];
            }



            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}
