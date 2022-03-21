package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Bak{
    int x;
    int y;
    int num;
    Bak(int x , int y, int num){
        this.x =x;
        this.y= y;
        this.num = num;
    }
}

public class P3197_백조의호수 {

    static int R,C;
    static char[][] map;
    static int[][] visit;       //물은 퍼지기만 하면되므로 Visit 체크 안해도됨 -> 빙하인지 확인하는거에서 다 걸러짐
    static int[] swan = new int[4];
    static Queue<Integer> Wq = new LinkedList<>();
    static Queue<Integer> Bq = new LinkedList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean chk = false;
    static int cnt = 0;

    //둘이 이어지는지 확인만 하면되므로 한마리가 다른 한마리에 BFS로 닿았을 경우를 체크해주면 됩니다.
    //또 한 백조가 있는 곳도 물입니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visit = new int[R][C];
        int swanIndex = 0;
        for(int i = 0 ; i<R; i++){
            String s = br.readLine();
            for(int j = 0 ; j<C ;j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'L'){
                    Wq.add(i);  //백조가 있는 위치도 물이므로 물 큐에 넣음
                    Wq.add(j);
                    swan[swanIndex++] = i;
                    swan[swanIndex++] = j;
                } else if(map[i][j] =='.'){
                    Wq.add(i);
                    Wq.add(j);
                }
            }
        }

        Bq.add(swan[0]);
        Bq.add(swan[1]);
        visit[swan[0]][swan[1]] = 1;        //처음 백조위치 체크
        //접근 방식 -> 백조를 최대한 가게 하고 빙하를 만나면 못간다 -> 이때 빙하만난지점들은 다음 날에 물에 의해서 없어진다( 왜냐 ? 백조가 있는 곳도 물이므로 물이 닿아있어서)
        //따라서 이 빙하들 위치를 nextQ에 저장해두고 하루가 지나면 이 nextQ를 BQ로 바꾸어서 다시 그 위치부터 탐색을 시작하면된다.
        while (true){
            Queue<Integer> nextq = new LinkedList<>();
            boolean meet = false;
            while (!Bq.isEmpty()) {       //백조들 위치 넓혀감 -> 빙하만날떄까지
                int rx = Bq.poll();
                int ry = Bq.poll();
                if (rx == swan[2] && ry == swan[3]) {
                    chk = true;      //while문 탈출조건
                    break;   //만났으면 만나는데 걸렸던 일수 리턴
                }
                for (int i = 0; i < 4; i++) {
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        if (visit[nx][ny] == 0) {
                            visit[nx][ny] = 1;
                            if (map[nx][ny] == 'X') {     //만약 빙하를 만났으면 그 위치를 다음에 탐색하기 위해 저장
                                nextq.add(nx);
                                nextq.add(ny);
                                continue;
                            }
                            Bq.add(nx);
                            Bq.add(ny);
                        }
                    }
                }
            }
            if(chk) break;
            Bq = nextq;         //다음에 백조들 위치 저장 이때부터 다시 BFS 실행
            //물 위치 갱신해주기 -> 이 때 while로 전부 갱신해주는게 아니라 하루동안 녹이는 것만 계산
            //따라서 Wq에 있는 사이즈만큼만
            int wqsize = Wq.size() / 2;
            for (int t = 0; t < wqsize; t++) {
                int rx = Wq.poll();
                int ry = Wq.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        if (map[nx][ny] == 'X') {
                            map[nx][ny] = '.';
                            Wq.add(nx);
                            Wq.add(ny);
                        }
                    }
                }
            }
            cnt++;

        }
        System.out.println(cnt);
    }


}
