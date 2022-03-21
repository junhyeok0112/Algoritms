package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3055_탈출 {

    static int R,C;
    static char[][] map;
    static int[][] visit;
    static int[][] wvisit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Integer> water = new LinkedList<>();

    //비버가 먼저 움직임.. 헷갈려음 비버가 움직일 곳에 물이 차면안된다는말이였음
    //물을 먼저 채운후 물이 찬 시간보다 비버가 도착한 시간이 빠르기만하면 비버가 지나갈수 있음 ->wvisit와 visit로 비교
    //도치시간 + 1 >= 물 도착시간이면 그 칸에 갈 수없음
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R+1][C+1];
        visit = new int[R+1][C+1];
        wvisit = new int[R+1][C+1];
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();
        for(int i = 1 ; i<=R; i++){
            String s = br.readLine();
            for(int j =1 ; j<=C;j++){
                map[i][j] = s.charAt(j-1);
                if(map[i][j] == 'D'){
                    end.add(i);
                    end.add(j);
                }
                if(map[i][j] == 'S'){
                    start.add(i);
                    start.add(j);
                }

                if(map[i][j] == '*'){
                    water.add(i);
                    water.add(j);
                }

            }
        }

        WaterBFS();

        BFS(start.get(0) , start.get(1));

        if(visit[end.get(0)][end.get(1)] == 0){ //도착 못했을경우
            System.out.println("KAKTUS");
        }else{
            System.out.println(visit[end.get(0)][end.get(1)] -1);   //1부터 시작했으므로
        }


    }

    static void WaterBFS() {
        Queue<Integer> q = new LinkedList<>();
        while (!water.isEmpty()) {
            int startX = water.poll();
            int startY = water.poll();
            wvisit[startX][startY] = 1;
            q.add(startX);
            q.add(startY);
        }
        //System.out.println(wvisit[1][3] +"내부"); //왜 여기서 wvisit값이 다르죠 ?
        while (!q.isEmpty()) {
            int rx = q.poll();
            int ry = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if (nx > 0 && nx <= R && ny > 0 && ny <= C) {
                    if (wvisit[nx][ny] == 0 && map[nx][ny] == '.') {
                        wvisit[nx][ny] = wvisit[rx][ry] + 1;
                        q.add(nx);
                        q.add(ny);
                    }
                }
            }
        }

    }

    static void BFS(int x, int y){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visit[x][y] = 1;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0 ; i<4; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if (nx > 0 && nx <= R && ny > 0 && ny <= C){
                    //고슴도치가 방문할 위치의 visit가 물이 방문했을때 시간 wvisit보다 2이상 작아야지 방문할 수 있다. 1차이나면 물이 먼저차서 잠겨버림
                    int temp = visit[rx][ry] + 1;
                    if(map[nx][ny] =='D'){
                        visit[nx][ny] = temp;
                        return; //목적지 도착할 경우 ->물이 안들어오니까 바로 옆에가면 무조건 갈 수있음
                    }
                    if(temp >=wvisit[nx][ny] && wvisit[nx][ny] != 0) continue; //visit가 wvisit보다 적어도 1이상 차이가 안날 경우 못감
                    //고슴도치가 먼저 움직이니까 작기만 하면 가능함 -> 즉 고슴도치 2  ,물 2 이럴때는 안되고 고슴도치 2 ,물 3 이면 가능함
                    //D에는 물이 안들어와서 wvisit = 0 이므로 예외처리해줘야함
                    //wvisit = 0이면 물이 안들어온거니까 방문가능 -> 따라서 0이 아닐경우만 예외처리해줘야함
                    if(visit[nx][ny] == 0 && map[nx][ny] != '*' && map[nx][ny] != 'X'){   //방문 안했고 물이 아닌고 , 돌도 아닌곳
                        visit[nx][ny] = temp;
                        q.add(nx);
                        q.add(ny);
                    }
                }
            }
        }
    }
}
