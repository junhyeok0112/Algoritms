package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16918_봄버맨 {

    static int r, c, n;
    static int[][] map;
    static int[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        visit = new int[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                char now = str.charAt(j);
                if (now == 'O') {   //now가 폭탄이면
                    map[i][j] = 0;        //시간 셋팅
                } else {
                    map[i][j] = -1;
                }
            }
        }
        int time = 1;
        Queue<Integer> q = new LinkedList<>();  // 터질 폭탄들 저장
        while (true){
            //어떤 행동을 한 후 시간 증가 즉 time++은 맨 마지막

            if(time % 2 == 0){          //폭탄 설치
                for(int i = 0 ; i<r; i++){
                    for(int j = 0 ; j<c; j++){
                        if(map[i][j] == -1) map[i][j] = 0;  //폭탄 설치
                        else if(map[i][j]++ >= 3){
                            q.add(i);
                            q.add(j);                       //터질 폭탄들 좌표 저장
                        }
                    }
                }
            } else{     //폭탄 설치할 때가 아니면
                for(int i = 0 ; i<r; i++){
                    for(int j = 0; j<c ;j++){
                        if(map[i][j] == -1 )continue;
                        else if(++map[i][j] >= 3){
                            q.add(i);
                            q.add(j);                       //터질 폭탄들 좌표 저장
                        }
                    }
                }
            }

            //폭탄 터트리기
            while (!q.isEmpty()){
                int rx = q.poll();
                int ry = q.poll();
                map[rx][ry] = -1;       //폭탄 터트려서 초기화
                for(int i = 0 ; i <4 ;i++){
                    int nx = rx +dx[i];
                    int ny = ry + dy[i];
                    if(nx <0 || nx>= r ||ny <0 ||ny>=c) continue;
                    map[nx][ny] = -1;
                }
            }
            if(time == n) break;       //정해진 시간만큼 봤을 때  , 끝낼 때를 잘 봐야함
            time++;     //시간 증가
        }
        //정답 출력
        for(int i = 0 ; i<r; i++){
            for(int j = 0; j<c ;j++){
                if(map[i][j] == -1 ){
                    System.out.print(".");
                } else{
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }




}
