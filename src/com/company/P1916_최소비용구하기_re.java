package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P1916 implements Comparable<P1916>{
    int end ;
    int val ;
    P1916(int end , int val){
        this.end = end;
        this.val = val;
    }

    @Override
    public int compareTo(P1916 o) {
        return this.val - o.val;
    }
}

public class P1916_최소비용구하기_re {

    static int n,m ,start ,end;
    static ArrayList<P1916>[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n =Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new ArrayList[n+1];
        for(int i = 0 ; i<= n; i++) arr[i] = new ArrayList<>();
        StringTokenizer st ;
        for(int i = 0 ; i <m ;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int val =Integer.parseInt(st.nextToken());
            arr[s].add(new P1916(e, val));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int[] dist = new int[n+1];
        Arrays.fill(dist , Integer.MAX_VALUE);      //dist 배열 초기화
        dist[start] = 0;
        PriorityQueue<P1916> pq = new PriorityQueue<>();    //pq 선언
        pq.add(new P1916(start , 0));
        boolean[] visit = new boolean[n+1];             //boolean 배열선언
        while (!pq.isEmpty()){
            P1916 r = pq.poll();
            if(visit[r.end]) continue;      //방문했으면 스킵
            visit[r.end] = true;
            for(P1916 next : arr[r.end]){
                if(dist[next.end] > dist[r.end] + next.val ){   //시작지점에서 next로 가는것보다 r을 거쳐서 r->next로 가는게 더 짧은지 확인
                    dist[next.end] = dist[r.end] +next.val;
                    pq.add(new P1916(next.end, dist[next.end]));
                }
            }
        }

        System.out.println(dist[end]);
    }
}
