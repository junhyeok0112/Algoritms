package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1749_점수따먹기 {

    static int n, m;
    static int[][] arr;
    static int[][] prefix_sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        prefix_sum = new int[n][m];         //누적 합이 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = Integer.MIN_VALUE;
        //첫행 , 첫 열 값 채우기
        prefix_sum[0][0] = arr[0][0];
        for (int i = 1; i < n; i++) {      //행 채우기
            prefix_sum[i][0] = prefix_sum[i - 1][0] + arr[i][0];
            ans = Math.max(prefix_sum[i][0] , ans); //시작 부분 수열들 구해놓기 -> 나중에 범위 넘어가는거 방지하기 위해서
        }

        for (int i = 1; i < m; i++) {
            prefix_sum[0][i] = prefix_sum[0][i - 1] + arr[0][i];
            ans = Math.max(prefix_sum[0][i] , ans); //범위 넘어가는거 방지하기 위해 첫번째 열, 행에 대한 최대값들 다 구해놓기
        }

        //전체 누적합 구하기
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                prefix_sum[i][j] = prefix_sum[i][j-1] + prefix_sum[i-1][j] - prefix_sum[i-1][j-1] + arr[i][j];
            }
        }


        //완탐으로 모든 부분 행렬 확인

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m; j++){      //시작 위치 (i,j)
                for(int x = 0 ; x<n-i; x++){    //행으로는
                    for(int y = 0; y<m-j; y++){
                        //부분 배열의 합 구하기
                        int temp = 0;
                        if(i-1< 0 && j-1<0){
                            temp = prefix_sum[i+x][j+y];
                        } else if(i-1 <0 ){    //첫번째 행일때
                            temp = prefix_sum[i+x][j+y] - prefix_sum[i+x][j-1];
                        } else if(j-1<0){
                            //첫번째 열일 때
                            temp = prefix_sum[i+x][j+y] - prefix_sum[i-1][j+y];
                        } else{
                            temp = prefix_sum[i+x][j+y] + prefix_sum[i-1][j-1] - prefix_sum[i+x][j-1] - prefix_sum[i-1][j+y];
                        }
                        ans = Math.max(ans , temp);
                    }
                }

            }
        }
        System.out.println(ans);


    }
}
