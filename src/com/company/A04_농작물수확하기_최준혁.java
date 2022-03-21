package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A04_농작물수확하기_최준혁 {

    static int tc, n;
    static int[][] arr;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = 0;
        tc = Integer.parseInt(br.readLine());
        while (t++ < tc){
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            for(int i= 0;i<n;i++){
                String str = br.readLine();
                for(int j = 0 ; j<n;j++){
                    arr[i][j] = str.charAt(j) - '0';
                }
            }
            int total = 0;
            //더하는 갯수 증가
            int cnt = n/2;
            for(int i = 0 ; i<=n/2; i++){
//                for(int j = n/2-i ; j<=n/2+i ;j++){
//                    total += arr[i][j];         //값 더하기
//                }
                for(int j = cnt ; j <n-cnt; j++){
                    total += arr[i][j];
                }
                cnt--;      //cnt 감소시켜가면서 봐야할 갯수 체크
            }

            //더하는 갯수 감소
            cnt = 1;            //몇개씩 감소시킬 것인가 ?
            for(int i = n/2+1 ; i<n ;i++){  //남은 부분들 더함
                for(int j = cnt ; j <n-cnt; j++){
                    total += arr[i][j];
                }
                cnt++;
            }


            sb.append("#"+t+" ").append(total);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
