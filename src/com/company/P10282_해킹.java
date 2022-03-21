package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Com implements Comparable<Com>{
    int x;
    int dis;
    Com(int x, int dis){
        this.x = x;
        this.dis = dis;
    }

    @Override
    public int compareTo(Com o) {
        if(this.dis < o.dis){
            return -1 ;
        } else{
            return 1;
        }
    }
}


public class P10282_해킹 {

    static int n,d,c;
    static ArrayList<Com>[] adj ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T>0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            int[] dist = new int[n+1];
            int[] visit = new int[n+1];
            adj = new ArrayList[n+1];
            for(int i =1 ; i<=n ;i++) adj[i] = new ArrayList<>();
            Arrays.fill(dist,Integer.MAX_VALUE);
            int time = 0;
            //시작지점부터 마지막 지점까지 가는데 걸리는 시간 출력하면됨
            dist[c] = 0;
            for(int i = 0 ; i<d; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                adj[b].add(new Com(a,s));
            }

            PriorityQueue<Com> pq = new PriorityQueue<>();
            pq.add(new Com(c, 0));  //pq에 앞에꺼가 시작지점부터 도착 위치
            while(!pq.isEmpty()){
                Com r = pq.poll();
                if(visit[r.x] != 0)     //방문한 점이면 continue
                    continue;
                visit[r.x] = 1;
                for(Com next : adj[r.x]){
                    if(dist[next.x] > dist[r.x] + next.dis){
                        dist[next.x] = dist[r.x] + next.dis;
                        pq.add(new Com(next.x, dist[next.x]));
                    }
                }
            }
            //즉 dist 배열에서 최대값 찾으면됨
            int max = -1;
            int cnt =0 ;
            for(int i = 1; i<=n;i++){
                if(dist[i] != Integer.MAX_VALUE) {
                    cnt++;
                    if(max < dist[i]){
                        max = dist[i];
                    }
                }

            }
            System.out.println(cnt+ " " + max);

            T--;
        }
    }
}
