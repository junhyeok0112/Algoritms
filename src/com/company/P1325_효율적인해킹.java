package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1325_효율적인해킹 {

    static int n,m;
    static ArrayList<Integer>[] graph;
    static int[] visit;                    //해당 지점에서 시작하면 최대 몇개를 해킹할 수 있을지
    static int[] ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        ans = new int[n+1];
        for(int i = 0 ;i<=n;i++) graph[i] = new ArrayList<>();
        for(int i = 0 ;i<m; i++){
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            graph[start].add(end);
        }

        for(int i = 1; i<=n ;i++){
            visit = new int[n+1];
            ans[i] = dfs(i);
        }
        int max = -1;
        for(int i = 1 ; i<=n ;i++){
            max = Math.max(ans[i], max);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i<=n ;i++){
            if(ans[i] == max) pq.add(i);
        }
        int pqsize = pq.size();
        for(int i = 0 ; i<pqsize; i++){
            System.out.print(pq.poll()+" ");
        }

    }

    static int dfs(int start){
        visit[start] = 1;

        for(int next : graph[start]){
            if(visit[next] == 0){
                visit[start] += dfs(next);      //현재 지점에서 해킹 가능한거의 수 더하기
            }
        }

        return visit[start];
    }
}
