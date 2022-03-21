package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class A28 implements Comparable<A28>{
    int y;
    int weight;
    A28(int y , int weight){
        this.y = y;
        this.weight = weight;
    }

    @Override
    public int compareTo(A28 o) {
        return this.weight - o.weight;
    }
}


public class A28_최단경로_최준혁 {

    static int v, e ,start;
    static ArrayList<A28>[] arr ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        arr = new ArrayList[v+1];
        for(int i = 0; i<=v; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i = 0 ; i<e ;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            arr[start].add(new A28(end ,weight));
        }

        int[] dis = new int[v+1];
        boolean[] visit = new boolean[v+1];
        Arrays.fill(dis , Integer.MAX_VALUE);
        dis[start] = 0;
        PriorityQueue<A28> pq = new PriorityQueue<>();
        pq.add(new A28(start ,0));
        while (!pq.isEmpty()){
            A28 cur = pq.poll();
            if(visit[cur.y]) continue;
            visit[cur.y] = true;
            for(A28 next : arr[cur.y]){
                if(dis[next.y] > dis[cur.y] +next.weight ){
                    dis[next.y] = dis[cur.y] + next.weight;
                    pq.add(new A28(next.y ,dis[next.y] ));
                }
            }
        }

        for(int i =1 ; i<=v; i++){
            if(i == start) {
                System.out.println(0);
                continue;
            } else if(dis[i] == Integer.MAX_VALUE){
                System.out.println("INF");
            } else{
                System.out.println(dis[i]);
            }
        }



    }
}
