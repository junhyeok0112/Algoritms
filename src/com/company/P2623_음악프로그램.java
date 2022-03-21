package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2623_음악프로그램 {

    static int N,M;
    static int[] indeg;
    static ArrayList<Integer>[] adj;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indeg = new int[N+1];
        adj = new ArrayList[N+1];
        for(int i = 1 ; i<=N;i++) adj[i] = new ArrayList<>();
        for(int i = 0 ; i<M ;i++){                          //그래프 만들기
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int[] temp = new int[t+1];
            for(int j = 1 ; j<=t;j++){
                temp[j] = Integer.parseInt(st.nextToken());
            }
            for(int j = 1 ; j<t ; j++){
                adj[temp[j]].add(temp[j+1]);                //그래프 만드는 과정 temp[j] 뒤에 temp[j+1]와야함
                indeg[temp[j+1]]++;                         //temp[j+1] indegree증가
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i<=N; i++){
            if(indeg[i] == 0){
                q.add(i);
            }
        }

        while (!q.isEmpty()){
            int X = q.poll();
            sb.append(X).append("\n");
            for(int Y :adj[X]){
                indeg[Y]--;
                if(indeg[Y] == 0){  //먼저 올거 없으면
                    q.add(Y);
                }
            }
        }

        for(int i=1 ;i<=N; i++){
            if(indeg[i] != 0){  //0이 아니면 위상정렬불가
                System.out.println(0);
                return;
            }
        }
        System.out.println(sb);

    }
}
