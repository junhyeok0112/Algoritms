//package com.company;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.PriorityQueue;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//
////class Point{
////    int x;
////    int y;
////    Point(int x, int y){
////        this.x = x;
////        this.y = y;
////    }
////}
//public class P2667_단지번호붙이기 {
//
//    static int N ,cnt;
//    static int[][] map;
//    static int[][] visit;
//    static PriorityQueue<Integer> pq = new PriorityQueue<>();
//    static int[] dx = { -1 , 1 ,0 ,0};
//    static int[] dy = {0, 0, -1 , 1};
//
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        map = new int[N][N];
//        visit = new int[N][N];
//        cnt = 1;
//        for(int i = 0 ; i<N;i++){
//            String s = br.readLine();
//            for(int j = 0 ; j<N ;j++){
//                map[i][j] = s.charAt(j) - '0';  //숫자로 대입
//            }
//        }
//
//        for(int i = 0 ; i<N;i++){
//            for(int j = 0 ; j<N ;j++){
//                if(map[i][j] != 0 && visit[i][j] ==0){  //1이고 방문안했으면
//                    bfs(i,j);
//                }
//            }
//        }
//        System.out.println(cnt-1);
//        int pqsize = pq.size();
//        for(int i = 0 ; i<pqsize;i++){
//            System.out.println(pq.poll());
//        }
//    }
//
//    static void bfs(int x , int y){
//        Queue<Point> q = new LinkedList<>();
//        q.add(new Point(x,y));
//        visit[x][y] = cnt;
//        int count = 1;
//        while(!q.isEmpty()){
//            Point r = q.poll();
//            int rx = r.x;
//            int ry = r.y;
//            for(int k = 0 ; k<4; k++){
//                int nx = rx + dx[k];
//                int ny = ry + dy[k];
//                if(nx>= 0 && nx <N && ny>= 0 &&ny<N){
//                    if(map[nx][ny] == 1 && visit[nx][ny] == 0){
//                        visit[nx][ny] = cnt;
//                        q.add(new Point(nx,ny));
//                        count++;
//                    }
//                }
//            }
//        }
//        pq.add(count);
//        cnt++;
//
//    }
//}
