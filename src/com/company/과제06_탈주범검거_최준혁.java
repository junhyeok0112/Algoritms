package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq
//1953_탈주범검거
class P1953{
    int x;
    int y;
    int val;
    int[] dx;
    int[] dy;
    P1953(int x, int y, int val , int[] dx , int[] dy){
        this.x = x;
        this.y = y;
        this.val =val;
        this.dx = dx;
        this.dy = dy;
    }


}


public class 과제06_탈주범검거_최준혁 {

    static int n,m,t,r,c,l,ans;
    static P1953[][] map;
    static int[][] visit;

    //현재 내가 몇일때 , 몇이랑 이어질 수 있냐 !를 파악하는게 제일 중요
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int tc = 0 ;
        while (tc++<t){
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            ans = 0;
            map = new P1953[n][m];
            visit = new int[n][m];
            for(int i = 0 ;i <n ;i++){
                st = new StringTokenizer(br.readLine());
                for(int j= 0; j<m;j++){
                    int val = Integer.parseInt(st.nextToken());
                    if(val == 0){
                        int[] dx = {0};
                        int[] dy = {0};
                        map[i][j] = new P1953(i,j,val,dx,dy);
                    } else if(val == 1){
                        int[] dx = {1,-1,0,0};
                        int[] dy = {0,0,1,-1};
                        map[i][j] = new P1953(i,j,val,dx,dy);
                    }else if(val == 2){
                        int[] dx = {1,-1};
                        int[] dy = {0,0};
                        map[i][j] = new P1953(i,j,val,dx,dy);
                    }else if(val == 3){
                        int[] dx = {0,0};
                        int[] dy = {1,-1};
                        map[i][j] = new P1953(i,j,val,dx,dy);
                    }else if(val == 4){
                        int[] dx = {-1,0};
                        int[] dy = {0,1};
                        map[i][j] = new P1953(i,j,val,dx,dy);
                    }else if(val == 5){
                        int[] dx = {1,0};
                        int[] dy = {0,1};
                        map[i][j] = new P1953(i,j,val,dx,dy);
                    }else if(val == 6){
                        int[] dx = {1,0};
                        int[] dy = {0,-1};
                        map[i][j] = new P1953(i,j,val,dx,dy);
                    }else if(val == 7){
                        int[] dx = {-1,0};
                        int[] dy = {0,-1};
                        map[i][j] = new P1953(i,j,val,dx,dy);
                    }

                }
            }

            bfs(r,c);           //시작지점 셋팅
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(visit[i][j] != 0 && visit[i][j] <=l) ans++;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());

    }

    static void bfs(int x, int y){
        Queue<P1953> q = new LinkedList<>();
        visit[x][y] = 1;
        q.add(map[x][y]);
        while (!q.isEmpty()){
            P1953 r = q.poll();
            int rx = r.x;
            int ry = r.y;
            int dSize= r.dx.length;
            for(int i = 0 ;i<dSize; i++){       //그 지점에서 움직일 수 있는 방향으로
                int nx = rx + r.dx[i];
                int ny = ry + r.dy[i];
                if(nx<0 ||nx>=n ||ny<0 ||ny>=m || visit[nx][ny] !=0 || map[nx][ny].val == 0) continue;
                int tempdx = r.dx[i] * -1;
                int tempdy = r.dy[i] * -1;          //임시적으로 이 방향이 있어야함
                int tempSize = map[nx][ny].dx.length;
                for(int j = 0 ; j<tempSize;j++){
                    if(map[nx][ny].dx[j] == tempdx && map[nx][ny].dy[j] == tempdy){     //반대 방향이 있는거면 연결 가능한거면 연결
                        visit[nx][ny] = visit[rx][ry] + 1;
                        q.add(map[nx][ny]);
                        break;
                    }
                }
            }
        }
    }
}
