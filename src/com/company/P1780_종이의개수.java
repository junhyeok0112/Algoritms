package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1780_종이의개수 {

    static int n;
    static int[][] map;
    static int zero = 0;   //0으로 된거 개수
    static int one = 0;
    static int minusOne = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //분할 정복으로 나누기
        dc(0,0,n,n);

        //정답 출력
        System.out.println(minusOne);
        System.out.println(zero);
        System.out.println(one);
    }

    //변수를 시작점과 사이즈만으로도 할 수 있음 -> 그러면 더 간단해짐
    static void dc(int x1, int y1, int x2, int y2) {
        if (check(x1, y1, x2, y2)) { //하나로 이루어져 있으면
            if (map[x1][y1] == 0) zero++;
            else if (map[x1][y1] == 1) one++;
            else minusOne++;
            return;
        }

        //하나로 안 이루어졌으면
        int len = (x2 - x1) / 3;
        int len2 = len * 2;
        dc(x1, y1, x1 + len, y1 + len);
        dc(x1 + len, y1 + len, x1 + len2, y1 + len2);
        dc(x1 + len2, y1 + len2, x2, y2);
        dc(x1, y1 + len, x1 + len, y1 + len2);
        dc(x1, y1 + len2, x1 + len, y2);
        dc(x1 + len, y1, x1 + len2, y1 + len);
        dc(x1 + len, y1 + len2, x1 + len2, y2);
        dc(x1 + len2, y1, x2, y1 + len);
        dc(x1 + len2, y1 + len, x2, y1 + len2);
    }

    static boolean check(int x1, int y1, int x2, int y2) {
        int start = map[x1][y1];
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                if (map[i][j] != start) return false;
            }
        }
        return true;
    }
}
