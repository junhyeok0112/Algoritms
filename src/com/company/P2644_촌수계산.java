package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2644_촌수계산 {

    static int N ,res1,res2 , M;
    static ArrayList<Integer>[] arr ;
    static int[] visit;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visit = new int[N+1];
        arr = new ArrayList[N+1];
        for(int i = 1 ; i<=N ;i++) arr[i] = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        res1 = Integer.parseInt(st.nextToken());
        res2 = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        for(int i = 0 ; i<M ;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x].add(y);
            arr[y].add(x);
        }
        bfs(res1);
        if(visit[res2] == 0){
            System.out.println(-1);
        } else{
            System.out.println(visit[res2] - 1);
        }
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = 1;
        while (!q.isEmpty()){
            int r = q.poll();
            for(int cur : arr[r]){
                if(visit[cur] == 0){
                    visit[cur] = visit[r] + 1;
                    q.add(cur);
                }
            }
        }
    }
}
