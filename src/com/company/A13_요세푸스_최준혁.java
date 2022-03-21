package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A13_요세푸스_최준혁 {

    static int n,k;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i =1 ; i<=n ;i++){
            q.add(i);
        }
        System.out.print("<");
        int cnt = 0;
        while (!q.isEmpty()){
            cnt++;
            int cur =q.poll();
            if(cnt == k){
                if(!q.isEmpty()){
                    cnt= 0;
                    System.out.print(cur + ", ");
                    continue;
                } else{
                    cnt= 0;
                    System.out.print(cur + ">");
                    continue;
                }
            }
            q.add(cur);
        }
    }
}
