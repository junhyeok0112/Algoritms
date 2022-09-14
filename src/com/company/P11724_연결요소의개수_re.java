package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11724_연결요소의개수_re {

    static int n , m,u,v;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new boolean[n+1];
        graph = new ArrayList[n+1];
        for(int i = 0 ; i<=n ; i++) graph[i] = new ArrayList<>();
        for(int i = 0 ; i<m ;i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        int cnt = 0;
        for(int i =1 ; i<=n ;i++){
            if(!visit[i]){
                bfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);

    }

    public static void bfs(int n){
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visit[n] = true;
        while (!q.isEmpty()){
            int r = q.poll();
            for(int next : graph[r]){
                if(!visit[next]){
                    q.add(next);
                    visit[next] = true;
                }
            }
        }
    }


}
