//package com.company;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//class Point{
//    int x;
//    int y;
//    Point(int x, int y){
//        this.x= x;
//        this.y = y;
//    }
//}
//public class P2178_미로탐색 {
//
//    static int N, M ;
//    static int[][] map;
//    static int[][] visit;
//    static int[] dx = {1,-1,0,0};
//    static int[] dy = {0,0,1,-1};
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N][M];
//        visit= new int[N][M];
//        for(int i = 0 ; i<N ;i++){
//            String s = br.readLine();
//            for(int j = 0 ; j<M;j++){
//                map[i][j] = s.charAt(j) - '0';
//            }
//        }
//
//        System.out.println(bfs(0,0));
//
//
//    }
//
//    static int bfs(int x, int y){
//        Queue<Point> q = new LinkedList<>();
//        visit[x][y] = 1;
//        q.add(new Point(x,y));
//        while (!q.isEmpty()){
//            Point r = q.poll();
//            int rx = r.x;
//            int ry = r.y;
//            for(int i = 0 ; i <4; i++){
//                int nx = rx + dx[i];
//                int ny = ry + dy[i];
//                if(nx>= 0 && nx<N && ny >= 0 && ny<M){
//                    if(visit[nx][ny] == 0 && map[nx][ny] == 1){
//                        visit[nx][ny] = visit[rx][ry] + 1;
//                        q.add(new Point(nx,ny));
//                    }
//                }
//            }
//
//
//        }
//
//        return visit[N-1][M-1] ;
//    }
//}
