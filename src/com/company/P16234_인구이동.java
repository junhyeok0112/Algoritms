package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16234_인구이동 {

    static int n,l,r;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int day = 0;
    static boolean chk = true;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i = 0 ; i <n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<n ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true){
            //chk 가 false 일떄까지 반복
            visit = new boolean[n][n];
            int cnt = 0;
            for(int i = 0 ; i<n;i++){
                for(int j = 0 ; j<n;j++){
                    if(!visit[i][j]){
                        bfs(i,j);
                        cnt++;
                    }
                }
            }
            if(cnt == n*n) break;     //모든 점 방문해서 봤을때 -> 즉 모든 국가가 따로일떄
            day++;
        }
        System.out.println(day);

    }
    static void bfs(int x ,int y ){
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> temp = new LinkedList<>();   //같은 지역에 있는 애들
        int sum = map[x][y];
        q.add(x);
        q.add(y);
        temp.add(x);
        temp.add(y);
        visit[x][y] = true;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0 ; i<4; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx< 0 || nx>=n || ny<0 || ny>= n || visit[nx][ny] ) continue;
                if( l > Math.abs(map[rx][ry] - map[nx][ny]) ||  r< Math.abs(map[rx][ry] - map[nx][ny]))continue;
                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
                temp.add(nx);
                temp.add(ny);
                sum += map[nx][ny];
            }
        }
        //같은 지역 없을 경우
        if(temp.size() == 2) {  //연결 안되어 있으면 false로 -> 모든 점봤을떄 false면 끝
            return;
        }
        else{       //하나라도 분배 가능하면 true;
            int size = temp.size() / 2; //총 갯수
            sum = sum / size;           //분배
            while (!temp.isEmpty()){
                int rx = temp.poll();
                int ry = temp.poll();
                map[rx][ry] = sum;
            }
        }
    }

}
