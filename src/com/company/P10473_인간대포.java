package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P10473 implements Comparable<P10473>{
    int y;
    double val;
    P10473(int y , double val){
        this.y =y;
        this.val = val;
    }

    @Override
    public int compareTo(P10473 o) {
        if(this.val >= o.val){
            return 1 ;
        } else{
            return -1;
        }
    }
}

class Place{
    double x;
    double y;
    Place(double x , double y){
        this.x = x;
        this.y = y;
    }
}


public class P10473_인간대포 {

    static int n;
    //시작 지점이 0 , 도착지점이 n+1 지점
    static ArrayList<P10473>[] arr;
    static ArrayList<Place> places = new ArrayList<>();


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        Place start = new Place(x,y);
        st = new StringTokenizer(br.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        Place end = new Place(x,y);
        n = Integer.parseInt(br.readLine());    //대포 개수
        arr = new ArrayList[n+2];
        for(int i = 0 ; i<= n+1; i++) arr[i] = new ArrayList<>();   //대포들끼리의 거리 저장
        places.add(start);      //대포들 위치 저장
        for(int i = 0 ; i < n;i++){
            st = new StringTokenizer(br.readLine());
            double rx = Double.parseDouble(st.nextToken());
            double ry = Double.parseDouble(st.nextToken());
            places.add(new Place(rx,ry));
        }
        places.add(end);            //대포들 위치 저장 끝

        //각 대포들끼리의 거리 구해야함
        for(int i = 0 ; i<n+1; i++){
            for(int j = i+1 ; j< n+2 ;j++){     //시간을 기준으로 정렬한다면 ?
                double dis = Math.sqrt(Math.pow((places.get(j).x - places.get(i).x), 2)+Math.pow((places.get(j).y - places.get(i).y) , 2));
                double time = 0;
                if(i == 0 ){
                    time =  dis/5;
                    arr[i].add(new P10473(j ,time));
                } else if ( j == n+1){  //목적지가 도착점이면
                    time = Math.min((dis/5) , 2+Math.abs(dis-50)/5 );     //걸어가는 것과 대포 이용해서 가는 것 중 작은거
                    arr[i].add(new P10473(j ,time));
                }else{          //대포끼리면
                    time = Math.min((dis/5) , 2+Math.abs(dis-50)/5 );     //걸어가는 것과 대포 이용해서 가는 것 중 작은거
                    arr[j].add(new P10473(i ,time));
                    arr[i].add(new P10473(j,time));
                }
            }
        }

        double[] dist = new double[n+2];    //길이를 저장할 배열 선언
        Arrays.fill(dist , Double.MAX_VALUE);
        dist[0] = 0;                        //시작값 초기화
        boolean[] visit = new boolean[n+2];
        PriorityQueue<P10473> pq = new PriorityQueue<>();
        pq.add(new P10473(0,0));        //시작 지점이 무조건 0
        while (!pq.isEmpty()){
            P10473 cur = pq.poll();
            if(visit[cur.y]) continue;
            visit[cur.y] = true;
            for(P10473 next : arr[cur.y]){
                if(dist[next.y] > dist[cur.y] + next.val){
                    dist[next.y] = dist[cur.y] + next.val;
                    pq.add(new P10473(next.y, dist[next.y]));
                }
            }
        }

        //dist[n+1]에 실제 걸리는 시간
        System.out.println(String.format("%.6f", dist[n+1]));
    }
}
