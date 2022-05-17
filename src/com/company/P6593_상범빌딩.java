package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P6593_상범빌딩 {

    static int l,r,c;
    static StringBuilder sb = new StringBuilder();
    static char[][][] map;
    static int[][][] visit;
    static int[] dx = {1,-1,0,0,0,0};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {0,0,0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        while (l !=0 && r != 0 && c != 0){  //하나라도 0이면 멈춤
            map = new char[l][r][c];
            visit = new int[l][r][c];
            int startX= 0 , startY = 0 ,startZ= 0;
            int endX=0 ,endY = 0, endZ= 0;
            for(int t = 0 ; t<l ;t++){
                for(int i = 0 ;i<r; i++){
                    String str = br.readLine();
                    for(int j =0 ; j<c; j++){
                        map[t][i][j] = str.charAt(j);
                        if(map[t][i][j] =='S'){
                            startX =t;
                            startY= i;
                            startZ=j;
                        } else if(map[t][i][j] == 'E'){
                            endX =t;
                            endY= i;
                            endZ=j;
                        }
                    }
                }
                br.readLine();      //빈줄 제거
            }
            bfs(startX,startY,startZ);
            if(visit[endX][endY][endZ] != 0){
                sb.append("Escaped in "+ (visit[endX][endY][endZ]-1)+" minute(s).").append("\n");
            } else{
                sb.append("Trapped!").append("\n");
            }
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb.toString());
    }

    static void bfs(int x, int y, int z){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        q.add(z);
        visit[x][y][z] = 1;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            int rz = q.poll();
            for(int i = 0 ; i<6 ;i++){
                int nx = rx +dx[i];
                int ny = ry + dy[i];
                int nz = rz + dz[i];
                if(nx<0 ||nx>=l ||ny<0 ||ny>=r ||nz<0 ||nz>=c || visit[nx][ny][nz] != 0 || map[nx][ny][nz] == '#' ) continue;
                q.add(nx);
                q.add(ny);
                q.add(nz);
                visit[nx][ny][nz] = visit[rx][ry][rz] + 1;
            }
        }
    }
}
