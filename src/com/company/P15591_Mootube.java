package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class P15591{
    int y;
    int dis;
    P15591(int y , int dis){
        this.y = y;
        this.dis = dis;
    }
}

public class P15591_Mootube {

//    static int[][] graph;
    static int n,q;
    static StringBuilder sb = new StringBuilder();
    static int[] visit;     //DFS 시 방문했는지 체크 하는 함수
    static boolean[] visit2;
    static ArrayList<P15591>[] graph;
    static int cnt ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        for(int i = 0 ; i<=n ;i++) graph[i] = new ArrayList<>();

        for(int i = 0 ; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            graph[start].add(new P15591(end , dis));
            graph[end].add(new P15591(start,dis));
        }


        for(int i = 0 ; i<q ;i++){
            st= new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            visit = new int[n+1];
            Arrays.fill(visit, Integer.MAX_VALUE);
            sb.append(bfs(v,k)).append("\n");
        }
        System.out.println(sb.toString());
    }
    //K보다 큰애들을 찾는 것이므로 , K 보다 작은 경로가 있는 루트는 더 이상 탐색하지 않아도된다.
    static public int bfs(int start ,int k){
        Queue<P15591> q = new LinkedList<>();
        q.offer(new P15591(start, 0));
        visit[start] = 0;
        while (!q.isEmpty()){
            P15591 r = q.poll();
            for(P15591 next : graph[r.y]){
                if(visit[next.y] != Integer.MAX_VALUE || next.dis < k) continue;        //방문한 곳이면 넘어가
                q.offer(next);
                if(visit[r.y] == 0){ //이전 값이 없으면 무조건 dis 거리가 셋팅
                    visit[next.y] = next.dis;
                } else{             //이전값이 있으면 이전 값과 현재 값 비교해서 최소 셋팅
                    visit[next.y] = Math.min(visit[r.y] , next.dis);
                }
            }
        }
        int cnt = 0;
        for(int i = 1 ; i<=n ;i++){
            if(visit[i] == Integer.MAX_VALUE) continue;
            if(visit[i] >= k) cnt++;
        }
        return cnt;
    }

}
