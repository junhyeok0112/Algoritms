package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3109_빵집 {

    static int r,c;
    static char[][] map;
    static int[] dx = {-1,0,1};
    static int[] dy = {1,1,1};
    static boolean[][] visit;     //하나의 지점을 총 3군데에서 올 수 있음
    static boolean[] flag;          //해당 행에서 끝까지 도달 할 수 있는지 체크

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visit = new boolean[r][c];
        flag = new boolean[r];
        for(int i = 0 ; i<r; i++){
            String str = br.readLine();
            for(int j = 0 ; j<c; j++){
                map[i][j] = str.charAt(j);
            }
        }

        //각 행에서 탐색 시작
        for(int i = 0 ; i< r; i++){
            dfs(i, i, 0);
        }

        //마지막 열에서 어떤 방향이든 방문한거 있으면 값 증가
        int ans = 0;
        for(int i = 0 ; i<r ; i++){
            if(visit[i][c-1]) ans++;
        }
        System.out.println(ans);
    }
    //해당 행이 끝까지 도달 못했을 경우 다른 방향으로 갈 수 있는지도 체크해줘야함.
    //만약 위로 가는 길이 불가능하다면 갈 수있었다고 끝내는게 아니라 돌아와서 다시 갈 수 있는 길로 가야함
    static void dfs(int start , int row , int col) {
        if(col >= c-1){
            visit[row][col] = true;
            flag[start] = true;   //도달했다고 체크
            return;
        }

        visit[row][col] = true;
        for(int i = 0 ;i<3; i++){
            if(flag[start]) return;
            int nx = row + dx[i];
            int ny = col + dy[i];
            if( nx<0 ||nx >= r || ny< 0 ||ny >=c || visit[nx][ny] ||map[nx][ny] == 'x') continue;
            dfs(start,nx,ny);
        }

    }
}
