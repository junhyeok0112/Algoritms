package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B02_파리퇴치_최준혁 {

    static int tc,n,m;
    static int[][] arr;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        int t = 0;
        while (t++<tc){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[n][n];
            for(int i = 0 ; i< n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j< n ;j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int max = -1;
            for(int i = 0 ; i<n ;i++){
                for(int j = 0 ;j<n;j++){
                    int temp = 0;
                    if(i-1+m >=n || j-1+m >=n) continue;     //범위밖이면
                    for(int k = i ;k<i+m ;k++){
                        for(int h =j ;h <j+m ;h++){
                            temp+=arr[k][h];
                        }
                    }
                    max = Math.max(temp,max);
                }
            }



            sb.append("#"+t+" ").append(max);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
