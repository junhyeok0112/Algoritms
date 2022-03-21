package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5567_결혼식 {

    static int N ,M;
    static boolean[] visit;
    static ArrayList<Integer>[] map;
    static int cnt = 0;
    static int level = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new ArrayList[N+1];
        visit = new boolean[N+1];
        for(int i =1 ; i<=N ;i++){
            map[i] = new ArrayList<>();
        }
        for(int i = 0 ; i<M ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            map[num1].add(num2);
            map[num2].add(num1);
        }

        bfs();
        cnt--;  //1인 본인도 포함했으므로 1빼고 출력
        System.out.println(cnt);

    }

    static void bfs(){
        Queue<Integer> q= new LinkedList<>();
        visit[1] = true;
        q.add(1);
        while (!q.isEmpty()) {
            if(level > 2 ){
                break;
            }
            int qsize = q.size();
            for(int i = 0 ; i<qsize;i++) {
                int r = q.poll();
                cnt++;
                for(int t : map[r]) {
                    if (!visit[t]) {
                        visit[t] = true;
                        q.add(t);
                    }
                }
            }
            level++;
        }

    }
}
