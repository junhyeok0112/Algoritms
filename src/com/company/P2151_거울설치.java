package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class P2151 implements Comparable<P2151>{
    int x;
    int y;
    int mir;            //필요한 최소 거울 개수
    int dir;            //지금 나타내는 방향
    P2151(int x, int y, int mir ,int dir){
        this.x = x;
        this.y = y;
        this.mir = mir;
        this.dir = dir;
    }

    @Override
    public int compareTo(P2151 o) {
        return this.mir - o.mir;
    }
}

//PQ를 이용해서 mirror 사용이 적었던 빛부터 나오게 하면 되는 문제
//도착지점에 도착했을 떄 여러 방향에서 여러개의 거울을 써서 도착했을 것이다.
//이때 최소 거울의 개수를 출력하면 되므로 PQ를 이용하는 것
//즉 endX,endY 지점에 거울이 2 3 4 개 가 필요한 점들이 들어올 수 있는데
//최소 갯수를 출력


public class P2151_거울설치 {

    static int n;
    static char[][] map;
    static boolean[][][] visit;         //방문 지점과 어떤 방향에서 방문했는지 저장
    static int[] dx = {0,-1,0,1};       //좌우상하 ,
    static int[] dy = {1,0,-1,0};
    static Queue<Integer> point = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visit = new boolean[n][n][4];
        for(int i = 0 ; i<n ; i++){
            String str = br.readLine() ;
            for(int j = 0 ; j< n ;j ++){
                map[i][j] = str.charAt(j);
                if(map[i][j]=='#'){     //문 위치 저장
                    point.add(i);
                    point.add(j);
                }
            }
        }

        int startX = point.poll();
        int startY = point.poll();
        int endX = point.poll();
        int endY = point.poll();

        BFS(startX,startY,endX,endY);

    }

    //방향은 유지되다가 거울을 설치한 곳이면 위,아래 2곳으로 이동
    //또는 원래 방향으로 이동 총 3가지의 경우가 존재한다.

    static void BFS(int startX , int startY , int endX , int endY){
        PriorityQueue<P2151> pq = new PriorityQueue<>();
        for(int i = 0 ; i<4 ; i++){     //처음 문을 기준으로
            pq.add(new P2151(startX,startY , 0,  i));
        }

        while (!pq.isEmpty()){
            P2151 r = pq.poll();
            int rx = r.x;
            int ry = r.y;
            int rMir =r.mir;
            int rDir = r.dir;
            visit[rx][ry][rDir] = true;     //큐에서 꺼냈으니 방문처리

            if(rx == endX && ry == endY){  //최종지점 만났으면
                System.out.println(rMir);
                return;
            }

            int nx = rx + dx[rDir];
            int ny = ry + dy[rDir];     //가던 방향 방문


            //방문한 곳은 다시 안본다고 해도 된다 -> 왜냐 ? 거울이 최소 개수인것부터 뽑으므로
            //이미 방문한 곳은 거울이 이미 최소 개수일 것
            if(nx>= 0 && nx<n && ny>= 0 && ny< n && map[nx][ny] != '*' && !visit[nx][ny][rDir]){  //범위 안이고 , 방문 안했을 경우
                if(map[nx][ny] == '.'){ //그냥 길을 만났을 때 ,가던방향 진행
                    pq.add(new P2151(nx,ny,rMir,rDir));
                }else{          //거울 설치 할 수 있는곳 만났을 경우 , 3가지 가능
                    //설치해서 방향 바꾸기 or 원래 방향대로 가기
                    //dx,dy를 상 좌 하 우 순으로 움직이게함

                    //거울 이용해서 바뀐 지점 저장(좌 -> 상 , 우 ->하 , 시계방향 90도)
                    int dir = (rDir + 3) % 4;
                    pq.add(new P2151(nx,ny,rMir+1, dir));

                    //거울 이용해서 바뀐 지점 저장(좌 -> 하 , 우 ->상 , 반시계방향 90도)
                    dir = (rDir + 1 ) % 4;
                    pq.add(new P2151(nx,ny,rMir+1 , dir));

                    //거울 이용 안할 수도 있으므로 원래 가던 방향 저장
                    pq.add(new P2151(nx,ny,rMir, rDir));
                }
            }
        }
   }

}
