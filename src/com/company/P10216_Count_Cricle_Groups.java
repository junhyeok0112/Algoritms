package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Circle{
    int idx ;
    int x;
    int y;
    int r;
    Circle(int idx , int x, int y, int r){
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.r = r;
    }
}


public class P10216_Count_Cricle_Groups {

    static int T,N;
    static int[] p ;
    static ArrayList<Circle> arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            p = new int[N];
            int cnt = N;
            //for(int i = 0 ; i<N ;i++) p[i] = i;
            Arrays.fill(p,-1);
            arr = new ArrayList<>();
            for(int i = 0 ; i<N ;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                arr.add(new Circle(i , x, y, r));
            }
            int arrsize = arr.size();
            for(int i = 0 ; i<arrsize ;i++) {
                Circle cur = arr.get(i);
                for (int j = 0; j < arrsize; j++) {
                    Circle now = arr.get(j);
                    double dis = getDistance(cur.x, cur.y, now.x, now.y);
                    int Rsum = cur.r + now.r;
                    if (dis <= Rsum) {          //거리가 두개의 합보다 작으면거나 같으면 같은 집합
                        if (find(cur.idx) != find(now.idx)) {     //같은 집합이여야하는데 다른 집합이면
                            merge(cur.idx, now.idx);           //합친다
                            cnt--;
                        }
                    }
                }
            }
               System.out.println(cnt);
        }
    }

    //유니온 파인드 -> disjoint-set
    static int find(int x){
        if(p[x]<0)
            return x;
        return p[x] = find(p[x]);       //x의 부모를 p[x]의 부모로 즉 최고 수준 부모
    }

    static void merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y)
            return;
        p[y] = x;
    }

    static double getDistance(int x, int y, int x2 ,int y2){
        double dis ;
        int Xdis = (int)Math.pow(Math.abs(x - x2) , 2);
        int Ydis = (int)Math.pow(Math.abs(y - y2) , 2);
        dis = Math.sqrt(Xdis + Ydis);
        return dis;
    }
}
