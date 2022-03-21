package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11725_트리의부모찾기_re {

    static int n;
    static ArrayList<Integer>[] arr;
    static int[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new ArrayList[n+1];
        for(int i = 0 ; i<=n ;i++) arr[i] = new ArrayList<>();
        visit = new int[n+1];
        for(int i = 0 ; i<n-1 ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x].add(y);
            arr[y].add(x);
        }
        visit[1] = -1;
        dfs(1);
        for(int i =2 ; i<=n; i++){
            sb.append(visit[i]).append("\n");
        }
        System.out.println(sb.toString());

    }

    static void dfs(int x){
        for(int next : arr[x]){
            if(visit[next] == 0 ){
                visit[next] = x;    //부모 저장
                dfs(next);
            }
        }
    }
}
