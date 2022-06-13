package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pos{
    int x;
    int y;
    Pos(int x , int y){
        this.x = x;
        this.y = y;
    }
}

//두 좌표와 두 좌표표의 거리를 저장하는 클래스
class P1774 implements Comparable<P1774>{
    int start;
    int end;
    double dis;
    P1774(int start , int end ,int startX , int startY ,int endX , int endY){
        this.start = start;
        this.end = end;
        this.dis = Math.sqrt(Math.pow(Math.abs(startX - endX), 2) + Math.pow(Math.abs(startY - endY) , 2));
//        this.dis = Math.round(this.dis *100) / 100.0;
    }

    @Override
    public int compareTo(P1774 o) {
        if(this.dis >= o.dis){
            return 1;
        } else{
            return -1;
        }
    }
}

public class P1774_우주신과의교감 {

    static int n,m;
    static int[] parent;
    static Pos[] position;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        position = new Pos[n+1];
        Arrays.fill(parent , -1);
        for(int i = 1 ; i<=n ;i++){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            position[i] = new Pos(X,Y);
        }
        for(int i = 0 ; i<m ;i++){
            st = new StringTokenizer(br.readLine());
            merge(Integer.parseInt(st.nextToken()) , Integer.parseInt(st.nextToken()));
        }
        //몇개의 그룹으로 나뉘어져 있는지 확인
        int group_size = 0;
        for(int i = 1 ;i<=n ;i++){
            if(parent[i] == -1 ) group_size++;
        }

        //MST 구하기
        PriorityQueue<P1774> pq = new PriorityQueue<>();
        for(int i = 1; i<n ;i++){
            for(int j = i+1 ; j<=n ;j++){   //모든 좌표들간의 거리 구하기
                pq.offer(new P1774(i,j,position[i].x , position[i].y , position[j].x , position[j].y));
            }
        }

        double ans = 0;     //만들어지는 거리
        int cnt = 0;        //몇개의 간선이 만들어졌는지
        while (!pq.isEmpty()){
            P1774 r = pq.poll();
            if(find(r.start) != find(r.end)){
                merge(r.start , r.end);
                ans += r.dis;
                cnt++;
                if(cnt == group_size -1 ) break;    //전부다 연결되었으면
            }
        }
        System.out.println(String.format("%.2f", ans));
    }

    static int find(int x){
        if(parent[x] < 0 ) return x;
        return parent[x] = find(parent[x]);
    }

    static void merge(int x , int y){
        x = find(x);
        y = find(y);
        if(x == y) return;
        if(x < y) parent[y] = x;
        else parent[x] = y;
    }
}
