package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
//이분 그래프는 모든 정점에서 인접한 정점이 다른 색으로 칠해져있을 경우 이분 그래프이다
//모든 다른 색으로 칠해져있으면 2가지 집합으로 인접하지 않게 나눌 수 있다라고 이해되기 때문이다
public class P1707_이분그래프 {

    static int k,v,e;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] graph;
    static int[] visit;
    static boolean flag;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        while (k-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            graph = new ArrayList[v+1];
            visit = new int[v+1]; //0이면 칠해져있지 않음, 1 , 2 => 2가지 색상 존재
            flag = true;
            for(int i = 0 ; i<=v ; i++) graph[i] = new ArrayList<>();
            for(int i = 0 ; i<e ;i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                graph[start].add(end);
                graph[end].add(start);
            }

            //그래프가 완전히 연결된게 아니라 여러 덩이로 떨어져있을 때 다른 컴포넌트 트리도 확인해야하므로
            //방문안한 모든 노드들에 대해 체크합니다.
            for(int i = 1 ; i<=v ;i++){
                if(visit[i] == 0 ){
                    dfs(i,1 , true);       //색을 홀수 , 짝수로 구분해서 칠하자 홀수이면 true가 가야함
                }
            }
            if(flag) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");

        }
        System.out.println(sb.toString());
    }

    static void dfs(int start , int color, boolean odd){
        visit[start] = color;        //방문했어

        for(int next : graph[start]){
            if(visit[next] == 0) dfs(next , color+1 , !odd);       //방문 안했으면 방문해서 체크
            else{       //방문 한곳이면
                if((visit[next] % 2 == 1) && odd) { //다음 곳이 홀수일때
                    flag = false;
                }
                if((visit[next] % 2 == 0) && !odd){
                    flag = false;
                }
            }
        }
    }
}
