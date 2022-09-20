package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//본인이 얼리어답터면 주변은 얼리어답터이든 아니든 상관없다
//본인이 얼리어답터가 아니면 주변은 얼리어답터여야한다 -> 가 핵심
//위의 공식이 트리에서의 DP의 핵심 -> 해당 지점이 어떤거이냐에 따라서 달라짐
public class P2533_사회망서비스 {

    static int n;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static int[][] dp;              //dp가 의미하는 것은 현재 노드를 서브트리로 하는 것들 중 필요한 최소의 얼리어답터 수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visit = new boolean[n+1];
        dp = new int[n+1][2];       //[][0] 본인이 얼리어답터일때 서브 트리들 중 얼리어답터의 수 , [][1] 본인이 얼리어답터가 아닐때 서브트리들이 얼리어답터일때 ?
        graph = new ArrayList[n+1];
        for(int i = 0 ;i<=n ;i++) graph[i] = new ArrayList<>();
        for(int i = 0 ; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1); //시작점을 1로 잡기
        System.out.println(Math.min(dp[1][0] , dp[1][1]));
    }

    static void dfs(int start){
        if(visit[start]) return;    //방문 한곳이면 넘어가기
        visit[start] = true;

        dp[start][0] = 1;          //해당 지점이 얼리어답터면 적어도 자기 자신 1개
        dp[start][1] = 0;          //해당 지점이 얼리어답터가 아니면 자기 자신은 우선 0 -> 주변의 영향

        for(int next : graph[start]){
            if(visit[next]) continue;       //방문한 곳이면 건너뛰기
            dfs(next);                      //최하위 자식까지 내려가기

            //내가 얼리어답터면 자식들은 상관없음 , 즉 둘 중 적은거 더해주면됨
            dp[start][0] += Math.min(dp[next][1] , dp[next][0]);
            //내가 얼리어답터가 아니면 자식들은 반드시 얼리어답터
            dp[start][1] += dp[next][0];
        }
    }
}
