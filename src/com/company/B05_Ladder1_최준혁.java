package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B05_Ladder1_최준혁 {

    static int tc;
    static int[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = 0;
        while(t++<10){
            tc = Integer.parseInt(br.readLine());
            StringTokenizer st ;
            map = new int[100][100];
            visit = new boolean[100][100];
            int startX = 0;
            int startY = 0;
            for(int i = 0 ; i<100; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0;  j< 100 ;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 2){
                        startX = i;
                        startY = j;
                    }
                }
            }
            System.out.println("#"+tc+" "+solve(startX,startY));

        }

    }

    static int solve(int x, int y){

        while (x>0){            //x 0인 지점 도착하면 끝 ->이떄의 y값 출력
            visit[x][y] = true;
            if(y>0 && map[x][y-1] == 1 && !visit[x][y-1]){       //왼쪽이 사다리면
                y--;
            }
            else if(y<99 && map[x][y+1] == 1&& !visit[x][y+1]){
                y++;
            } else{
                x--;            //한칸 올라감
            }
        }
        return y;
    }


}
