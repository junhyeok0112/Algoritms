package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


class P16954{
    int x;
    int y;
    P16954(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class P16954_움직이는미로탈출 {

    static char[][] map = new char[8][8];
    static int[][] visit = new int[0][0];
    static int[] dx = {-1,1,0,0,1,1,-1,-1,0};       //좌우 , 대각선까지
    static int[] dy = {0,0,1,-1,1,-1,1,-1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0 ; i<8 ;i++){
            String str = br.readLine();
            for(int j = 0 ; j<8 ; j++){
                map[i][j] = str.charAt(j);
            }
        }

        System.out.println(bfs());

    }

    static int bfs(){
        Queue<P16954> q = new LinkedList<>();
        q.add(new P16954(7,0));     //시작 지점 셋팅 //시작지점 체크  , 갔는데 맨 밑에 점이면 안됨
        while (!q.isEmpty()){
            int qsize = q.size();
            for(int t = 0 ; t<qsize; t++){      //1초 시간 지났을때
                P16954 r = q.poll();
                int rx = r.x;
                int ry = r.y;
                if(rx == 0 && ry ==7){      //도착지점 갔을 때
                    return 1;
                }
                for(int i = 0 ; i< 9 ; i++){
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if(nx >=0 && nx< 8 && ny>=0 && ny<8 && map[nx][ny] != '#'){       //범위안이고 벽이 아닐경우
                        if(nx-1>=0 && map[nx-1][ny] != '#'){
                            q.add(new P16954(nx,ny));   //움직였는데 끝지점이 아닐경우
                        }
                        if(nx == 0){                    //맨 위에 도착했을경우
                            q.add(new P16954(nx,ny));
                        }
                    }
                }
            }
            moveWall();
        }

        return 0;
    }


    static void moveWall(){
        for(int i = 7; i>= 1 ; i--){
            for(int j = 0 ; j<8 ; j++){
                map[i][j] = map[i-1][j];    //밑으로 내리기
            }
        }

        for(int i = 0 ; i< 8 ; i++){        //맨 위에 .으로 채우기
            map[0][i] = '.';
        }
    }


}
