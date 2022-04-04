package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 실습08_사람네트워크2_최준혁 {

    static int tc,n;
    static int[][] arr;
    static int[][] dis;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        int t = 0 ;
        while (t++<tc){
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            arr = new int[n][n];
            dis = new int[n][n];
            for(int i = 0 ; i< n; i++)Arrays.fill(dis[i] ,Integer.MAX_VALUE);
            for(int i = 0 ; i<n ;i++){
                for(int j =0; j<n;j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());

                }
            }

            for(int i = 0 ; i< n; i++){
                for(int j =0; j<n;j++){
                    if(i == j) dis[i][j] = 0;
                    else{
                        if(arr[i][j] != 0 ) dis[i][j] = arr[i][j];
                        else dis[i][j] = 10000001;
                    }
                }
            }

            //k 는 거쳐가는 점
            for(int k = 0 ; k<n ;k++){
                //i = 출발 노드
                for(int i = 0 ; i<n ;i++){
                    //j는 도착노드
                    for(int j = 0;j<n ;j++){
                        if(dis[i][k] + dis[k][j] < dis[i][j]){
                            dis[i][j] = dis[i][k]+ dis[k][j];
                        }
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            for(int i = 0 ; i<n ;i++){
                int sum = 0;
                for(int j = 0;j <n; j++){
                    sum+=dis[i][j];
                }
                min = Math.min(sum ,min);
            }
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }
}
