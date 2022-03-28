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
    long val ;
    int cnt ;               //현재까지 포장한 도로의 개수
    P1162(int y, long val , int cnt){
        this.y = y;
        this.val = val;
        this.cnt = cnt;         //즉 다익스트라로 체크할 때 해당 도로를 포장한것과 안 한것 구분해서 넣어야함
    }

    @Override
    public int compareTo(P1162 o) {
        if(this.val == o.val){
            return this.cnt - o.cnt;
        } else if(this.val > o.val){
            return 1;
        }else return -1;
    }
}

public class P1162_도로포장 {

    static int n,m,k;
    static ArrayList<P1162>[] map;
    static boolean[][] visit ;
    static long[][] dist ;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new ArrayList[n+1];
        visit = new boolean[n+1][k+1];
        dist = new long[n+1][k+1];               //dist[i][k]는 i번째 목적지를 방문하는데 포장한도로가 k개 일때의 최소거리를 나타냅니다 -> 즉 dist[n][k]값 들 중 최소값이 정답입니다
        for(int i = 0 ; i<=n ;i++) Arrays.fill(dist[i],Long.MAX_VALUE);
        for(int i = 0 ; i<=n ; i++) map[i] = new ArrayList();
        for(int i = 0 ; i<m ;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            map[start].add(new P1162(end , val,0));
            map[end].add(new P1162(start , val,0));
        }

        PriorityQueue<P1162> pq = new PriorityQueue<>();
        pq.add(new P1162(1,0,0));
        dist[1][0] = 0;
        while (!pq.isEmpty()){
            P1162 cur = pq.poll();
            if(visit[cur.y][cur.cnt]) continue;      //visit가 여기서 탐색 시작할게 ->의미
            visit[cur.y][cur.cnt] = true;
            for(P1162 next : map[cur.y]){
                int tempCnt = cur.cnt + 1;          //다음 도로도 포장할껀지 체크하는 함수

                //포장 횟수가 남았으면 포장하고 가중치는 0으로 하고 저장한다.
                if(tempCnt<=k && dist[next.y][tempCnt] > dist[cur.y][cur.cnt] ){
                    dist[next.y][tempCnt] = dist[cur.y][cur.cnt] ;
                    pq.add(new P1162(next.y , dist[next.y][tempCnt] , tempCnt));
                }

                //포장 횟수가 없으면 or 포장 안했을 경우 -> 다음 지점을 현재 포장 횟수로 유지한다
                if(dist[next.y][cur.cnt] > dist[cur.y][cur.cnt] + next.val){
                    dist[next.y][cur.cnt] = dist[cur.y][cur.cnt] + next.val;
                    pq.add(new P1162(next.y,  dist[next.y][cur.cnt] , cur.cnt ));
                }
            }
        }

        long ans = dist[n][0];
        for(int i = 1 ; i<=k ;i++) ans = Math.min(ans , dist[n][i]);

        System.out.println(ans);

    }
}
