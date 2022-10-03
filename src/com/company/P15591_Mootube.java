package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15591_Mootube {

    static int[][] graph;
    static int n,q;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visit;     //DFS 시 방문했는지 체크 하는 함수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        graph = new int[n+1][n+1];
        for(int i =1 ; i<=n ;i++){
            Arrays.fill(graph[i] , Integer.MAX_VALUE);
            graph[i][i] = 0;
        }

        for(int i = 0 ; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            graph[start][end] = dis;
            graph[end][start] = dis;
        }





        for(int i = 0 ; i<q ;i++){
            st= new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            visit = new boolean[n+1];
            dfs(v,v,Integer.MAX_VALUE);
            int cnt = 0;
            for(int j = 1; j<=n ;j++){
                if(v == j ) continue;
                if(graph[v][j] >= k ) cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int start , int cur , int min){
        visit[cur] = true;

        if(start != cur && min < graph[start][cur]){
            graph[start][cur] = min;
            graph[cur][start] = min;
        }

        for(int i = 1; i<=n;i++){
            if(visit[i] || graph[cur][i] == Integer.MAX_VALUE) continue;        //방문 했거나 못가는 곳이면 넘엄감
            dfs(start ,i, Math.min(min , graph[cur][i]));
        }

    }
}
