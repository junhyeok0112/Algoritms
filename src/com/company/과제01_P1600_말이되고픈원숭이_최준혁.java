package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class P1600{
    int x;
    int y;
    int k;      //말 움직임으로 움직인 횟수
    P1600(int x , int y ,int k){
        this.x= x;
        this.y = y;
        this.k = k;
    }
}


public class 과제01_P1600_말이되고픈원숭이_최준혁 {

    static int w,h,k;
    static int[][] map;
    static int[][][] dp;
    static int[] mdx = {-1,-1,1,1,-2,-2,2,2};
    static int[] mdy = {-2,2,-2,2,-1,1,-1,1};
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        dp = new int[h][w][k+1];      //구하는 값 : dp[h-1][w-1]의 최소값
        for(int i = 0 ; i<h ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<w ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp 배열 초기화
        for(int i = 0 ;i<h; i++){
            for(int j = 0 ; j<w; j++){
                for(int t = 0 ; t<k+1 ;t++){
                    dp[i][j][t] = Integer.MAX_VALUE;
                }
            }
        }

        bfs();
        int ans = Integer.MAX_VALUE;
        for(int i = 0 ;i<=k ;i++){
            ans = Math.min(dp[h-1][w-1][i] , ans);
        }
        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        } else{
            System.out.println(ans);
        }


    }

    static void bfs(){
        Queue<P1600> q = new LinkedList<>();
        q.add(new P1600(0,0,0));
        dp[0][0][0] = 0;    //이동 횟수 초기화 ,(0,0)애 말 움직임으로 0번 이동한 거리
        while (!q.isEmpty()){
            P1600 r = q.poll();
            int rx = r.x;
            int ry = r.y;
            int rk = r.k;
            for(int i =0 ;i<8 ;i++){        //말 움직임
                int nx = rx+ mdx[i];
                int ny = ry + mdy[i];
                int nk = rk + 1;
                if(nk > k) break;       //횟수 초과하면 더이상 말 상태로 갈 수 없어
                if(nx<0 || nx>=h ||ny<0 ||ny >=w ||map[nx][ny] == 1) continue;     //범위 넘어가면 불가 ,장애물 있어도 불가능능
                if(dp[nx][ny][nk] > dp[rx][ry][rk]+1) {
                    dp[nx][ny][nk] = dp[rx][ry][rk] + 1;
                    q.add(new P1600(nx, ny, nk));                                     //nx,ny를 nk번의 말 이동횟수로 갈 수 있는 곳은 원래 있던값 보다 작은 경우만 방문한다 -> 갱신하면서
                }
           }

            for(int i = 0 ; i<4 ; i++){     //일반 사방탐색 => k는 rk로 유지됨
                int nx = rx +dx[i];
                int ny = ry + dy[i];
                if(nx<0 || nx>=h ||ny<0 ||ny >=w ||map[nx][ny] == 1) continue;
                if(dp[nx][ny][rk] > dp[rx][ry][rk]+1){
                    dp[nx][ny][rk] = dp[rx][ry][rk]+1;
                    q.add(new P1600(nx,ny,rk));
                }
            }
        }
    }
}
