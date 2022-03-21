package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P15686_치킨배달 {
    //bfs문제가 아닌 백트래킹 문제
    //M개의 치킨집 조합을 고른뒤 각 집에서 M개의 치킨집 거리를 구함 -> 그러면 한 집마다 m개의 거리가 나오는데 이중 최소값이 치킨 거리
    //치킨거리들의 합들의 최소가 답이다
    static class Point{
        int x,y, dir;
        Point(int x ,int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N,M;
    static int[][] map;
    static ArrayList<Point> chicken = new ArrayList<>();
    static ArrayList<Point> house = new ArrayList<>();
    static boolean[] ch;
    static int min = Integer.MAX_VALUE;

    static void ref_function(int idx , int cnt){
        if(cnt == M ){  //M개 골랐을경우 0~ M-1개 고르고 M번째 들왔을경우 처리
            int total = 0;
            for(Point r : house){
                int rx = r.x;
                int ry = r.y;
                int temp = Integer.MAX_VALUE; //최소거리를 구하기 위한 임시변수
                for(int i = 0 ; i<chicken.size();i++){
                    if(ch[i])  {    //모든 치킨집 보는데 선택한 치킨 집이면
                        int dis = Math.abs(r.x -chicken.get(i).x ) + Math.abs(r.y - chicken.get(i).y);
                       // System.out.println(dis);
                        //i번째 치킨집과 거리 구하기
                        temp = Math.min(temp, dis);
                    }
                }
                total += temp;
            }
            min = Math.min(total,min);  //최종 결과를 여태까지 min과 비교해서 갱신
        } else{
            for(int i = idx ; i<chicken.size();i++){    //백트래킹을 치킨집 M 개의 조합 구하기
                ch[i] = true;
                ref_function(i+1, cnt+1);   //지금 본거의 다음거부터 봐야함
                ch[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];

        for(int i = 1 ; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N ;j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 1){
                    house.add(new Point(i,j));
                } else if(num == 2){
                    chicken.add(new Point(i,j));
                }
            }
        }
        //ystem.out.println(chicken.size());
        ch = new boolean[chicken.size()];  //백트래킹하면서 index에 맞는 치킨 집 고름
        ref_function(0,0);
        System.out.println(min);
    }
}
