//package com.company;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//
////class Point{
////    int x;
////    int y;
////    Point(int x , int y){
////        this.x = x;
////        this.y= y;
////    }
////}
//public class P14502_연구소 {
//
//    static int N,M;
//    static int[][] map;
//    static boolean[][] visit;
//    static int max = -1;    //정답
//    static int[] dx = {1 , -1, 0 ,0 };
//    static int[] dy = {0,0,1,-1};
//    static ArrayList<Point> air = new ArrayList<>();    //아무것도 없는 점들을 저장할 리스트 -> 이걸이용해 3중포문으로 벽세움
//    static ArrayList<Point> source = new ArrayList<>(); //바이러스 시작 지점을 저장할 배열
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N+1][M+1];
//        visit = new boolean[N+1][M+1];
//        for(int i = 1; i<=N; i++){
//            st = new StringTokenizer(br.readLine());
//            for(int j =1;j <=M; j++){
//                map[i][j] = Integer.parseInt(st.nextToken());
//                if(map[i][j] == 0){
//                    air.add(new Point(i,j));
//                }
//                if(map[i][j] == 2){
//                    source.add(new Point(i,j));
//                }
//            }
//        }
//
//        //벽 3개 세우기
//        int Wallcnt = 0;
//        int arrsize = air.size();
//        for(int i=0; i<arrsize-2;i++){
//            Point one = air.get(i);
//            int oneX = one.x;
//            int oneY = one.y;
//            map[oneX][oneY] = 1;    //빈지점 벽으로 세우기
//            for(int j = i+1; j<arrsize -1; j++){
//                Point two = air.get(j);
//                int twoX = two.x;
//                int twoY = two.y;
//                map[twoX][twoY] = 1;    //빈지점 벽으로 세우기
//                for(int k = j+1; k<arrsize;k++){
//                    Point three = air.get(k);
//                    int threeX = three.x;
//                    int threeY = three.y;
//                    map[threeX][threeY] = 1;    //빈지점 벽으로 세우기    (3번째 벽까지 세운 경우)
//                    for(int t = 0 ;t<source.size();t++){
//                        Point f = source.get(t);
//                        bfs(f.x, f.y);  //bfs 실행
//                    }
//                    find();
//                    //visit배열 초기화
//                    for(boolean[] a : visit) {
//                        Arrays.fill(a, false);   //다음을 위해 2차언 배열인 visit 초기화
//                    }
//                    map[threeX][threeY] = 0;
//                }
//                map[twoX][twoY] = 0;
//            }
//            map[oneX][oneY] = 0;
//        }
//
//        System.out.println(max);
//    }
//
//    static void bfs(int x, int y){ //체크하는 함수
//        Queue<Point> q = new LinkedList<>();
//        visit[x][y] = true;
//        q.add(new Point(x,y));
//        while(!q.isEmpty()){
//            Point r = q.poll();
//            int rx = r.x;
//            int ry = r.y;
//            for(int i =0 ; i<4;i++){
//                int nx = rx + dx[i];
//                int ny = ry + dy[i];
//                if(nx>0 && nx<=N && ny >0 && ny<=M){    //범위안이면
//                    if(!visit[nx][ny] && map[nx][ny] == 0){ //방문 안하고 벽이 아니면
//                        visit[nx][ny] = true;
//                        q.add(new Point(nx,ny));
//                    }
//                }
//            }
//        }
//    }
//
//    static void find(){
//        int cnt = 0 ;
//        for(int i = 1; i<=N;i++){
//            for(int j =1 ;j<=M;j++){
//                //바이러스가 방문안한곳이 안전구역
//                if(!visit[i][j] && map[i][j] == 0) cnt++;
//            }
//        }
//        max = Math.max(max, cnt);
//    }
//}
