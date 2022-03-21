package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class P10217 implements Comparable<P10217>{
    int y;
    int c;
    int d;
    P10217(int y, int c , int d){
        this.y = y;
        this.c = c;
        this.d = d;
    }

    @Override
    public int compareTo(P10217 o) {
        return this.d - o.d;            //소요 시간 순으로 정렬
    }
}


public class P10217_KCMTravel {

    static int t,n,m,k;
    static ArrayList<P10217>[] adj;
    static int[] dist;
    static int[] cost;
    static PriorityQueue<P10217> pq ;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while(t-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            dist = new int[n+1];
            cost = new int[n+1];
            adj = new ArrayList[n+1];
            for(int i = 0 ; i<= n; i++){
                adj[i] = new ArrayList<>();
                dist[i] = Integer.MAX_VALUE;           //최대값 갱신
            }
            pq = new PriorityQueue<>();


            for(int i = 0 ; i< k ;i++){
                st = new StringTokenizer(br.readLine());
                int u,v,c,d;
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                adj[u].add(new P10217(v,c,d));
            }

            dist[1] = 0;        //시작값
            pq.add(new P10217(1,0,0));  //초기값 pq에 넣어줌
            while (!pq.isEmpty()){
                P10217 r = pq.poll();

            }



        }
    }
}
