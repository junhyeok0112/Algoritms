package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo
public class 과제05_키순서_최준혁 {

    static int n,m;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] arr ; //정방향 리스트
    static ArrayList<Integer>[] rArr ; //역방향 리스트
    static int cnt ;
    static int ans = 0;
    //나와 어떻게든 연결되어 있는 사람들의 수가 n-1 이여야 나의 키를 알 수 있음
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];
        rArr = new ArrayList[n+1];
        for(int i = 0 ; i<=n ; i++){
            arr[i] = new ArrayList<>();
            rArr[i] = new ArrayList<>();
        }
        for(int i = 0 ; i<m ;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[start].add(end);        //정방향 저장
            rArr[end].add(start);       //역방향 저장
        }

        //bfs나 dfs 로 탐색하면됨 -> 모든 점에 대해서 탐색 그리고 이때 , cnt == N-1 이면 키 알 수 있음
        //역방향 ,정방향 둘다
        for(int i =1 ; i<=n ;i++){
            boolean[] visit = new boolean[n+1];
            cnt = 0;
            bfs(i, visit,arr);
            bfs(i,visit,rArr);
            if(cnt == n-1) ans++;
        }
        System.out.println(ans);
    }
    static void bfs(int start ,boolean[] visit, ArrayList<Integer>[] arr){
        Queue<Integer> q = new LinkedList<>();
        visit[start] = true;
        q.add(start);
        while (!q.isEmpty()){
            int r= q.poll();
            for(int next : arr[r]){
                if(!visit[next]){
                    visit[next] = true;
                    q.add(next);
                    cnt++;
                }
            }
        }

    }
}
