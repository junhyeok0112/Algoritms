package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point implements Comparable<Point> {
    int x;
    int dist;

    Point(int x , int dist){
        this.x = x;
        this.dist = dist;
    }
    @Override
    public int compareTo(Point o) {
        if(this.dist > o.dist){
            return 1;
        } else{
            return -1;
        }
    }
}


public class P1916_최소비용구하기 {

    static int N,M;
    static int[] dist;
    static ArrayList<Point>[] adj;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        adj = new ArrayList[N+1];
        for(int i = 1 ; i<=N; i++) adj[i] = new ArrayList<>();
        for(int i = 0 ; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            adj[num1].add(new Point(num2 , dist));              //num1에서 num2로 dist 만큼의 거리로 갈 수 있다.
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());             //목적지

        PriorityQueue<Point> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Point(start,0));
        while (!pq.isEmpty()){
            Point r = pq.poll();                            //Point로 할필요가 없음 어짜피 거기까지의 거리는 필요없음 따라서 int cur로 받아도됨
            if(dist[r.x] < r.dist) continue;                //도착한 정점까지의 거리가 이미 최소거리면 안봐도 되므로 넘어감 ->visit로 방문체크해도됨
            for(Point Edge : adj[r.x]){                     //도착한 정점과 연결된 간선들 체크
                if(dist[Edge.x] > dist[r.x] + Edge.dist){   //도착한 정점과 연결된 간선까지의 거리가 도착한 정점까지의 거리 + 정점에서 간선까지의 거리보다 크면
                    dist[Edge.x] = dist[r.x]+ Edge.dist;   //최단거리 갱신 가능하므로 갱신 -> 시작점에서 Edge까지 가는것보다 r걸쳐서 Edge가는게 더 가까워서
                    pq.add(new Point(Edge.x , dist[Edge.x]));
                }
            }
        }

        System.out.println(dist[end]);



    }
}
