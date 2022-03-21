package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2615_오목 {

    static int[] dx = {-1, 0, -1, -1, 1, 0, 1, 1};        //0~3까지 내가 끝점 , 4~7까지 내가 시작점
    static int[] dy = {0, -1, 1, -1, 0, 1, -1, 1};

    static int[][] arr;
    static boolean ansTF = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 구현하세요.
        arr = new int[20][20];
        for (int i = 1; i <= 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                if (arr[i][j] != 0 ) {        //돌이 있고 방문안했으면
                    bfs(i, j, arr[i][j]);
                    if (ansTF) return;        //5개 되는 곳 찾았으면 종료
                }
            }
        }


        System.out.println(0);


    }

    static void bfs(int x, int y, int color) {
        int[][] visit = new int[20][20];
        visit[x][y] = 1;
        int ansX = 0;
        int ansY = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int cnt =2;
            while (true) {
                if (nx < 1 || nx >= 20 || ny < 1 || ny >= 20) break;    //범위 밖이면
                if (visit[nx][ny] != 0 || arr[nx][ny] != color) break;    //
                visit[nx][ny] = cnt++;                                    //몇번째인지 설정 후
                ansX = nx;
                ansY = ny;
                nx += dx[i];
                ny += dy[i];
            }

            if (cnt == 6) {            //볼수 있는곳 봤는데 5개 연속이면
                ansTF = true;
                System.out.println(color);
                if (i < 4) {
                    System.out.println(ansX + " " + ansY);        //왼쪽으로 가는거여서 내가 끝이면 마지막에 방문한 곳 출력
                } else {
                    System.out.println(x + " " + y);            //내가 시작점
                }
                return;
            }
        }
    }
}
