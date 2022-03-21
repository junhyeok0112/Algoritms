package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15650_N과M2 {

    static StringBuilder sb = new StringBuilder();
    static int n,m;
    static boolean[] select;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        select = new boolean[n+1];
        arr = new int[m+1];

        pro(0,1,0);
        System.out.print(sb.toString());
    }

    //idx는 몇번째에 나올 수 인지 , cur 은 이전에 어디까지 봤는지
    static void pro(int cnt , int idx , int prev){
        if(cnt >= m){
            for(int i =1 ; i<= m ;i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = prev+1 ; i<=n ;i++){
            if(!select[i]){
                select[i] = true;
                arr[idx] = i;
                pro(cnt+1 , idx+1, i);
                select[i] = false;
                arr[idx] = 0;
            }
        }

    }
}
