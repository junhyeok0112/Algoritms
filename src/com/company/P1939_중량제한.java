package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Bridge implements Comparable<Bridge> {
    int a;
    int b;
    int c;
    Bridge(int a , int b , int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(Bridge p1) {
        if(this.c > p1.c){
            return -1;
        }else{
            return 1;
        }
    }
}


//disjoint 배열을 2개 선언
public class P1939_중량제한 {

    static int N,M ,start ,end;
    static ArrayList<Bridge> adj = new ArrayList<>() ;
    static PriorityQueue<Bridge> pq = new PriorityQueue<>();
    static int[] p1 = new int[10001];
    static int[] p2 = new int[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Arrays.fill(p1,-1);
        Arrays.fill(p2 ,-1);
        for(int i = 0 ; i<M ;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            adj.add(new Bridge(num1 ,num2 ,dis));
            pq.add(new Bridge(num1, num2, dis));
        }


        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        // 집합 나누기
        for(Bridge cur : adj){
            if(find(p1, cur.a) == find(p1,cur.b)) continue;
            merge(p1, cur.a, cur.b);
        }

        //3이 root니까 -1
        //System.out.println(p1[1]+ " " + p1[3]);
        //pq는 poll할때 재정렬하므로 꺼내면서 써야한다.
        int pqsize =pq.size();
        for(int i = 0 ; i<pqsize; i++){
            Bridge cur = pq.poll();
            if(find(p1,cur.a) != find(p1,start)) continue;    //같은 집합이 아니면 넘김
            if(find(p1,cur.b) != find(p1,start)) continue;
            if(find(p2, cur.a) != find(p2,cur.b)){  //다른 집합이면 합쳐
                merge(p2, cur.a, cur.b);
                if(find(p2,start) == find(p2,end)){           //합쳤을 때 같으면 연결된 거 이 중 최소값 즉 지금 값을 출력
                    System.out.println(cur.c);
                    return;
                }
            }
        }

    }
    static int find(int[] p , int x ){
        if(p[x] < 0 )
            return x;
        return p[x] = find( p ,p[x] );
    }

    static void merge(int[] p ,int x , int y){
        x = find(p,x);
        y = find(p,y);
        if(x == y)
            return;
        p[y] = x;
    }



}
