package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class P1194 {
    int x;
    int y;
    int key;
    P1194(int x, int y, int key){
        this.x = x;
        this.y = y;
        this.key = key;
    }
}


public class 실습06_달이차오른다가자_최준혁 {

    static int n, m;
    static char[][] map;
    static int[][][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] moon = {0,1,2,3,4,5};
    static int[] key = {0,1,2,3,4,5};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visit = new int[n][m][64];      //64는 비트
        P1194 start = null;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    start = new P1194(i,j,0); //키 없으니 0
                }
            }
        }

        bfs(start);     //첫 탐색


    }


    private static void bfs(P1194 start) {
       Queue<P1194> q = new LinkedList<>();
       q.add(start);
       visit[start.x][start.y][start.key] = 1;  //방문 체크
        while (!q.isEmpty()){
            P1194 r = q.poll();
            int rx = r.x;
            int ry = r.y;
            int rKey = r.key;
            if(map[rx][ry] == '1'){       //종료 조건건
               System.out.println(visit[rx][ry][rKey] - 1);
                return;
            }
            for(int i=  0; i<4 ;i++){
                int nx = rx+ dx[i];
                int ny = ry+ dy[i];
                if(nx<0 ||nx>=n ||ny<0 ||ny>=m ||visit[nx][ny][rKey] != 0 ||map[nx][ny] == '#') continue;
                if(map[nx][ny] == 'A' || map[nx][ny] == 'B' || map[nx][ny] == 'C'||map[nx][ny] == 'D'||map[nx][ny] == 'E'||map[nx][ny] == 'F'){ //방문했는데 문이면
                    //해당 키가 있어야 지나갈 수 있음
                    int num = moon[map[nx][ny] - 'A'];      //0~5까지 숫자
                    if((rKey & 1<<num) == 1<<num) {         //해당 키가 있으면 방문 가능
                        visit[nx][ny][rKey] = visit[rx][ry][rKey] + 1;
                        q.add(new P1194(nx,ny,rKey));
                    }else {
                        continue;   //못가면 그냥 지나감
                    }
                }
                //방문한 곳이 열쇠면
                if(map[nx][ny] == 'a' ||map[nx][ny] == 'b' || map[nx][ny] == 'c' || map[nx][ny] == 'd' || map[nx][ny] == 'e' || map[nx][ny] == 'f' ){
                    int num = key[map[nx][ny]- 'a'];
                    //원래 가지고 있던 key 에 값 을 추가해야함
                    int nKey = (rKey | 1<<num); //새 키 발금
                    visit[nx][ny][nKey] = visit[rx][ry][rKey] + 1;
                    visit[nx][ny][rKey] = visit[rx][ry][rKey] +1;
                    q.add(new P1194(nx,ny,nKey));
                    continue;
                }
                //방문한 곳이 그냥 갈 수 있는 곳일때
                visit[nx][ny][rKey] = visit[rx][ry][rKey] + 1;
                q.add(new P1194(nx,ny,rKey));
            }
        }
        System.out.println(-1);
    }
}
