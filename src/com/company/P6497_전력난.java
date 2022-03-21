package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P6497 implements Comparable<P6497> {
    int x ;
    int y;
    int dis;
    P6497(int x , int y , int dis){
        this.x = x;
        this.y = y;
        this.dis = dis;
    }

    @Override
    public int compareTo(P6497 o) {
        return this.dis - o.dis;
    }
}

public class P6497_전력난 {

    static int total,n,m;       //total 은 총 합쳤을때의 값
    static int[] p ;            //Union - find
    static PriorityQueue<P6497> pq ;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if(m == 0 && n == 0) break;
            p = new int[m];
            Arrays.fill(p,-1);
            total = 0;
            pq = new PriorityQueue<>();
            for(int i = 0 ; i<n ;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dis = Integer.parseInt(st.nextToken());
                pq.add(new P6497(x,y,dis));
                total += dis;
            }

            int min_sum = 0;
            int cnt = 0;
            while(!pq.isEmpty()){
                P6497 r = pq.poll();
                if(find(r.x) != find(r.y)){     //다르면
                    merge(r.x,r.y);             //합치고
                    min_sum += r.dis;           //최소 비용 갱신
                    cnt++;
                    if(cnt == m - 1){            //전부 연결되었으면
                        break;
                    }
                }
            }

            System.out.println(total - min_sum);
        }


    }

    static int find(int x){
        if(p[x] <0 ) return x;
        return p[x] = find(p[x]);
    }

    static void merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y) return;
        p[y] = x;               //x,y 부모 다르면 y와 x를 합침 -> 낮은걸로
    }

}
