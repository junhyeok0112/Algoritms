package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//빨강 , 파랑 공을 따로 이동하는게 아닌 같이 이동시킨다 ->매우 중요 포인트
//또 그 순간 이동했을 때 판의 상태를 클래스로 나타낸다.
//여러개의 경로가 존재하는데 이중 BFS를 통해 최단거리로 갔을때의 판 상태(Marble 객체)의 값을 출력
class Marble{
    int rx;
    int ry;
    int bx;
    int by;
    int cnt;        //몇번 이동했는지 횟수를 나타냄
    Marble(int rx,  int ry ,int bx , int by , int cnt){
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.cnt = cnt;
    }

}

public class P13460_구슬탈출2 {
    static int N , M;
    static char[][] map;
    static boolean[][][][] visit;               //Red ,Blue를 2가지를 한번에 봐야하므로 4차원 배열
    static int Rx, Ry ,Bx, By , Ox,Oy;
    static int[] dx = {-1,1,0,0};       //하상우좌 순으로 움직임
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M][N][M];
        for(int i =0 ; i<N ;i++){
            String s = br.readLine();
            for(int j = 0 ; j<M ;j++) {
                map[i][j] = s.charAt(j);
                if(s.charAt(j) == 'R'){
                    Rx = i;
                    Ry = j;
                } else if(s.charAt(j) == 'B'){
                    Bx = i;
                    By = j;
                } else if(s.charAt(j) == 'O'){
                    Ox = i;
                    Oy = j;
                }
            }
        }
        Marble start = new Marble(Rx,Ry,Bx,By ,0);    //처음 판의 상태 ->이동횟수 없어서 0
        System.out.println(bfs(start));

    }

    static int bfs(Marble start){
        Queue<Marble> q = new LinkedList<>();
        visit[Rx][Ry][Bx][By] = true;           //처음 위치 셋팅
        q.add(start);
        while(!q.isEmpty()){
            Marble r = q.poll();
            int RedX = r.rx;
            int RedY = r.ry;
            int BlueX = r.bx;
            int BlueY = r.by;
            int curCnt = r.cnt;
            if(curCnt>=10){
                return -1;
            }
            for(int i = 0 ; i<4; i++){
                int nrx = RedX;
                int nry = RedY;
                int nbx = BlueX;
                int nby = BlueY;
                while(map[nbx+dx[i]][nby+dy[i]] != '#'){
                    nbx += dx[i];
                    nby += dy[i];
                    if(nbx == Ox && nby ==Oy) break;    //만약 구멍에 빠지면 종료
                }

                while(map[nrx+dx[i]][nry+dy[i]] != '#'){
                    nrx += dx[i];
                    nry += dy[i];
                    if(nrx == Ox && nry ==Oy) break;    //만약 구멍에 빠지면 종료
                }

                if(nbx == Ox && nby ==Oy) continue;     //파란공을 먼저봐서 구멍에 빠졌으면 실패
                //즉 지금의 경로는 잘못된 경로이므로 경로를 넣어주는 Q에 넣지 않고 continue로 넘어간다 -> 다른 경로는 가능할 수 도 있어서 다른 경로보러감
                if(nrx == Ox && nry ==Oy){      //빨간색 들어가면 성공 ->즉 움직인 횟수 출력
                    return curCnt+1;
                }

                //만약에 2개의 구슬이 같은 지점왔으면 그에 따라 처리해줘야함
                if(nrx ==nbx && nry == nby){    //상 하 우 좌로 움직임
                    switch (i){
                        case 0:                 //X가 행 Y가 열이라는걸 잊지말자
                            if(RedX > BlueX){   //아래로 움직임 , 처음 X가 작은게 위로감
                                nrx -= dx[i];
                            } else{
                                nbx -= dx[i];
                            }
                            break;
                        case 1:
                            if(RedX > BlueX){
                                nbx -=dx[i];
                            } else nrx -=dx[i];
                            break;
                        case 2:
                            if(RedY > BlueY) nby -=dy[i];            //오른쪽으로 갈때 -> 처음 Y가 작은게 더 왼쪽에 있음
                            else nry -=dy[i];
                            break;
                        case 3:
                            if(RedY > BlueY) nry -=dy[i];           //왼쪽으로 갈때 -> 처음 더 큰 Y를 가진게 오른쪽에있음
                            else nby -= dy[i];
                            break;
                    }
                }

                if(!visit[nrx][nry][nbx][nby]){
                    visit[nrx][nry][nbx][nby] = true;
                    q.add(new Marble(nrx,nry,nbx,nby,curCnt+1));
                }

            }
        }
        return -1;
    }

}
