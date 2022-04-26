package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2234_성곽 {

    static int m,n;
    static int[][] map;
    static int[][] visit;
    static int roomSize = 0;               //방의 최대 사이즈
    static int cnt = 1;                    // 방 카운트
    static int max = 0;                     //합쳤을때 최대값
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    static int[] size = new int[2501];      //cnt의 집이 size 만큼 존재한다.
    //왼쪽 1 , 위쪽 2, 오른쪽 4 , 아래쪽 8    // 4비트로 표현가능하다.
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n+1][m+1];
        visit = new int[n+1][m+1];
        for(int i =1  ; i<=n ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j= 1; j<=m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i<=n ;i++){
            for(int j =1 ;j<=m ;j++){
                if(visit[i][j] == 0) {
                    bfs(i,j);
                    cnt++;
                }
            }
        }
        //벽 허물면서 최대 갯수 구하기 , 각 지점에서 사방을 봐서 다른 방이면 합친다 가정 -> 그 때 두 방의 합을 계속 갱신
        for(int i = 1; i<=n ;i++){
            for(int j =1 ; j<=m ;j++){
                int cur = visit[i][j];
                for(int t = 0 ;t <4 ;t++){
                    int nx = i +dx[t];
                    int ny = j +dy[t];
                    if(nx<1 || nx>n || ny<1 || ny>m || cur == visit[nx][ny]) continue;        //범위 넘거나 같은 방이면 넘어감
                    //다른 방일 경우
                    int temp = size[cur] + size[visit[nx][ny]];   //합쳤을때의 방 크기 구함
                    max = Math.max(temp , max);             //  방크기 최대값 갱신
                }
            }
        }

        System.out.println(cnt -1 );
        System.out.println(roomSize);
        System.out.println(max);        //왜 다르지 ?

    }


    static void bfs(int x , int y){
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        q.offer(y);
        visit[x][y] = cnt;
        int room = 0;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            room++;
            for(int i = 0 ; i<4 ;i++){
                if((map[rx][ry] & (1<<i)) != (1<<i)){  //만약 벽이 없으면 ->갈수 있음
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if(nx<1 ||nx>n || ny <1 || ny>m || visit[nx][ny] != 0) continue;        //범위 밖이거나 방문했으면 , 범위가 1~m or 1~n 이다.
                    visit[nx][ny] = cnt;
                    q.add(nx);
                    q.add(ny);
                }
            }
        }

        //갯수 최대 몇개인지.
        size[cnt] = room;
        roomSize = Math.max(roomSize , room);
    }
}
