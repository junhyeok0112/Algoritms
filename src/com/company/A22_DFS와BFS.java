package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A22_DFS와BFS {

    static int n,m,v;
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        visit = new boolean[n+1];
        for(int i = 0 ; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0 ; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i = 1; i<=n;i++){
            Collections.sort(graph[i]); //정렬
        }

        dfs(v);
        sb.append("\n");
        visit = new boolean[n+1];       //visit 초기화
        bfs(v);
        System.out.println(sb.toString());
    }

    static void dfs(int start){
        visit[start] = true;
        sb.append(start).append(" ");
        for (int cur : graph[start]){
            if(!visit[cur]){
                dfs(cur);
            }
        }
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        while (!q.isEmpty()){
            int r = q.poll();
            sb.append(r).append(" ");
            for(int cur : graph[r]){
                if(!visit[cur]){
                    visit[cur] = true;
                    q.add(cur);
                }
            }
        }
    }
}
