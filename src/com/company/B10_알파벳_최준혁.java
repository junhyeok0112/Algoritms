package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B10_알파벳_최준혁 {

    static int r,c;
    static int[][] visit;
    static char[][] map;
    static int[] chk = new int[26]; //해당 알파벳을 밟았는지 확인
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int ans = -1;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        visit = new int[r][c];
        map = new char[r][c];
        for(int i = 0 ; i<r;i++){
            String str = br.readLine();
            for(int j = 0 ; j<c; j++){
                map[i][j] = str.charAt(j);
            }
        }

        //0,0에서 시작
        //bfs(0,0);
        dfs(0,0,1);
        System.out.println(ans);
    }


    static void dfs(int x, int y , int cnt){
        visit[x][y] = 1;
        chk[map[x][y] - 'A']++;
        for(int i = 0 ; i< 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx>=r || ny<0 ||ny>=c) continue;   //범위 넘어가면
            if(visit[nx][ny] != 0 || chk[map[nx][ny] - 'A'] != 0) continue; //방문한곳이거나 방문한 알파벳이면 넘어감
            dfs(nx,ny,cnt+1);
        }
        //더이상 갈 수 없을때
        ans = Math.max(ans , cnt);
        visit[x][y] = 0;
        chk[map[x][y]-'A']--;
    }

}
