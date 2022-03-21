package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class D03 implements Comparable<D03>{
    int y;
    int dis;
    D03(int y , int dis){
        this.y = y;
        this.dis = dis;
    }

    @Override
    public int compareTo(D03 o) {
        return this.dis - o.dis;
    }
}

public class D03_파티_최준혁 {

    static int n,m,x;       //모든 학생들이 X로 갔다가 X에서 오는길을 구해야함
    //다익스트라로 2개 구해야할듯?
    static ArrayList<D03>[] arr;
    static boolean[] visit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];
        for(int i =0 ; i<=n;i++){
            arr[i] = new ArrayList<>();
        }
        for(int i = 0 ; i<m ;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            arr[start].add(new D03(end , dis));
        }

        int[][] dis = new int[n+1][n+1];
        for(int i =0 ;i<=n;i++){
            Arrays.fill(dis[i], Integer.MAX_VALUE);
            dis[i][i] = 0;
        }

        PriorityQueue<D03> pq = new PriorityQueue<>();
        for(int t = 1 ; t<=n ;t++){ //n번의 다익스트라
            visit = new boolean[n+1];
            pq.add(new D03(t,0));       //시작점 pq에 넣기
            while (!pq.isEmpty()){
                D03 cur = pq.poll();
                if(visit[cur.y]) continue;
                visit[cur.y] = true;
                for(D03 next : arr[cur.y]){
                    if(dis[t][next.y] >= dis[t][cur.y] + next.dis){
                        dis[t][next.y] = dis[t][cur.y] + next.dis;
                        pq.add(new D03(next.y, dis[t][next.y]));
                    }
                }
            }
            pq.clear();
        }
        int max = -1;
        for(int i = 1 ; i<=n ;i++){
            max = Math.max(max, dis[i][x] + dis[x][i]);
        }
        System.out.println(max);
    }
}
