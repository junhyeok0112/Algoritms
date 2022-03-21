package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1697_숨바꼭질 {

    static int N,K;
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[100001];

        bfs(N);
        System.out.println(map[K] -1 );
        //시작이 1부터였으므로 1빼서 시작

    }

    static void bfs(int N){
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        map[N] = 1;
        while (!q.isEmpty()){
            int r = q.poll();
            if(r-1 >=0 && r-1<=100000){
                if(map[r-1] == 0){
                    map[r-1] = map[r] +1;
                    q.add(r-1);
                }
            }
            if(r+1 >=0 && r+1<=100000){
                if(map[r+1] == 0){
                    map[r+1] = map[r] +1;
                    q.add(r+1);
                }
            }
            if(2*r >=0 && 2*r<=100000){
                if(map[2*r] == 0){
                    map[2*r] = map[r] +1;
                    q.add(2*r);
                }
            }
        }
    }
}
