package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15645_N과M1_re {

    static int n,m;
    static StringBuilder sb = new StringBuilder();
    static boolean[] select;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        select = new boolean[n+1];

        pro(0);
        System.out.println(sb.toString());
    }

    static void pro(int num){
        if(num>=m){
            for(int i = 1; i<=n; i++){
                if(select[i]){
                    sb.append(i).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        for(int i = 1 ;i<=n ;i++){
            if(select[i]) continue;
            select[i] = true;
            pro(num+1);
            select[i] = false;
        }
    }

}
