package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int dis;
    int x;
    int y;
    Edge(int dis ,int x, int y){
        this.dis = dis;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.dis > o.dis){
            return 1;
        } else{
            return -1;
        }
    }
}

public class P16398_행성연결 {

    static int N;
    static int[][] map;
    static int[] p;
    static long ans = 0;     //최대 1000억까지 가능
    static int cnt = 0;     //간선의 개수 N-1 개 되면 끝

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        p = new int[N+1];
        Arrays.fill(p,-1);
        map = new int[N+1][N+1];
        for(int i =1 ; i<=N ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j<=N ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //MST 이용하는 것으로 모든 정점을 잇는데 최소비용 계산한다.
        //간선들을 비용이 적은 순으로 연결해서 연결되면 union해서 집합 통일시켜준다

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 1;i<=N; i++){
            for(int j =i+1; j<=N ;j++){
                pq.add(new Edge(map[i][j] , i, j));
            }
        }
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            int dis = cur.dis;
            int x = cur.x;
            int y = cur.y;
            if(find(x) != find(y)) {             //합친 간선 집어넣기
                merge(x, y);
                ans += dis;
                cnt++;
                if (cnt == N - 1) break;
            }
        }
        System.out.println(ans);

    }

    static int find(int x){
        if(p[x] <0)
            return x;
        return p[x] = find(p[x]);
    }

    static void merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x ==y ) return;
        p[y] = x;
    }
}
