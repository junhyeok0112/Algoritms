package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1987_알파벳{

    static int R,C;
    static char[][] map ;
    static boolean[][] visit;
    static boolean[] chk = new boolean[26];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int max = -1;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R+1][C+1];
        visit = new boolean[R+1][C+1];
        for(int i = 1 ; i<=R;i++){
            String s = br.readLine();
            for(int j = 1 ; j<=C;j++){
                map[i][j] = s.charAt(j-1);
            }
        }
        visit[1][1] = true;
        chk[map[1][1] - 'A'] = true;
        dfs(1,1);
        System.out.println(max);

    }

    static void dfs(int x , int y){
        cnt++;
        //System.out.println(x+" " + y +" " + cnt);
        for(int i = 0 ; i<4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >0 && nx<=R && ny> 0 &&ny<=C){
                if(!visit[nx][ny] && !chk[map[nx][ny] - 'A']) { //갈 수 있는 곳이면
                    visit[nx][ny] = true;
                    chk[map[nx][ny] - 'A'] = true;    //시작지점 셋팅
                    dfs(nx,ny);
                    visit[nx][ny] = false;
                    chk[map[nx][ny] - 'A'] = false;
                    cnt--;
                }   
            }
        }
        max = Math.max(cnt, max);

    }
}
