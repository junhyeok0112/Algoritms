package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P12100_2048_easy {

    static int n;
    static int[][] map;
    static int max = -1;

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

        pro(0 , map);
//        int[][] test = rightMap(map);
//        test = rightMap(test);
//        test = upMap(test);
        System.out.println(max);

    }

    static void pro(int cnt, int[][] map) {
        if (cnt >= 5) {
            max = Math.max(max, findMax(map));
            return;
        }

        //위로 올리고 다음 횟수
        pro(cnt + 1, upMap(map));
        //아래로 내리고 다음 횟수
        pro(cnt + 1, downMap(map));
        //오른쪽으로 움직이고 다음 횟수
        pro(cnt + 1, rightMap(map));
        //왼쪽으로 움직이고 다음 횟수
        pro(cnt + 1, leftMap(map));

        max = Math.max(max, findMax(map));
    }

    private static int[][] leftMap(int[][] map) {
        int[][] temp = new int[n][n];
        Queue<Integer> q = new LinkedList<>(); //
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n;j++) {
                if(map[i][j] == 0) continue;
                q.offer(map[i][j]);    //0이 아닌 값들 집어넣기 -> 한줄처리
            }
            //temp에 넣은 값들 뿌려주기
            int column = 0;    //temp[row][i]에 뿌려주기
            while (!q.isEmpty()){
                if(temp[i][column] == 0){  //0이여서 채워야 할 부분이면
                    temp[i][column] = q.poll();
                    continue;
                }
                if(temp[i][column] == q.peek()){
                    temp[i][column] *= 2;  //같으면 합침
                    q.poll();
                    column++;              //한번 합친거 또 합치면 안되므로 다음거
                } else {    //0이 아니고 같지않으면
                    column++;
                    temp[i][column] = q.poll();
                }
            }
        }
        return temp;
    }

    private static int[][] rightMap(int[][] map) {
        int[][] temp = new int[n][n];
        Queue<Integer> q = new LinkedList<>(); //
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j >= 0; j--) {
                if(map[i][j] == 0) continue;
                q.offer(map[i][j]);    //0이 아닌 값들 집어넣기 -> 한줄처리
            }
            //temp에 넣은 값들 뿌려주기
            int column = n-1;    //temp[row][i]에 뿌려주기
            while (!q.isEmpty()){
                if(temp[i][column] == 0){  //0이여서 채워야 할 부분이면
                    temp[i][column] = q.poll();
                    continue;
                }
                if(temp[i][column] == q.peek()){
                    temp[i][column] *= 2;  //같으면 합침
                    q.poll();
                    column--;              //한번 합친거 또 합치면 안되므로 다음거
                } else {    //0이 아니고 같지않으면
                    column--;
                    temp[i][column] = q.poll();
                }
            }
        }
        return temp;
    }

    private static int[][] downMap(int[][] map) {
        int[][] temp = new int[n][n];
        Queue<Integer> q = new LinkedList<>(); //
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j >= 0; j--) {
                if(map[j][i] == 0) continue;
                q.offer(map[j][i]);    //0이 아닌 값들 집어넣기 -> 한줄처리
            }
            //temp에 넣은 값들 뿌려주기
            int row = n-1;    //temp[row][i]에 뿌려주기
            while (!q.isEmpty()){
                if(temp[row][i] == 0){  //0이여서 채워야 할 부분이면
                    temp[row][i] = q.poll();
                    continue;
                }
                if(temp[row][i] == q.peek()){
                    temp[row][i] *= 2;  //같으면 합침
                    q.poll();
                    row--;              //한번 합친거 또 합치면 안되므로 다음거
                } else {    //0이 아니고 같지않으면
                    row--;
                    temp[row][i] = q.poll();
                }
            }
        }
        return temp;
    }

    //배열을 위로 이동하는 함수
    private static int[][] upMap(int[][] map) {
        int[][] temp = new int[n][n];
        Queue<Integer> q = new LinkedList<>(); //
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[j][i] == 0) continue;
                q.offer(map[j][i]);    //0이 아닌 값들 집어넣기 -> 한줄처리
            }
            //temp에 넣은 값들 뿌려주기
            int row = 0;    //temp[row][i]에 뿌려주기
            while (!q.isEmpty()){
                if(temp[row][i] == 0){  //0이여서 채워야 할 부분이면
                    temp[row][i] = q.poll();
                    continue;
                }
                if(temp[row][i] == q.peek()){
                    temp[row][i] *= 2;  //같으면 합침
                    q.poll();
                    row++;              //한번 합친거 또 합치면 안되므로 다음거
                } else {    //0이 아니고 같지않으면
                    row++;
                    temp[row][i] = q.poll();
                }
            }
        }
        return temp;
    }

    private static int findMax(int[][] map) {
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }
}
