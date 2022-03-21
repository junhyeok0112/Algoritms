package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Dir{          //x,y를 탐색했을때 방향과 거울 갯수 -> 이 걸로 visit 갱신해서 visit에서 최소 찾음
    int x;
    int y;
    int dir;
    int mirror;

    Dir(int x, int y, int dir , int mirror){
        this.x = x;
        this.y =y;
        this.dir = dir;
        this.mirror = mirror;
    }
}

public class P6087_레이저통신 {
    static int W, H;
    static char[][] map;
    static int[][] visit;                   //visit는 그 xy까지 왔을때의 최소 필요한 개수 -> 계속 갱신
    static int[][] point = new int[2][2];   //c의 위치를 저장할 배열
    static int[] dx = {-1,1,0,0};           //상하좌우
    static int[] dy = {0,0,-1,1};



    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visit = new int[H][W];
        int pointCnt = 0;



        for(int i = 0 ; i<H ;i++){
            String s = br.readLine();
            for(int j = 0 ; j<W ;j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'C'){   //[0][0],[0][1] 에 첫번째 C저장
                    point[pointCnt][0] = i;
                    point[pointCnt][1] = j;
                    pointCnt++;
                }
            }
        }

        System.out.println(bfs());



    }


    //bfs로 최단거리구하면서 만약 90도 꺾였으면 1추가해서 거울 개수 추가
    static int bfs(){
        Queue<Dir> q =new LinkedList<>();
        q.add(new Dir(point[0][0] , point[0][1],-1 , 0));
        visit[point[0][0]][point[0][1]] = -1;           //시작지점
        while (!q.isEmpty()){
            Dir r = q.poll();
            int rx = r.x;
            int ry = r.y;
            int rd = r.dir;
            int rm = r.mirror;
            for(int i = 0 ; i <4 ; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                int nm = rm;
                if(nx>= 0 && nx<H && ny>=0 && ny<W && map[nx][ny] != '*'){
                    int nd = i+1;    //그 점을 방문했을때의 방향 -> 같은 점을 방문했을때 다른 방향에서 왔을 수도 있으므로 새로운 nd, nm을 정의해주는 것
                    if(nd != rd && rd != -1){   //방향이 다르고 그 전에가 시작점이 아닐때
                        nm++;                   //거울 추가
                    }
                    if(visit[nx][ny] == 0){     //처음 방문지면 여태 필요했던 거울 수로 visit값 초기화
                        visit[nx][ny] = nm;
                        q.add(new Dir(nx,ny,nd,nm));    //bfs위해 실행
                    }else{                      //첫방문 아니면
                        if(visit[nx][ny] >= nm){        //작거나 같을 경우 그점에서 다시 bfs 를 해야함 -> 원래 값을 가진채로 -> 이 때 같은 지점을 같은 갯수로 들어왔을때 처리가능
                            // 그 점에서 다시 bfs 하므로 최소값을 가진 상태로 주변을 갱신 가능
                            visit[nx][ny] = nm;
                            q.add(new Dir(nx,ny, nd, nm));
                        }
                    }
                }
            }
        }
        //System.out.println(visit[point[1][0]][point[1][1]].visit);
        return visit[point[1][0]][point[1][1]];  //한 지점에서 끝지점을 최단거리로 도착했을때까지의 거울 개수 출력
    }
}
