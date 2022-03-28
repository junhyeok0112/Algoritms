package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class P10217 implements Comparable<P10217>{
    int y;
    int cost;      //비용
    int time;      //시간
    P10217(int y, int cost , int time){
        this.y = y;
        this.cost = cost;
        this.time = time;
    }

    @Override
    public int compareTo(P10217 o) {    //시간이 적은 순으로 정렬하되 같으면 비용이 적은 것 부터
        if(this.time == o.time){
            return this.cost - o.cost;
        } else return this.time - o.time;

    }
}


public class P10217_KCMTravel {

    static int t,n,m,k;
    static ArrayList<P10217>[] map;
    static int[][] dist;                //dist[i][j] 가 나타내는 것은 1번에서 i까지 도착하는데 j원의 비용으로 걸리는 최소 시간을 나타냅니다, 즉 정답은 dist[n][j] 중 최솟값이 정답입니다.s
    static boolean[][] visit;
    static PriorityQueue<P10217> pq ;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(t-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            dist = new int[n+1][m+1];               //최대 M원까지므로 m개의 열 생성
            map = new ArrayList[n+1];
            visit = new boolean[n+1][m+1];
            for(int i = 0 ; i<= n; i++){
                map[i] = new ArrayList<>();
                Arrays.fill(dist[i] , Integer.MAX_VALUE);
            }
            pq = new PriorityQueue<>();


            for(int i = 0 ; i< k ;i++){
                st = new StringTokenizer(br.readLine());
                int u,v,c,d;
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                map[u].add(new P10217(v,c,d));
            }

            for(int i = 0 ; i <=m ;i++){
                dist[1][i] = 0;     //시작값 초기화
            }
            pq.add(new P10217(1,0,0));  //초기값 pq에 넣어줌
            while (!pq.isEmpty()){
                P10217 r = pq.poll();
                int ry = r.y;
                int rCost = r.cost;
                int rTime = r.time;
                if(visit[ry][rCost]) continue;
                visit[ry][rCost] = true;
                for(P10217 next : map[ry]){     //ry와 연결되어 있는 것들을 전부 꺼냄
                    int tempCost = rCost + next.cost;           //r까지 왔을떄의 비용과 r -> next 까지 갈때의 비용을 합하면 1 -> next 까지의 비용이나옴 , 이 비용이 m이하여야함
                    if(tempCost <=m && dist[next.y][tempCost] > dist[ry][rCost] + next.time){            //시작지점에서 next.y 까지 tempCost로 갈때 걸리는 시간이 ry를 rCost의 비용으로 거쳐서 가는 것보다 크면 -> 일반 다익스트라
                        //즉 거쳐가는게 시간이 더 적게걸리면 -> 주의할점은 dist[ry]의 비용 ->ry 까지는 rCost로 갔기때문
                        dist[next.y][tempCost] = dist[ry][rCost] +  next.time;
                        pq.add(new P10217(next.y, tempCost, dist[next.y][tempCost]));
                    }
                }
            }

            int ans = dist[n][0];       //dist[n]을 전부 비교해야하므로 시작지점을 0으로 잡음
            for(int i =1 ; i<=m ;i++) ans = Math.min(ans,dist[n][i]);
            if(ans == Integer.MAX_VALUE){           //못 가는 곳이면
                sb.append("Poor KCM").append("\n");
            }else{
                sb.append(ans).append("\n");
            }

        }
        System.out.println(sb.toString());
    }
}
