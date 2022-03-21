package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C08_오셀로게임_최준혁 {

    static int t,n,m;
    static int[][] map;
    static int[] dx = {-1,1,0,0,1,1,-1,-1};
    static int[] dy = {0,0,1,-1,1,-1,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        int tc = 0;
        while(tc++ < t){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n =Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new int[n+1][n+1];
            //초반 4개 깔아두기 1이 흑돌, 2가 백돌
            map[(n/2)][(n/2)] = 2;
            map[(n/2)][(n/2)+1] = 1;
            map[(n/2)+1][(n/2)] = 1;
            map[(n/2)+1][(n/2)+1] = 2;

            for(int i = 0 ; i<m ;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                map[y][x] = color;
                //놓고 8방향으로 다른색 돌이 있는지 확인
                for(int j = 0 ; j< 8; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if(nx<1 || nx>n || ny <1 || ny> n || map[ny][nx] ==0 || map[ny][nx] == color ) continue;
                    boolean chk = true;
                    while(map[ny][nx] != color){		//색 바꾸는 과정을 다르게 해야
                        nx += dx[j];
                        ny += dy[j];
                        if(nx<1 || nx> n ||ny<1 ||ny > n || map[ny][nx] == 0) {
                            chk = false;
                            break;
                        }
                    }

                    if(chk){
                        nx -= dx[j];
                        ny -= dy[j];
                        while(map[ny][nx] != color){		//색 바꾸는 과정을 다르게 해야
                            map[ny][nx] = color;
                            nx -= dx[j];
                            ny -= dy[j];
                        }
                    }
                }
            }

            int black = 0 , white = 0;
            for(int i = 1 ; i<=n ;i++){
                for(int j =1 ; j<=n ;j++){
                    if(map[i][j] == 1 )black++;
                    if(map[i][j] == 2 ) white++;
                }
            }
            System.out.println("#"+tc+" " + black+" "+white);
        }

    }



}
