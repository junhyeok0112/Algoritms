package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Knight{
    int x ;
    int y ;
    Knight(int x,  int y){
        this.x = x;
        this.y = y;
    }
}

public class P18404_현명한_나이트 {

    static int N , M , X ,Y;
    static int[] dx = {-2,-2,-1,-1,1,1,2,2};
    static int[] dy = {-1,1,-2,2,-2,2,-1,1};
    static int[][] map;
    static int[][] visit;
    static Queue<Integer> enemy = new LinkedList<>();


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        visit = new int[N+1][N+1];
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i<M ;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            enemy.add(num1);
            enemy.add(num2);
        }
        bfs();  //모든 도착하는데 걸리는 시간 계산 후 -> enemy 배열에서 하나씩 빼서 계산
        while (!enemy.isEmpty()){
            int x = enemy.poll();
            int y = enemy.poll();
            System.out.print(visit[x][y] - 1  + " ");
        }
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();

        q.add(X);
        q.add(Y);
        visit[X][Y] = 1; //초기 나이트 위치
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0 ; i<8; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx> 0 && nx<= N && ny>0 && ny<=N){
                    if(visit[nx][ny] == 0){
                        visit[nx][ny] = visit[rx][ry] + 1;
                        q.add(nx);
                        q.add(ny);
                    }
                }
            }
        }
    }

}
