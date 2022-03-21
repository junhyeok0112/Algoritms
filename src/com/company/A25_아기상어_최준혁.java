package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class A25_아기상어_최준혁 {

    static int n;
    static int[][] map;
    static int sec = 0;         //정답 출력할 초
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int eatCnt = 0;     //먹은 수
    static int shark = 2;      //상어 크기
    static int startX = 0, startY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    startX = i;
                    startY = j;
                }
            }
        }
        //좌 상단이 우선수위 부여 받음 -> 중요
        while (canEat(startX,startY, shark) != 0) { //먹을게 남아있는동안 반복
            //bfs로 이동 -> 가장 가까운 물고기 위치로 이동하기
            bfs(startX, startY);
        }

        System.out.println(sec);

    }

    //상어가 먹을 수 있는게 남아있는지 확인 -> 상어가 있는 위치에서 bfs를 통해서 확인해야한다.
//    static int canEat(int shark) {
//        int min = 10;       //남아있는 물고기 중 최소 크기
//        for (int i = n - 1; i >= 0; i--) {
//            for (int j = n - 1; j >= 0; j--) {
//                if (map[i][j] == 0 || map[i][j] == 9) continue;
//                if (min >= map[i][j]) {// 최소 물고기 크기 구하기 ,위쪽을 최신
//                    min = map[i][j];
//                }
//            }
//        }
//        if (min == 10) return 0;         //10이면 물고기 안남았으므로 0리턴
//        if (min >= shark) return 0;      //모든 물고기가 아기상어보다 크거나 같으므로 0리턴
//        return min;                     //상어보다 작은 크기 물고기 존재하므로 먹을 수 있다고 리턴
//    }

    static int canEat(int startX , int startY, int shark) {
       Queue<Integer> q = new LinkedList<>();
       boolean[][] visit = new boolean[n][n];
       q.add(startX);
       q.add(startY);
       visit[startX][startY] = true;
       while (!q.isEmpty()){
           int rx = q.poll();
           int ry = q.poll();
           for(int i = 0 ; i< 4; i++){
               int nx = rx + dx[i];
               int ny = ry +dy[i];
               if(nx<0 || nx>= n||ny<0 ||ny>=n) continue;
               if(!visit[nx][ny] && map[nx][ny] <= shark){
                   if(map[nx][ny] < shark && map[nx][ny] != 0){     //먹을 수 있는 곳이 있으면 return 1 단 비어있는 곳이아닐떄
                       visit[nx][ny] =true;
                       return 1;
                   } else{                      //그냥 지나가는 곳이면
                       visit[nx][ny] = true;
                       q.add(nx);
                       q.add(ny);
                   }
               }
           }
       }
       return 0;
    }

    static void bfs(int x, int y) {
        boolean[][] visit = new boolean[n][n];
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> possible = new LinkedList<>();   //상어가 갈 수 있고 , 물고기가 있는 곳 저장
        q.add(x);
        q.add(y);
        visit[x][y] = true;
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int t = 0; t < qsize/2; t++) {
                int rx = q.poll();
                int ry = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if (visit[nx][ny] || map[nx][ny] > shark) continue;  //범위안이고 상어보다 크기가 크면
                    //방문 안했고 지나갈 수 있는 곳이면
                    if (map[nx][ny] == shark || map[nx][ny] == 0) {   //크기 같으면 그냥 지나만 갈 수있음 물고기가없어서 지나갈 경우
                        visit[nx][ny] = true;
                        q.add(nx);
                        q.add(ny);
                        continue;
                    }
                    if (map[nx][ny] < shark) {    //먹을 수 있으면
                        //가까운 곳 도착이므로 더 이상탐색안해도될것같음
                        visit[nx][ny] = true;
                        possible.add(nx);
                        possible.add(ny);
                    }
                }
            }
            sec++;      //1초 지남
            if (!possible.isEmpty()) {    //만약 가까운 곳에서 먹을 수 있는 물고기들 찾았으면 멈춤
                break;
            }

        }

        if (!possible.isEmpty()) {
            //좌 상단으로 움직이게해야함함
            int endX = possible.poll();
            int endY = possible.poll();
            while (!possible.isEmpty()) {
                int nx = possible.poll();
                int ny = possible.poll();
                if (endX > nx) {
                    //새로뽑은게 더 위에 존재하는거면
                    endX = nx;
                    endY = ny;
                    continue;
                } else if (endX == nx) {
                    if (endY > ny) {
                        endX = nx;
                        endY = ny;
                        continue;
                    }
                }
            }
            eatCnt++;
            if (eatCnt == shark) {   //상어 크기 증가시키고 먹은 횟수초기화
                shark++;
                eatCnt = 0;
            }
            map[endX][endY] = 9;    //그 위치를 상어 위치로 갱신
            map[startX][startY] = 0; //원래 위치 0으로
            startX = endX;          //시작값 초기화
            startY = endY;
        }
    }
}
