package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P19637_IF문좀대신써줘 {

    static int n,m;
    static StringBuilder sb = new StringBuilder();
    static String[] title;
    static int[] score;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        title = new String[n];
        score = new int[n];
        for(int i = 0 ; i<n ;i++){
            st = new StringTokenizer(br.readLine());
            title[i] = st.nextToken();
            score[i] = Integer.parseInt(st.nextToken());
        }

        for(int i= 0; i<m ;i++){
            int num = Integer.parseInt(br.readLine());      //점수 입력받고 얘가 어딘지 체크
            find(num);
        }
        System.out.println(sb.toString());
    }
    static void find(int num){
        int left = 0;
        int right = n-1;
        while (left<right){     //서로 같아져도 종료
            int mid = (left + right) / 2;
            if(score[mid] == num){  //같을 경우 같은 점수가 앞에 있을 수도 있으므로 앞에부터 또 탐색
                //여기서 시간초과 걸러줘야함
                for(int i = 0 ;i<=mid; i++){
                    if(score[mid] == score[i]){
                        sb.append(title[i]).append("\n");
                        return;
                    }
                }
            }else if(score[mid] > num){
                right = mid ;
            }else {
                left = mid +1;
            }
        }
        sb.append(title[left]).append("\n");
    }
}
