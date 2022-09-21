package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//부모노드가 얼리 어답터면 자식은 얼리 or 얼리 아니든 관계업승ㅁ
//부모노드가 얼리어답텨가 아니면 자식들은 반드시 얼리 어답터여야함
//트리 DP에서 매우 중요
public class P2533_사회망서비스_re {

    static int n;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static boolean[] visit;   //DFS를 통해 방문했는지 체크

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][2];               //dp가 나타내는게 해당 노드를 루트로 한 서브트리에서 필요한 최소 얼리어답터 수 , [][0] 내가 얼리어답터가 아닐 때 , [][1] 내가 얼리어답터일 때
        visit = new boolean[n+1];
        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        StringTokenizer st ;
        for(int i = 0 ; i<n-1 ; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }


        dfs(1);
        System.out.println(Math.min(dp[1][0] , dp[1][1]));
    }

    static void dfs(int cur){
        visit[cur] = true;
        dp[cur][0] = 0;
        dp[cur][1] = 1;     //적어도 내가 얼리어답터일 때

        for(int next : graph[cur]){
            if(visit[next]) continue;
            dfs(next);
            dp[cur][0] += dp[next][1];      //내가 얼리어답터가 아니면 자식들은 모두 얼리어답터터
            dp[cur][1] += Math.min(dp[next][0] , dp[next][1]);      //내가 얼리어답터이면 자식들은 뭐든 상관없음
        }

    }
}
