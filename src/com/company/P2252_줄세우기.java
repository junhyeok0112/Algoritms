package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2252_줄세우기 {

    static int N,M;
    static int indeg[];
    static boolean[] visit; //indeg가 0이 되었을 때 방문체크
    //static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();            //그래프 구현 할 ArrayList
    static ArrayList<Integer>[] adj2 ;
    static Queue<Integer> q = new LinkedList<>();           //후보들 집어 넣을 Queue


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
//        for(int i = 0 ; i<=N; i++){
//            adj.add(new ArrayList<>());
//        }
        adj2 = new ArrayList[N+1];
        for(int i = 0 ; i<=N; i++){
            adj2[i] = new ArrayList<>();
        }
        visit = new boolean[N+1];
        indeg = new int[N+1];
        for(int i = 0 ;i <M; i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            adj2[num1].add(num2);                            //num1의 키가 num2보다 작다는 거 의미 -> 간선생성 ->이 방향있는 간선으로 위상정렬
            indeg[num2]++;
        }

        for(int i = 1; i<=N; i++){
            if(indeg[i] == 0){
                q.add(i);                                   //indegree가 없으면 제일 먼저 세워도 되므로 제일 앞에 세우기 위해 후보를 위한 queue에 넣음
                visit[i] = true;
            }
        }

        while (!q.isEmpty()){
            int x = q.poll();
            sb.append(x).append("\n");                      //최대 32000개 출력되므로 stringBuilder쓰는게 좋음
            for(int y : adj2[x]){                            //x에서 갈 수 있는 정점들의 indegree 감소시켜주면서 x정점 삭제
                indeg[y]--;
                if(indeg[y] == 0 ) q.add(y);                //감소 시켰을 때 0이면 visti 체크안해도 처음 방문한 것
            }
        }
        System.out.println(sb);
    }
}
