package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P22352_항체인식 {
    static int N, M;
    static int[][] before;
    static int[][] after;
    static boolean[][] visit;
    static int[] dx = {-1 , 1 , 0 , 0};
    static int[] dy = {0,0, 1 ,-1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        before = new int[N+1][M+1];
        after = new int[N+1][M+1];
        visit =new boolean[N+1][M+1];
        for(int i = 1; i<=N ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=M ; j++){
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i<=N ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=M ; j++){
                after[i][j] = Integer.parseInt(st.nextToken());
                if(before[i][j] != after[i][j]){
                    q.add(i);
                    q.add(j);
                    q.add(after[i][j]);
                    q.add(before[i][j]);
                }
            }
        }

        if(q.isEmpty()){    //q가 비어있으면 이미 똑같으므로 어떻게 해도 백신일 수 잇음
            System.out.println("YES");
            return;
        }
        int startX = q.poll();
        int startY = q.poll();
        int changeNum = q.poll();
        int beforeNum = q.poll();

        bfs(startX , startY , changeNum , beforeNum);

        if(comp()){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

//        for(int i = 1; i<=N; i++){
//            for(int j = 1; j<=M ;j++){
//                System.out.print(before[i][j]+ " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for(int i = 1; i<=N; i++){
//            for(int j = 1; j<=M ;j++){
//                System.out.print(after[i][j]+ " ");
//            }
//            System.out.println();
//        }


    }

    static void bfs(int x, int y , int change , int beforeNum){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visit[x][y] = true;
        before[x][y] = change;
        while(!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i =0 ; i<4; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx>=1 && nx<=N && ny>=1 && ny<=M){
                    if(!visit[nx][ny] && before[nx][ny] == beforeNum){
                        visit[nx][ny] = true;
                        before[nx][ny] = change;
                        q.add(nx);
                        q.add(ny);
                    }
                }
            }
        }
    }

    static boolean comp(){
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=M ;j++){
                if(before[i][j] != after[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
