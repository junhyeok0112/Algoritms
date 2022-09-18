package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class P1197 implements Comparable<P1197> {
    int s,e,dis;
    P1197(int s, int e, int dis){
        this.s = s;
        this.e = e;
        this.dis = dis;
    }

    @Override
    public int compareTo(P1197 o) {
        return this.dis - o.dis;
    }
}

public class P1197_최소스패닝트리 {

    static int v ,e;
    static PriorityQueue<P1197> pq = new PriorityQueue<>();
    static int[] p;     //부모 관계

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i<e ;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new P1197(a,b,c));
        }

        long ans = 0;
        int n = 0;      //간선 갯수
        p = new int[v+1];
        Arrays.fill(p ,-1);
        while (!pq.isEmpty()){
            P1197 r = pq.poll();
            int ra = r.s;
            int rb = r.e;
            int rc = r.dis;
            if(merge(ra,rb)){
                ans += rc;
                n++;
                if(n == v- 1) break;
            }
        }
        System.out.println(ans);
    }

    static int find(int x){
        if(p[x] == -1 ) return x;
        return p[x] = find(p[x]);
    }

    static boolean merge(int x , int y){
        x = find(x);
        y = find(y);
        if(x == y) return false;
        p[x] = y;
        return true;
    }
}
