package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P1931 implements Comparable<P1931> {
    int start;
    int end;
    P1931(int start ,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(P1931 o) {
        //끝나는 시간 순으로 정렬
        if(this.end < o.end) return -1;
        else if(this.end > o.end) return 1;
        else{
            return this.start - o.start;        //종료시간이 같을경우 시작시간이 빠른걸로 셋팅
            //종료시간이 같을 경우 시작 시간 순으로 정렬해야하는 이유
            //(8,8)같이 시작과 종료시간이 같은 것때문이다.
            //예를들어 (1,3) ,(4,8) ,(8,8)이 있으면
            //(1,3) (8,8) (4,8) 순으로 정렬되었다면
            //(1,3) (8,8)을 고른 후 (4,8)을 못고른다 하지만 이것은 오답이므로
            //시작 시간을 기준으로 한번 더 정렬해야한다.
        }
    }
}

public class P1931_회의실배정 {

    static int n;
    static PriorityQueue<P1931> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        for(int i = 0 ; i< n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new P1931(start,end));
        }
        //작은 것들부터 채워넣으면 될 것 같은데 ...
        //종료시간이 짧은 것들 부터 보면서 체크 ,
        //셋팅 하고 이전 종료시간이 다음 시작시간보다 빠르면 추가하는 형식으로해야함
        //겹치는게 있으면 무시
        int cnt = 0;
        int time = 0;
        while(!pq.isEmpty()){
            P1931 cur = pq.poll();
            if(time <=cur.start){       //이전까지의 종료 시간이 현재 회의실 시간의 시작시간보다 작으면
                time = cur.end;
                cnt++;
            }
        }

        //갯수를 구하는 것이므로 종료시간이 같은 것들 중에서 더 짧은 걸 고를필요가 없다.
        sb.append(cnt);
        System.out.println(sb.toString());


    }
}
