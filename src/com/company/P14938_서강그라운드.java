package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class P14938 implements Comparable<P14938>{
    int y;
    int dis;
    P14938(int y, int dis){
        this.y = y;
        this.dis = dis;
    }

    @Override
    public int compareTo(P14938 o) {
        return this.dis - o.dis;
    }
}

//최단거리 전부 구해놓고 , 그 거리 내에 있는 아이템의 갯수 구하기
public class P14938_서강그라운드 {

    static int n,m,r;
    static int[] items ;
    static int[][] dist;
    static boolean[] visit;
    static ArrayList<P14938>[] adj;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n+1];
        dist = new int[n+1][n+1];
        adj = new ArrayList[n+1];
        for(int i = 0 ; i<=n ; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0 ; i<= n ;i++){
            for(int j = 0 ; j<=n ;j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        st = new StringTokenizer(br.readLine());        //아이템 갯수
        for(int i = 1 ; i<=n ;i++){
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i<r ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            adj[a].add(new P14938(b,l));
            adj[b].add(new P14938(a,l));
        }


        //2차원 배열을 이용해서 모든 노드에서의 다익스트라를 구함
        for(int i = 1 ; i<= n ;i++){
            dist[i][i] = 0;                 //본인이 가는 곳
            PriorityQueue<P14938> pq = new PriorityQueue<>();
            pq.add(new P14938(i,0));        //자기 자신 까지 가는길
            visit = new boolean[n+1];       //매번 반복때마다 초기화 해줘야함
            while (!pq.isEmpty()){
                P14938 r = pq.poll();
                if(visit[r.y]) continue;        //방문했으면 continue
                visit[r.y] = true;              //방문 갱신
                for(P14938 cur : adj[r.y]){
                    if(dist[i][cur.y] >= dist[i][r.y] + cur.dis ){  //i에서 cur.y 로 바로가는 것보다 r을 거쳐가는게 더 크면
                        dist[i][cur.y] = dist[i][r.y] + cur.dis;
                        pq.add(new P14938(cur.y , dist[i][cur.y]));
                    }
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int i =1 ; i<=n; i++){
            int sum = 0;
            for(int j =1 ; j<=n ;j++){
                if(dist[i][j] <= m){
                    sum += items[j];
                }
            }
            ans = Math.max(ans , sum);
        }

        System.out.println(ans);


    }
}
