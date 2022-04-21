package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class P14502_re{
    int x;
    int y;
    P14502_re(int x, int y ){
        this.x = x;
        this.y = y;
    }
}

public class 실습14_연구소_최준혁 {

    static int n,m;
    static int[][] map;
    static ArrayList<P14502_re> zero = new ArrayList<>();
    static ArrayList<P14502_re> virus = new ArrayList<>();
    static int zeroCnt = 0;
    static boolean[][] visit ;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int ans = -1;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0 ;i<n ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {            //0위치 저장 ->벽으로 쓸 수 있음   ->이거를 3중 for문으로 벽세우는거
                    zero.add(new P14502_re(i,j));
                    zeroCnt++;
                } else if(map[i][j] == 2){
                    virus.add(new P14502_re(i,j));      //바이러스 위치 저장
                }
            }
        }

        int firstZero = zeroCnt;                //처음 zeroCnt 유지
        int zeroSize = zero.size();
        for(int i = 0 ; i < zeroSize - 2; i++){
            int firstX = zero.get(i).x;
            int firstY = zero.get(i).y;
            map[firstX][firstY] = 1;            //벽세우기
            for(int j = i+1 ; j<zeroSize-1; j++){
                int secondX = zero.get(j).x;
                int secondY = zero.get(j).y;
                map[secondX][secondY] = 1;
                for(int k = j+1 ; k<zeroSize; k++){
                    int thirdX = zero.get(k).x;
                    int thirdY = zero.get(k).y;
                    map[thirdX][thirdY] = 1;
                    visit = new boolean[n][m];
                    zeroCnt-=3;
                    int virusSize = virus.size();
                    for(int t = 0 ;t <virusSize;t++){
                        if(!visit[virus.get(t).x][virus.get(t).y]){
                            bfs(virus.get(t).x , virus.get(t).y);
                        }
                    }
                    ans = Math.max(ans, zeroCnt);
                    zeroCnt = firstZero;
                    map[thirdX][thirdY] = 0;
                }
                map[secondX][secondY] = 0;
            }
            map[firstX][firstY] = 0;
        }

        System.out.println(ans);
    }


    static void bfs(int x, int y){
        Queue<Integer> q = new LinkedList<>();
        visit[x][y] = true;
        q.add(x);
        q.add(y);
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i =0 ;i<4; i++){
                int nx = rx +dx[i];
                int ny = ry + dy[i];
                if(nx<0 ||nx>=n ||ny<0 ||ny>=m ||visit[nx][ny] || map[nx][ny] != 0) continue;
                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
                zeroCnt--;
            }
        }
    }

}
