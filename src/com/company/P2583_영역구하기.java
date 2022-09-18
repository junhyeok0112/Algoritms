package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2583_영역구하기 {

    static int m, n, k;
    static int[][] map;
    static boolean[][] visit;
    static boolean[][] visit2;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        visit = new boolean[m][n];
        visit2 = new boolean[m][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            fill(x1, y1, x2, y2);
        }

        int cnt = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && !visit2[i][j]) {
                    temp.add(bfs(i, j));
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        int tempSize = temp.size();
        Collections.sort(temp);
        for (int i = 0; i < tempSize; i++) {
            System.out.print(temp.get(i) + " ");
        }
    }

    static int bfs(int i, int j) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        q.add(j);
        visit2[i][j] = true;
        int cnt = 0;            //안칠해져있는 범위의 넓이
        while (!q.isEmpty()) {
            int rx = q.poll();
            int ry = q.poll();
            cnt++;
            for (int t = 0; t < 4; t++) {
                int nx = rx + dx[t];
                int ny = ry + dy[t];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visit2[nx][ny] || visit[nx][ny]) continue;
                q.add(nx);
                q.add(ny);
                visit2[nx][ny] = true;
            }
        }
        return cnt;
    }

    static void fill(int x1, int y1, int x2, int y2) {
        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) {
                visit[i][j] = true;
            }
        }
    }

}
