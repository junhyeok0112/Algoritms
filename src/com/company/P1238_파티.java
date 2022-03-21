package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1238_파티 {

    static int X,M,N;
    static int[][] dist;
    static ArrayList<Point>[] adj;
    static PriorityQueue<Point> pq = new PriorityQueue<>();
    static boolean[] visit;
    static int res = -1 ;
    //X가 목적지
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        dist= new int[N+1][N+1];
        for(int i = 1;i <=N ;i++){
            Arrays.fill(dist[i] , Integer.MAX_VALUE);
        }
        for(int i =1 ;i<=N;i++) adj[i] = new ArrayList<>();

        for(int i = 0 ;i<M ;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            adj[num1].add(new Point(num2 , dist));
        }

        for(int i =1 ; i<=N; i++){  //각 학생 i에 대해서 모두 거리 구함
            dist[i][i] = 0; //dist[i][j] 는 i에서 j까지 거리
            pq.add(new Point(i ,0));
            visit = new boolean[N+1];
            while (!pq.isEmpty()) {
                int cur = pq.poll().x;
                if (visit[cur]) continue;        //방문한거면 넘어감
                visit[cur] = true;
                for (Point t : adj[cur]) {
                    if (dist[i][t.x] > dist[i][cur] + t.dist) {
                        dist[i][t.x] = dist[i][cur] + t.dist;
                        pq.add(new Point(t.x, dist[i][t.x]));
                    }
                }
            }
        }


        for(int i = 1; i<=N; i++){  //X까지의 최단거리중 가장 오래걸리는 시간 출력
            res = Math.max(dist[i][X] + dist[X][i] , res);      //오고 가는데 걸리는 시간이므로 갈때 + 올때
        }
        System.out.println(res);

    }
}
