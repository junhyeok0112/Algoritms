package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A15_배열돌리기1_최준혁 {

    static int n,m,r;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    //반시계 방향으로 회전
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for(int i = 0 ; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<m ;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int level = Math.min(n,m) / 2;                  //한번 돌리는데 반복해야하는 횟수
        for(int i=0; i<r;i++){

            for(int j=0 ; j<level;j++){                 //항상 0,0 위치에서 시작한다 -> 즉 j 조그만 사각형의 왼쪽위에서 시작한다고 생각
                int x = j;
                int y = j;
                //반시계 방향으로보면 값이 계속 덮여쓰여진다 . 따라서 돌리려는 방향과 반대 방향으로봐야한다.
                int idx = 0;
                int val = arr[j][j];
                while (idx <4){
                    int nx = x + dx[idx];
                    int ny = y + dy[idx];
                    if(nx>=j && ny >=j && nx <n-j && ny <m-j){      //범위 안이면. 값 옮겨주고 x,y값 갱신
                        arr[x][y] = arr[nx][ny];                    //이동한 곳 값을 시작지점으로 땡겨와서 덮어씀 -> 이렇게 해야 값들이 안 덮힘
                        x = nx;                                     //다음 칸으로 이동
                        y = ny;
                    }else{
                        idx++;                                      //범위 넘어가면 idx 증가시켜서 방향전환
                    }
                }
                //처음 시작점 값을 저장해두지 않으면 한바퀴 다 돌았을때 시작점 값이 이동할 위치에 다른 값이 들어가있어서 정답이 다르다.  따라서 시작점값을 저장해두고 , 그 아래로 이동하게해야함
                //이때 while문은 시작점에 도착했을때 끝나므로 한행 밑에 값을 시작점으로 갱신하면된다.
                arr[j+1][j] = val;
            }
        }

        for(int i = 0 ; i<n;i++){
            for(int j= 0; j<m; j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

}
