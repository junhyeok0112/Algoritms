package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P11085 implements Comparable<P11085>{
    int x;
    int y;
    int val;
    P11085(int x,int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(P11085 o) {
        return o.val - this.val;
    }
}

public class P11085_군사이동 {

    static int p, w, c, v;
    static int[] par;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        par = new int[p];
        Arrays.fill(par,-1);
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        PriorityQueue<P11085> pq = new PriorityQueue<>();
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            pq.add(new P11085(start, end,val));
        }

        //잘못 접근한 점
        // 경로를 구해야 한다고 생각함 -> dfs로 경로를 구하려했음
        //하지만 경로를 구하는 문제가 아니였다 -> 블로그 참고고

        //구하는게 연결되었을 시에 간선 경로 중 가장 작은 값
        //즉 너비가 넓은 것 부터 연결 시키다가 c와 v가 연결되면 경로가 완성된거고 그떄의 값이 너비가 가장 좁은길의 너비를 최대화한 값입니다.

        //경로들을 우선순위 순으로 정렬한다.
       //정렬된 경로를 하나씩 꺼내서 지점들을 연결한다 , c와 v가 같은 집합이 되면 값을 출력 후 종료한다.
        while (!pq.isEmpty()){
            P11085 r = pq.poll();
            merge(r.x ,r.y);
            if(find(c) == find(v)){       //연결되어서 경로가 생기면
                System.out.println(r.val);  //놓은 다리 너비 출력 후 종료
                return;
            }
        }
    }

    static int find(int x ){
        if(par[x] == -1) return x;
        return par[x] = find(par[x]);
    }

    static boolean merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y ) return true;
        par[y] = x;
        return false;
    }

}

