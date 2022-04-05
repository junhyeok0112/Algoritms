package com.company;
//5656. [모의 SW 역량테스트] 벽돌 깨기


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 실습09_벽돌깨기_re_최준혁 {
    static int n,w,h,min;   //map[h][w]로 구현
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[] select;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=TC ; tc++ ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new int[h][w];            //지도
            select = new int[n+1];            //n번째 구슬을 몇번째 열에 던질지
            for(int i = 0 ; i<h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j =0 ;j<w;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            min = Integer.MAX_VALUE;


            System.out.println("#"+tc+" "+min);

        }
    }
    static void rec(int cnt){  //중복순열을 이용하여 구슬 던지기


        //n번까지 전부 던졌을 경우 종료
        if(cnt == n){
            //구슬 던져서 벽돌꺠기

        }
        //0~ w-1열까지 구슬 던져보기
        for(int y = 0 ; y<w; y++){
            //구슬에 맞는 벽돌 찾기
            int x = 0;
            while (x<h &&map[x][y] == 0) ++x;       //빈공간이면 계속해서 아래로
            //해당열은 벽돌이 없음
            if(x==h) continue;      //벽돌이 있는 곳에먼저 구글 던질거야

            bfs(x,y);               //해당 벽돌이 있는 위치에 구슬 던져서 벽돌 깨기

//            select[cnt] = i;
//            rec(cnt+1);
//            //중간에 벽돌 0개되면 굳이 더 안던져도됨 -> 따라서 여기서 체크하는 조건 추가하면 기저조건 따로 필요없음?
//            select[cnt] = 0;
        }
    }

    static void bfs(int x, int y){      //x,y위치에서 가능한 벽돌 깨기
        Queue<P5656> q = new LinkedList<>();
        boolean[][] visit = new boolean[h][w];
        q.add(new P5656(x,y,map[x][y]));
        visit[x][y] = true;
        while (!q.isEmpty()){
            P5656 r = q.poll();
            int rx = r.x;
            int ry = r.y;
            int rVal = r.val - 1;       //x,y 에서 터트려야 하는 갯수 해당 숫자 -1 만큼 써야하므로
            for(int i =0; i<4; i++){
                int nx = rx;
                int ny = ry;
                //while ()
            }
        }
    }

    static void down(){                 //떠있는 벽돌 내리기

    }

    static void getCount(){             //벽돌 갯수 세기

    }

    static void copy(){                 //하나의 배열로 할 수 없으므로 배열 복사해놔야함

    }
}
