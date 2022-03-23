package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P1504 implements Comparable<P1504>{
    int y;
    int val;
    P1504(int y , int val){
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(P1504 o) {
        return this.val - o.val;
    }
}

public class P1504_특정한최단경로 {

    static int n,e ,v1 ,v2;
    static ArrayList<P1504>[] map;
    static int[] dist;  //1번에서의 최단거리
    static int[] v1dist;    //v1에서의 최단거리
    static int[] v2dist;    //v2에서의 최단거리
    static boolean[] visit;
    static int MAX= 400000001;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        map = new ArrayList[n+1];
        for(int i = 0 ;i<= n; i++) map[i] = new ArrayList<>();
        dist = new int[n+1];
        v1dist = new int[n+1];
        v2dist = new int[n+1];
        Arrays.fill(dist,MAX);
        Arrays.fill(v1dist,MAX);
        Arrays.fill(v2dist,MAX);
        for(int i = 0 ; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            map[s].add(new P1504(e,val));
            map[e].add(new P1504(s,val));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        //1번에서의 최단 거리 구하기
        PriorityQueue<P1504> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.add(new P1504(1, 0));
        visit = new boolean[n+1];
        while (!pq.isEmpty()){
            P1504 r = pq.poll();
            if(visit[r.y]) continue;
            visit[r.y] = true;
            for(P1504 next : map[r.y]){
                if(dist[next.y] > dist[r.y] + next.val){
                    dist[next.y] = dist[r.y] + next.val;
                    pq.add(new P1504(next.y , dist[next.y]));
                }
            }
        }

        v1dist[v1] = 0;
        pq.add(new P1504(v1, 0));
        visit = new boolean[n+1];
        while (!pq.isEmpty()){
            P1504 r = pq.poll();
            if(visit[r.y]) continue;
            visit[r.y] = true;
            for(P1504 next : map[r.y]){
                if(v1dist[next.y] > v1dist[r.y] + next.val){
                    v1dist[next.y] = v1dist[r.y] + next.val;
                    pq.add(new P1504(next.y , v1dist[next.y]));
                }
            }
        }


        v2dist[v2] = 0;
        pq.add(new P1504(v2, 0));
        visit = new boolean[n+1];
        while (!pq.isEmpty()){
            P1504 r = pq.poll();
            if(visit[r.y]) continue;
            visit[r.y] = true;
            for(P1504 next : map[r.y]){
                if(v2dist[next.y] > v2dist[r.y] + next.val){
                    v2dist[next.y] = v2dist[r.y] + next.val;
                    pq.add(new P1504(next.y , v2dist[next.y]));
                }
            }
        }

        int ex1 = dist[v1] + v1dist[v2] + v2dist[n];
        int ex2 = dist[v2] + v2dist[v1] + v1dist[n];
        if(ex1>=MAX && ex2 >=MAX){
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(ex1,ex2));
    }
}
