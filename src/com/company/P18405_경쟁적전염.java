package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class P18405 implements Comparable<P18405>{
    int x, y, val;
    P18405(int x , int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(P18405 o) {
        return this.val - o.val;
    }
}


public class P18405_경쟁적전염 {

    static int n,k,s,x,y;
    static int[][] map;
    static boolean[][] visit;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<P18405> pq = new PriorityQueue<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        visit = new boolean[n+1][n+1];
        for(int i = 1 ; i<=n ;i++ ){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j<=n ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0){
                    pq.add(new P18405(i,j,map[i][j]));
                }
            }

        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        if( s == 0){
            sb.append(map[x][y]);
        } else{
            bfs();
            sb.append(map[x][y]);
        }
        System.out.println(sb.toString());

    }

    static void bfs(){
        int time = 0;
        Queue<P18405> q = new LinkedList<>();
        while (!pq.isEmpty()){
            int pqsize = pq.size();
            for(int t = 0 ; t< pqsize; t++){
                P18405 r = pq.poll();
                for(int i = 0 ; i<4 ;i++){
                    int nx = r.x + dx[i];
                    int ny = r.y + dy[i];
                    if(nx <1 || nx>n || ny <1 || ny >n || map[nx][ny] != 0) continue;
                    map[nx][ny] = r.val;    //값 갱신
                    q.add(new P18405(nx,ny,map[nx][ny]));
                }
            }
            time++;
            if(time == s) return;   //시간 다 됐으면 리턴
            int qsize = q.size();
            for(int i = 0 ; i<qsize; i++){
                pq.add(q.poll());
            }
        }
    }

}
