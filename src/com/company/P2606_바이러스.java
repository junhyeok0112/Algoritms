package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2606_바이러스 {

    static int N , M;
    static ArrayList<Integer>[] map;
    static boolean[] visit ;
    static int cnt = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new ArrayList[N+1];
        for(int i =1 ; i<=N ; i++) map[i] = new ArrayList<>();
        visit = new boolean[N+1];
        for(int i = 0 ; i<M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            map[num1].add(num2);
            map[num2].add(num1);
        }
        bfs(1);
        System.out.println(cnt);
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()){
            int r = q.poll();
            for(int t : map[r]){
                if(!visit[t]){
                    visit[t] = true;
                    q.add(t);
                    cnt++;
                }
            }
        }
    }
}
