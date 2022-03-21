package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11724_연결요소의개수 {

    static int N,M;
    static ArrayList<Integer>[] map;
    static boolean[] visit;
    static int cnt = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayList[N+1];
        for(int i =1 ; i<=N ;i++) map[i] = new ArrayList<>();
        visit = new boolean[N+1];
        for(int i = 0 ; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x].add(y);
            map[y].add(x);
        }
        for(int i = 1 ; i<=N ;i++){
            if(!visit[i]){
                dfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static void dfs(int x){
        visit[x] = true;
        for(int cur : map[x]){
            if(!visit[cur]){
                dfs(cur);
            }
        }
    }
}
