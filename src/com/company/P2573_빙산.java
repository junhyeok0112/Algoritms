package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2573_빙산 {

    static int n, m;
    static int[][][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;   //시간
        //녹을거 예상 -> 시간 출력 -> 녹임
        while (true) {
            //초기화
            int cnt = 0;
            visit = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j][1] = 0;
                }
            }
            //녹일거 체크
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (!visit[i][j] && map[i][j][0] != 0) {
                        dfs(i, j);
                        cnt++;
                        if (cnt >= 2) {       //2개가 됐을때 시간 리턴
                            System.out.println(time);
                            return;
                        }
                    }
                }
            }
            //녹임
            melt();
            //더 이상 빙하가 없을 경우
            if (cnt == 0) {
                System.out.println(0);
                return;
            }
            //연도 갱신
            time++;
        }
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;
        //사방에 0이 몇개인지 세기
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;  //범위밖이면
            if (map[nx][ny][0] == 0) map[x][y][1]++;         //주변이 물이면 그 해 녹을 높이 저장
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            //범위 밖이고 , 빙하가 아니고 방문 체크한 곳이면 넘어감
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny][0] == 0 || visit[nx][ny]) continue;  //범위밖이면
            dfs(nx, ny);
        }
    }

    static void melt() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j][0] -= map[i][j][1];
                if (map[i][j][0] < 0) map[i][j][0] = 0;
            }
        }
    }
}
