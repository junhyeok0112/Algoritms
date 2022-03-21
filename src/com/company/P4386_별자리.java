package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point_P4386{
    double x;
    double y;
    int idx;
    Point_P4386(double x, double y , int idx){
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
}

class P4386 implements Comparable<P4386> {
    Point_P4386 start;
    Point_P4386 end;
    double dis;
    P4386(Point_P4386 start , Point_P4386 end , double dis){
        this.start = start;
        this.end = end;
        this.dis = dis;
    }

    @Override
    public int compareTo(P4386 o) {
        if(this.dis - o.dis > 0 ){
            return 1;
        } else {
            return -1;
        }
    }
}



public class P4386_별자리 {

    static int n;
    static ArrayList<Point_P4386> point = new ArrayList<>();
    static int[] p;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[n];
        Arrays.fill(p , -1 );
        StringTokenizer st;
        for(int i = 0 ; i< n; i++){
            double x,y;
            st = new StringTokenizer(br.readLine());
            x = Double.parseDouble(st.nextToken());
            y = Double.parseDouble(st.nextToken());
            point.add(new Point_P4386(x,y,i));            //좌표 모두 저장 , index 로 어떤 별인지 구분
        }

        PriorityQueue<P4386> pq = new PriorityQueue<>();
        for(int i = 0 ; i<n-1 ;i++){
            for(int j = i+1 ; j< n; j++){
                Point_P4386 start = point.get(i);
                Point_P4386 end = point.get(j);
                double dif = Math.sqrt(Math.pow(start.x - end.x , 2) + Math.pow(start.y - end.y , 2));
                pq.add(new P4386(start, end , dif));
            }
        }

        int cnt = 0;
        double ans = 0;
        while (!pq.isEmpty()){
            P4386 r = pq.poll();
            int rx = r.start.idx;
            int ry = r.end.idx;
            if(find(rx) != find(ry)){
                merge(rx,ry);       //다르면 합침
                ans += r.dis;       //비용갱신
                cnt++;
                if(cnt == n -1) break;

            }
        }

        System.out.println(ans);

    }

    static int find(int x ){
        if(p[x] < 0) return x;
        return p[x] = find(p[x]);
    }

    static void merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y ) return;
        p[y] = x;
    }

}
