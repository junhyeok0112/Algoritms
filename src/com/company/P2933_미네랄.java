package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2933_미네랄 {

    static char[][] map;
    static boolean[][] visit;
    static int R,C, N;
    static int[] dx = {0,-1,1,0};   //오위왼아
    static int[] dy = {1,0,0,-1};
    static Queue<Integer> save = new LinkedList<>();//뭉텅이 들을 저장

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for(int i = 0 ; i<R ;i++){
            String s = br.readLine();
            for(int j = 0 ; j<C ;j++){
                map[i][j] = s.charAt(j);
            }
        }
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<N ;i++){
           int level = Integer.parseInt(st.nextToken());
           if(i % 2 == 0){
               shootLeft(R - level);
           } else{
               shootRight(R-level);
           }

        }

        for(int i = 0 ; i<R ; i++){
            for(int j = 0 ; j<C ; j++){
                System.out.print(map[i][j]);
            }
            if(i == R -1) break;
            System.out.println();
        }
    }

    static void shootLeft(int level){   //level은 실제로 map에서 쏴지는 행
        int curX = level;
        //처음만나는 위치 저장시키고 중단
        for(int i = 0 ; i<C ;i++){
            if(map[level][i] == 'x'){   //미네랄이면
                map[level][i] = '.';
                int curY = i;
                Left(curX,curY);
                break;
            }
        }

    }

    static void Left(int curX , int curY){
        //가능한 2개의 단위 찾아서 내릴수 있는지 확인하기
        for(int i = 0 ; i<3; i++){
            visit = new boolean[R][C];
            Queue<Integer> q = new LinkedList<>();
            int rx = curX + dx[i];
            int ry = curY + dy[i];
            if(rx>=0 && rx<R && ry>=0 && ry<C && map[rx][ry] =='x'){//범위 내이고 미네랄이면
                if(!visit[rx][ry]){
                    visit[rx][ry] = true;
                    q.add(rx);
                    q.add(ry);
                    save.add(rx);
                    save.add(ry);
                    while(!q.isEmpty()){
                        rx = q.poll();
                        ry = q.poll();
                        for(int j = 0 ; j<4 ;j++){
                            int nx = rx + dx[j];
                            int ny = ry + dy[j];
                            if(nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny] =='x'){
                                if(!visit[nx][ny]){
                                    visit[nx][ny] = true;
                                    q.add(nx);
                                    q.add(ny);
                                    save.add(nx);
                                    save.add(ny);
                                }
                            }
                        }
                    }
                }
                down();
                save.clear();
            }

        }
    }

    static void shootRight(int level){
        int curX = level;
        //처음만나는 위치 저장시키고 중단
        for(int i = C-1 ; i>=0 ;i--){
            if(map[level][i] == 'x'){   //미네랄이면
                map[level][i] = '.';
                int curY = i;
                Right(curX , curY);
                break;
            }
        }

    }
    static void Right(int curX , int curY){
        //가능한 2개의 단위 찾아서 내릴수 있는지 확인하기
        for(int i = 1 ; i<4; i++){
            visit = new boolean[R][C];
            Queue<Integer> q = new LinkedList<>();
            int rx = curX + dx[i];
            int ry = curY + dy[i];
            if(rx>=0 && rx<R && ry>=0 && ry<C && map[rx][ry] =='x'){//범위 내이고 미네랄이면
                if(!visit[rx][ry]){
                    visit[rx][ry] = true;
                    q.add(rx);
                    q.add(ry);
                    save.add(rx);
                    save.add(ry);
                    while(!q.isEmpty()){
                        rx = q.poll();
                        ry = q.poll();
                        for(int j = 0 ; j<4 ;j++){
                            int nx = rx + dx[j];
                            int ny = ry + dy[j];
                            if(nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny] =='x'){
                                if(!visit[nx][ny]){
                                    visit[nx][ny] = true;
                                    q.add(nx);
                                    q.add(ny);
                                    save.add(nx);
                                    save.add(ny);
                                }
                            }
                        }
                    }
                }
                down();
                save.clear();
            }
        }
    }

    static void down(){ //뭉텅이들이 담겨져있음
        int qsize = save.size()/2;
        int minCnt = 102; //전체를 이만큼 내릴 수 있음
        boolean[][] visittemp = new boolean[R][C];
        boolean[][] visitAfter = new boolean[R][C];
        for(int i = 0 ; i<qsize;i++){
            int x = save.poll();
            int y = save.poll();
            int cnt = 0;
            for(int j = x+1 ; j<R; j++){
                if(map[j][y] == '.') cnt++;
                else if(visit[j][y]){   //만약 같은 클러스트의 크리스탈을 만났으면
                    cnt = 103;          //말도 안되게 초기화
                    break;
                } else{ //크리스탈 만났을 경우
                    break;
                }
            }

            minCnt = Math.min(minCnt, cnt);
            save.add(x);
            save.add(y);
        }
        if(minCnt != 0) {
            while (!save.isEmpty()) {
                int x = save.poll();
                int y = save.poll();
                if(!visittemp[x+minCnt][y]){
                    visittemp[x+minCnt][y] = true;
                    map[x+minCnt][y] = 'x';
                    if(!visittemp[x][y]) {
                        map[x][y] = '.';
                    }
                }
            }
        }

    }
}
