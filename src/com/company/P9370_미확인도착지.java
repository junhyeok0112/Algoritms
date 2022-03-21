package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P9370 implements Comparable<P9370>{
    int y;
    int dis;
    //y까지의 거리가 dis이다
    P9370(int y , int dis){
        this.y = y;
        this.dis = dis;
    }

    @Override
    public int compareTo(P9370 o) {
        return this.dis - o.dis;
    }
}

//다익스트라 이용
public class P9370_미확인도착지 {

    static int T;
    static int n,m,t;
    static int s,g,h,a,b,d;
    static ArrayList<ArrayList<P9370>> graph;
    static PriorityQueue<Integer> destination;
    static int[] dist ;     //S에서 모든 점까지의 최단거리
    static int[] gdist;     //g에서 모든 점까지의 최단거리
    static int[] hdist;     //h에서 모든 점까지의 최단거리


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            for(int i = 0 ; i<=n ; i++){
                ArrayList<P9370> temp = new ArrayList<>();
                graph.add(temp);
            }
            //dist 배열들 선언 후 초기화
            dist = new int[n+1];
            gdist = new int[n+1];
            hdist = new int[n+1];
            for(int i = 0 ; i<=n ; i++){
                dist[i] = Integer.MAX_VALUE;
                gdist[i] = Integer.MAX_VALUE;
                hdist[i] = Integer.MAX_VALUE;
            }
            destination = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            int ghDis = 0;
            for(int i = 0 ; i<m ;i++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());
                //gh의 거리 저장
                if((a == g && b == h) ||(a == h && b == g) ){
                    ghDis = d;
                }
                //그래프 만들기
                graph.get(a).add(new P9370(b,d));
                graph.get(b).add(new P9370(a,d));
            }
            for(int i =0 ; i<t; i++){//목적지들 저장
                int temp = Integer.parseInt(br.readLine());
                destination.add(temp);
            }
            //s에서 g 까지 갈때 최단경로 , h까지 갔을때 각각 최단경로 구한후 g,h에서 목적지까지의 최단경로 중 제일 작
            //단 이떄 s에서 목적지까지 최단경로가 g,h를 통과하는 최단거리보다 짧으면 목적지에 최단거리로 가는게 아니므로 정답이 될수 업승ㅁ

            //s에서 모든 경로까지의 최단거리 구하기
            PriorityQueue<P9370> pq = new PriorityQueue<>();
            boolean[] visit = new boolean[n+1];
            dist[s] = 0;
            pq.add(new P9370(s,0));
            while (!pq.isEmpty()){
                P9370 r = pq.poll();
                if(visit[r.y]) continue;
                visit[r.y] = true;
                for(P9370 Edge : graph.get(r.y)){   //r과 연결된거 뺌
                    if(dist[Edge.y] > dist[r.y] + Edge.dis){
                        dist[Edge.y] = dist[r.y] + Edge.dis;
                        pq.add(new P9370(Edge.y , dist[Edge.y]));
                    }
                }
            }

            PriorityQueue<P9370> pqG = new PriorityQueue<>();
            gdist[g] = 0;   //g에서의 최단거리
            boolean[] qvisit = new boolean[n+1];
            pqG.add(new P9370(g,0));
            while (!pqG.isEmpty()){
                P9370 r = pqG.poll();
                if(qvisit[r.y]) continue;
                qvisit[r.y] = true;
                for(P9370 Edge : graph.get(r.y)){
                    if(gdist[Edge.y] > gdist[r.y] + Edge.dis){
                        gdist[Edge.y] = gdist[r.y] + Edge.dis;
                        pqG.add(new P9370(Edge.y , gdist[Edge.y]));
                    }
                }
            }

            PriorityQueue<P9370> pqH = new PriorityQueue<>();
            hdist[h] = 0;
            boolean[] hvisit = new boolean[n+1];
            pqH.add(new P9370(h,0));
            while (!pqH.isEmpty()){
                P9370 r = pqH.poll();
                if(hvisit[r.y]) continue;
                hvisit[r.y] = true;
                for(P9370 Edge : graph.get(r.y)){
                    if(hdist[Edge.y] > hdist[r.y] + Edge.dis){
                        hdist[Edge.y] = hdist[r.y] + Edge.dis;
                        pqH.add(new P9370(Edge.y , hdist[Edge.y]));
                    }
                }
            }

            //gh사이의 거리보다 최단거리로 가는 다른경우가 존재할 수 도 있으므로 2지점의 거리를 미리 ghDis라는 변수에 저장해둔다
            ArrayList<Integer> answer = new ArrayList<>();
            for(int ans : destination){
                long res1 = dist[ans];
                long res2 = dist[g] + ghDis + hdist[ans];
                long res3 = dist[h] + ghDis + gdist[ans];
                if(Math.min(res2,res3) == res1){
                    answer.add(ans);
                }
            }
            Collections.sort(answer);
            for(int ans : answer){
                System.out.print(ans + " ");
            }

        }
    }
}
