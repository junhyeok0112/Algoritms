package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P23081_오델로 {

    static int N;
    static char[][] map;
    static int answerX =501  , answerY = 501; //좌표값 ->출력은 Y,X순으로
    static int answerMax = 0;  //몇개 뒤집을 수 있는지
    static Queue<Integer> q = new LinkedList<>();
    static int[] dx = {-1,1,0,0,1,-1,1,-1};
    static int[] dy = {0,0,1,-1,1,1,-1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for(int i = 0 ; i <N ; i++){
            String s = br.readLine();
            for(int j = 0 ; j<N ;j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == '.'){   //비어 있는 곳들 체크
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();
            int cnt = 0;        //개수
            for(int i = 0 ; i<8; i++){  //8방향으로
                int nx = x+dx[i];
                int ny = y+dy[i];
                int temp = 0;
                while(nx>= 0 && nx<N && ny >= 0 && ny<N) {   //범위 내에있을때
                    if (map[nx][ny] == '.') break;   //.이면 멈춤
                    if(map[nx][ny] == 'B'){
                        cnt+=temp;
                        break;
                    }
                    //백을 만났으면
                    temp++; //백을 만난거의 갯수 -> 흑 만나면 cnt에 더해줌
                    nx += dx[i];
                    ny += dy[i];
                }
            }
            if(cnt >answerMax){ //클때
                answerMax = cnt;
                answerX = x;
                answerY = y;
            }else if(cnt == answerMax){//같을때
                if(x<answerX) {  //y가 더작은거 -> 내가 한거는 answerX가 Y좌표에 해당하는 것이므로
                    answerY = y;
                    answerX = x;
                } else if(x == answerX){
                    if(y < answerY){
                        answerY = y;
                        answerX = x;
                    }
                }
            }
        }

        if(answerMax == 0){ //놓을 곳 없으면
            System.out.println("PASS");
        }else {
            System.out.println(answerY+" " + answerX);
            System.out.println(answerMax);
        }

    }
}
