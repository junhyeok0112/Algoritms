package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 실습11_게리멘더링_최준혁 {

    static int n;
    static int[] person;
    static boolean[] select;                    //선택되었는지
    static HashSet<Integer>[] adj ;
    static ArrayList<Integer> on = new ArrayList<>();
    static ArrayList<Integer> off = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        person = new int[n+1];
        select = new boolean[n+1];
        adj = new HashSet[n+1];
        for(int i = 0 ;i<=n ;i++) adj[i] = new HashSet();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ;i<=n ;i++){
            person[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1 ; i<=n;i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j<cnt;j++){
                int e = Integer.parseInt(st.nextToken());
                adj[i].add(e);
                adj[e].add(i);
            }
        }

        rec(0,1);
        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(ans);
        }

    }
    //선택했을때 , 안했을 대
    static void rec(int cnt , int idx){
        if(cnt >= n){
            if(check()){
                int onTotal = 0;
                int offTotal = 0;
                for(int i =1 ;i<=n ;i++){
                    if(select[i]) onTotal+=person[i];
                    else offTotal += person[i];
                }
                int dif = Math.abs(onTotal - offTotal);
                ans = Math.min(dif,ans);
            }

            return;
        }

        select[idx] = true;           //선택
        rec(cnt+1, idx+1);
        select[idx] = false;          //선택 안함
        rec(cnt+1,idx+1);

    }

    private static boolean check() {
        //visit 로 방문 체크 -> 이 때 A구역을 모두 먼저 연결하고 B 구역을 연결합니다. 만약 한곳이라도 연결되어 있지 않다면 전부 연결되어 있지 않은 것이므로 false 리턴
        boolean[] visit = new boolean[n+1];
        //적어도 하나는 선택되어 있어야함
        int areaA = -1;
        int areaB = -1;     //2 구역의 시작점 찾기
        //A구역 시작점 찾기  -> A 는 true인 곳들
        for(int i = 1; i<=n ;i++){
            if(select[i]){
                areaA = i;
                break;
            }
        }

        for(int i =1 ;i<=n ;i++){
            if(!select[i]){
                areaB = i;
                break;
            }
        }

        //구역에 도시가 하나도 없을 경우
        if(areaA == -1 || areaB == -1) return false;

        //연결되어 있어야함
        //A구역 연결시키기
        Queue<Integer> q = new LinkedList<>();
        visit[areaA] = true;
        q.offer(areaA);
        while (!q.isEmpty()){
            int r = q.poll();
            for(int next : adj[r]){
                //이미 방문했거나 false로 분류되어 B구역일경우
                if(visit[next] || !select[next]) continue;
                visit[next] = true;
                q.offer(next);
            }
        }

        //B구역 연결하기
        visit[areaB] = true;
        q.offer(areaB);
        while (!q.isEmpty()){
            int r = q.poll();
            for(int next : adj[r]){
                //이미 방문했거나 true로 분류되어 A구역인 경우 continue
                if(visit[next] || select[next]) continue;
                visit[next] = true;
                q.offer(next);
            }
        }

        //방문 안한게 1개라도 있으면 false 리턴
        for(int i = 1; i<=n ;i++){
            if(!visit[i]) return false;
        }
        return true;
    }


}
