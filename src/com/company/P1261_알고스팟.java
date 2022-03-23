package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class P1261 implements Comparable<P1261>{
    int x;
    int y;
    int val ;
    P1261(int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(P1261 o) {
        return this.val - o.val;
    }
}

public class P1261_알고스팟 {

    static int n,m;     //start와 end 는 그룹
    static int[][] map;
    static int[][] dist;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dist = new int[n][m];
        for(int i = 0 ; i< n ;i++) {
            //dist[i][j] 가 1,1 에서 i,j까지 가는데 걸리는 거리 -> 즉 최소 가중치이다
            //따라서 구하는 값은 dist[n-1][m-1]이 된다
            Arrays.fill(dist[i], Integer.MAX_VALUE); //dist 배열 셋팅
        }
        for(int i = 0 ; i< n; i++){
            String str = br.readLine();
            for(int j = 0 ; j<m ;j++){
                map[i][j] = str.charAt(j) - '0' ;
            }
        }
        //잘못된 접근
        //1. 방들의 그룹을 나눈다 , 나누면서 시작점과 끝점이 있는 방의 그룹을 체크한다
        //2. 방끼리의 거리를 구한다 -> How? -> 거리 = 벽의 개수 -> 이 방법이 맞을까 ?
        //구한 벽의 개수를 기준으로 시작점에서 끝점까지의 최단거리를 구한다.


        //다른 접근
        //이차원 배열에서 벽이든 방이든 상관없이 이동한다 -> 단 이때 이동한 곳이 벽이면 1의 가중치를 계산해준다
        //예를들어 011일때 0->1 이동하면 (0,0)에서 (0,1)로 이동할때 가중치는 1 , 또 (0,2)로 이동할때 벽이므로 가중치는 1이다
        //가중치는 부순 벽의 갯수이다 . 즉 dist[i][j] 는 (0,0)에서 사방탐색을 하면서 이동하여서
        //도착할 수 있는 가중치의 최소값이다 ->
        //011
        //000
        //위의 경우 (0,0) -> (0,1) -> (0,2) 로가면 2개의 벽을 부셔서 가중치가 2이지만
        //돌아가면 가중치가 1이므로 dist는 1이된다 -> 즉 적은 가중치로 도착할 수있냐가 핵심
        //따라서 다익스트라를 이용한다.
        PriorityQueue<P1261> pq = new PriorityQueue<>();
        dist[0][0] = 0;     //시작지점
        pq.add(new P1261(0,0,0));   //시작지점 셋팅
        while (!pq.isEmpty()){
            P1261 r = pq.poll();
            //visit 체크하면 가중치를 적은걸로 갱신 못함 -> 따라서 방문체크안함
            //사방 탐색으로 이동 -> 사방탐색하는 것이 연결된 노드들이라 생각
            for(int i = 0 ; i<4 ; i++){
                int nx = r.x + dx[i];
                int ny = r.y + dy[i];
                if(nx<0 ||nx>=n ||ny <0 ||ny>=m) continue;
                if(dist[nx][ny] > dist[r.x][r.y] + map[nx][ny]){        // map[nx][ny] 1 or 0
                    dist[nx][ny] = dist[r.x][r.y] + map[nx][ny];
                    pq.add(new P1261(nx,ny , dist[nx][ny]));
                }
            }
        }
        sb.append(dist[n-1][m-1]);
        System.out.println(sb.toString());

    }


}
