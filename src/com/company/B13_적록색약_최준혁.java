package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//적록색약이면 빨강과 초록색의 차이를 못느낀다.-> 즉 R과 G를 같은 색이라 본다.
public class B13_적록색약_최준혁 {

    static int n;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int ans  =0 ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visit = new boolean[n][n];
        for(int i = 0 ; i<n ;i++){
            String str = br.readLine() ;
            for(int j=  0; j<n ;j++){
                map[i][j] = str.charAt(j);
            }
        }

        //색약이 아닐 경우
        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ; j<n; j++){
                if(!visit[i][j]){
                    bfs(i,j,map[i][j],false);
                    ans++;
                }
            }
        }

        System.out.print(ans+" ");

        //visit 초기화
        visit = new boolean[n][n];
        //ans 초기화
        ans = 0;


        //색약이 맞는 경우
        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ; j<n; j++){
                if(!visit[i][j]){
                    bfs(i,j,map[i][j],true);
                    ans++;
                }
            }
        }
        System.out.print(ans);

    }

    static void bfs(int startX ,int startY , char color , boolean  flag){
        Queue<Integer> q = new LinkedList<>();
        visit[startX][startY] = true;
        q.add(startX);
        q.add(startY);
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0 ; i< 4 ; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx<0 || nx>= n||ny <0 || ny>=n|| visit[nx][ny])  continue;
                if(flag && (map[nx][ny] == color || (map[nx][ny] == 'R' && color == 'G') || (map[nx][ny] == 'G' && color == 'R'))){       //적록색약이면 R과 G 같게봄
                    visit[nx][ny] = true;
                    q.add(nx);
                    q.add(ny);
                }

                if(!flag && map[nx][ny] == color){  //적록색약 아니면 같은색만
                    visit[nx][ny] = true;
                    q.add(nx);
                    q.add(ny);
                }
            }
        }
    }
}
