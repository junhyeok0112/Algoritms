package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2606_바이러스_re {

    static int n,m;
    static ArrayList<Integer>[] arr;
    static int ans = 0;
    static boolean[] visit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n =Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new ArrayList[n+1];
        visit = new boolean[n+1];
        for(int i= 0; i<=n ;i++) arr[i] = new ArrayList<>();
        for(int i= 0; i<m ;i++){
            String[] temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            arr[start].add(end);
            arr[end].add(start);
        }
        dfs(1);
        sb.append(ans-1);       //1번 컴퓨터를 제외
        System.out.println(sb.toString());
    }

    static void dfs(int cur){
        visit[cur] = true;
        ans++;
        for(int next : arr[cur]){
            if(!visit[next]){
                dfs(next);
            }
        }
    }
}
