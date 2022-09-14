package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class P1753 implements Comparable<P1753>{
    int end;
    int dis;

    P1753(int end , int dis){
        this.end = end;
        this.dis = dis;
    }

    @Override
    public int compareTo(P1753 o) {
        return this.dis - o.dis;
    }
}

public class P1753_최단경로 {

    static int v,e,k;
    static ArrayList<P1753>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        graph = new ArrayList[v+1];
        for(int i = 0 ;i<=v; i++){
            graph[i] = new ArrayList<P1753>();
        }
        for(int i = 0 ; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            graph[start].add(new P1753(end,dis));
        }

        //다익스트라로 최단 거리 구하기
        int[] dist = new int[v+1];   //최단 거리 저장해둔배열
        Arrays.fill(dist , Integer.MAX_VALUE);
        boolean[] visit = new boolean[v+1];
        PriorityQueue<P1753> pq = new PriorityQueue<>();
        pq.add(new P1753(k,0));
        dist[k] = 0;
        while (!pq.isEmpty()){
            P1753 cur = pq.poll();
            if(visit[cur.end]) continue;   //이미 방문한 곳이면 넘어감
            visit[cur.end] = true;
            for(P1753 next : graph[cur.end]){
                if(dist[next.end] >= dist[cur.end] + next.dis){
                    dist[next.end] = dist[cur.end] + next.dis;
                    pq.add(new P1753(next.end , dist[next.end]));
                }
            }
        }

        for(int i = 1 ;i<=v ;i++){
            if(dist[i] == Integer.MAX_VALUE){
                sb.append("INF").append("\n");
            } else{
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
