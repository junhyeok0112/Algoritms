package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2644_촌수계산_re {

    static int n, a, b, m;
    static ArrayList<Integer>[] arr;
    static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        arr = new ArrayList[n + 1];
        visit = new int[n + 1];
        for (int i = 0; i <= n; i++) arr[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x].add(y);
            arr[y].add(x);
        }
        bfs(a);
        if(visit[b] == 0){
            System.out.println(-1);
        } else{
            System.out.println(visit[b] - 1);
        }

    }

    static void bfs(int x){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visit[x] = 1;
        while (!q.isEmpty()){
            int r = q.poll();
            for(int next : arr[r]){
                if(visit[next] == 0){
                    visit[next] = visit[r] + 1;
                    q.add(next);
                    if(next == b) return;
                }
            }
        }

    }
}
