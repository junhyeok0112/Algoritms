package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7569_토마토 {

    static int N,M,H ;  //N이 행 , M 이 열
    static int[][][] map ;
    static boolean[][][] visit;
    static int cnt = 0;
    static int[] dh = {-1,1,0,0,0,0};
    static int[] dx = {0,0,1,-1,0,0};
    static int[] dy = {0,0,0,0,1,-1};
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];
        visit = new boolean[H][N][M];
        for(int h = 0 ; h<H ; h++){
            for(int i = 0 ; i<N ;i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j<M ;j++){
                  map[h][i][j] = Integer.parseInt(st.nextToken());
                  if(map[h][i][j] == 1){
                      q.add(h);
                      q.add(i);
                      q.add(j);
                      visit[h][i][j] = true;
                  }
                }
            }
        }

        bfs();

        visit = new boolean[H][N][M];
        for(int h = 0 ; h<H ; h++){
            for(int i = 0 ; i<N ;i++){
                for(int j = 0 ; j<M ;j++){
                    if(map[h][i][j] == 0){//안익은게 있으면
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(cnt - 1);

    }

    static void bfs(){
        while (!q.isEmpty()) {
            int qsize = q.size() / 3;       //3개가 한세트 이므로
            for (int k = 0; k < qsize; k++) {
                int rh = q.poll();
                int rx = q.poll();
                int ry = q.poll();
                for (int i = 0; i < 6; i++) {
                    int nh = rh + dh[i];
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if (nh >= 0 && nh < H && nx >= 0 && nx < N && ny >= 0 & ny < M) { //범위 안이면
                        if(!visit[nh][nx][ny] && map[nh][nx][ny] == 0){
                            visit[nh][nx][ny] = true;
                            map[nh][nx][ny] = 1;
                            q.add(nh);
                            q.add(nx);
                            q.add(ny);
                        }
                    }
                }
            }
            cnt++;
        }
    }

}
