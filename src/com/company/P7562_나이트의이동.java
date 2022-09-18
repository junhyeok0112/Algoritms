package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7562_나이트의이동 {

    static int t,n; //테케 , 체스판 한변의 길이
    static int[][] visit;   //방문 했는지 체크
    static int startX, startY , endX,endY;
    static int[] dx = {1,1,-1,-1,2,2,-2,-2};
    static int[] dy = {-2,2,-2,2,1,-1,1,-1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        while (t-- >0){
            n = Integer.parseInt(br.readLine());
            visit = new int[n][n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());
            bfs();
        }
        System.out.println(sb.toString());
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(startX);
        q.add(startY);
        visit[startX][startY] = 1;
        while (!q.isEmpty()){
            int rx = q.poll();
            int ry = q.poll();
            for(int i = 0 ; i<8 ;i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx <0 || nx>= n ||ny <0 || ny>=n ||visit[nx][ny] >0 ) continue;
                visit[nx][ny] = visit[rx][ry] + 1;
                q.add(nx);
                q.add(ny);
            }
        }
        sb.append(visit[endX][endY] - 1).append("\n");
    }
}
