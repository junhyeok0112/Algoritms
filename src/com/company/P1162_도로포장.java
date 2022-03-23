package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P1162 implements Comparable<P1162>{
    int y ;
    int val ;
    P1162(int y, int val){
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(P1162 o) {
        return this.val - o.val;
    }
}

public class P1162_도로포장 {

    static int n,m,k;
    static ArrayList<P1162>[] map;
    static int[] par;
    static boolean[] visit ;
    static int[] dist ;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new ArrayList[n+1];
        par = new int[n+1];
        visit = new boolean[n+1];
        dist = new int[n+1];
        Arrays.fill(dist , Integer.MAX_VALUE);
        for(int i = 0 ; i<=n ; i++) map[i] = new ArrayList();
        for(int i = 0 ; i<m ;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            map[start].add(new P1162(end , val));
            map[end].add(new P1162(start , val));
        }
        PriorityQueue<P1162> pq = new PriorityQueue<>();
        pq.add(new P1162(1,0));
        dist[1] = 0;
        while (!pq.isEmpty()){
            P1162 cur = pq.poll();
            if(visit[cur.y]) continue;      //visit가 여기서 탐색 시작할게 ->의미
            visit[cur.y] = true;
            for(P1162 next : map[cur.y]){
                if(dist[next.y] > dist[cur.y] + next.val){
                    dist[next.y] = dist[cur.y] + next.val;
                    pq.add(new P1162(next.y , dist[next.y]));
                    par[next.y] = cur.y;        //최단거리의 부모 갱신
                }
            }
        }

        System.out.println(dist[n]);

    }
}
