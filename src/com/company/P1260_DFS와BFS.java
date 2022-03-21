package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1260_DFS와BFS {
    static int N,M,V;
    static ArrayList<ArrayList<Integer>> adj;
    static int[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        visit = new int[N+1];
        for(int i = 0 ; i<=N; i++){ //정점의 개수만큼 동적배열할당
            ArrayList<Integer> list = new ArrayList<>();
            adj.add(list);
        }
        for(int i =0 ; i<M ;i++){   //간선 개수 입력
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            adj.get(num1).add(num2);
            adj.get(num2).add(num1);
        }

        for(int i =1 ; i<=N;i++){
            Collections.sort(adj.get(i));   //작은 거부터 출력 위해 정렬
            //시간 복잡도 한번 체크
        }
        dfs(V);
        sb.append("\n");
        Arrays.fill(visit , 0); //visit 초기화
        bfs(V);
        System.out.println(sb.toString());

    }
    //인접 리스트로 구현
    static void dfs(int x){
        visit[x] = 1;
        sb.append(x+" ");
        for(int t : adj.get(x)){    //입력 받은것들
            if(visit[t] == 0){ //방문안 했을 경우
                dfs(t);
            }
        }
    }

    static void bfs(int x){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visit[x] = 1;
        while(!q.isEmpty()){
            int r = q.poll();
            sb.append(r+" ");
            for(int t : adj.get(r)){
                if(visit[t] == 0){
                    q.add(t);
                    visit[t] = 1;   //
                }
            }
        }
    }
}
