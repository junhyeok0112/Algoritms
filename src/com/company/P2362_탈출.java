package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//다시 풀어보기

public class P2362_탈출 {

    static int N,K;
    static char[][] map;
    static boolean[][] visit;
    static Queue<Integer> start = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N+1][N+1];
        visit = new boolean[N+1][N+1];
        for(int i =1 ; i<=N;i++){
            String s = br.readLine();
            for(int j = 1 ; j<=N;j++){
                map[i][j] = s.charAt(j-1);
                if(map[i][j] == 'X'){
                    start.add(i);
                    start.add(j);
                }
            }
        }



    }
}
