package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class P5213{
    int r,c;
    //각 노드가 자신이 지나온 경로를 모두 가지고 있게함
    ArrayList<Integer> path;

    P5213(int r, int c ,ArrayList<Integer> path){
        this.r = r;
        this.c = c;
        this.path = path;
    }

    void add(int i){
        this.path.add(i);
    }
}

public class P5213_과외맨 {

    static int n;
    static int[][] map;
    static int[][] tileMap;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][2*n+1];
        tileMap = new int[n+1][2*n+1];
        visit = new boolean[n+1][2*n+1];
        StringTokenizer st ;

        int tile = n;
        int tileNum = 1;
        int c = 1;                      //홀수일때는 1부터 짝수일때는 2부터 시작하기 위해서
        for(int r = 1; r<=n; r++){       //n개의 줄
            if(r % 2 == 1){             //홀수 줄이면
                tile = n;
                c= 1;
            } else{
                tile = n-1;
                c =2 ;
            }

            //조각(map) , 타일(tileMap) 순으로 입력받는다 .  r로 홀 짝 구분
            //c로 열 구분
            for(int i = 0 ; i<tile ;i++){
                st = new StringTokenizer(br.readLine());
                map[r][c] = Integer.parseInt(st.nextToken());
                tileMap[r][c++] = tileNum;
                map[r][c] = Integer.parseInt(st.nextToken());
                tileMap[r][c++] = tileNum++;        //tileMap은 2개가 한쌍으로 값이 같다.
            }

        }

        P5213 ans = bfs();
        System.out.println(ans.path.size());
        for(int num : ans.path){
            System.out.print(num +" ");
        }

    }

    static P5213 bfs(){
        Queue<P5213> q = new LinkedList<>();
        ArrayList<Integer> path = new ArrayList<>();
        path.add(1);
        visit[1][1] = true;     //시작지점은 (1,1)로 정해져있고 한 타일에 2개의 조각이므로 q 에 2개씩 넣어서 진행한다.
        visit[1][2] = true;     //2개씩 넣는게 중요 !조각별로 탐색하면 최단거리가 타일로 탐색할때와 달라질 수 있다.
        q.add(new P5213(1,1,path));
        q.add(new P5213(1,2,path));
        P5213 ans = null;
        int max = 0;            //타일번호가 가장 큰 것을 저장
        while (!q.isEmpty()){
            P5213 r = q.poll();
            int rx = r.r;
            int ry = r.c;

            //타일번호가 가장 큰 것을 계속 갱신 -> 타일번호가 가장 큰것이 정답 !
            if(max < tileMap[rx][ry]){
                max = tileMap[rx][ry];
                ans = r;
            }

            for(int i = 0 ; i< 4; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                //같은 타일에 있는 조각의 방문은 이미 방문처리되어서 건너 뜀
                if(nx<1 || nx> n || ny<1 || ny>2*n || map[nx][ny] == 0 || visit[nx][ny]) continue;

                if(map[rx][ry] == map[nx][ny]){     //같은 값이라 넘어갈 수 있을 경우
                    path = new ArrayList<>();       //여태 지나온 타일들 갱신
                    path.addAll(r.path);
                    path.add(tileMap[nx][ny]);

                    //방문한 타일 방문체크 후 큐에 넣기
                    visit[nx][ny] = true;
                    q.add(new P5213(nx,ny ,path));

                    //방문한 타일의 다른 족각 찾아서 큐에 넣기 방문 체크 후
                    if(tileMap[nx][ny+1] == tileMap[nx][ny]){
                        visit[nx][ny+1] = true;
                        q.add(new P5213(nx,ny+1 , path));
                    } else if(tileMap[nx][ny-1] == tileMap[nx][ny]){
                        visit[nx][ny-1] = true;
                        q.add(new P5213(nx,ny-1 , path));
                    }
                }
            }
        }
        return  ans;
    }


}
