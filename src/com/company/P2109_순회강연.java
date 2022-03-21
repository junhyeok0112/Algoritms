package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Money implements Comparable<Money>{
    int d;
    int p;
    Money(int p, int d){
        this.d = d;
        this.p = p;
    }

    @Override
    public int compareTo(Money o) {
        if(this.p < o.p){
            return 1;
        } else{
            return -1;
        }
    }
}

public class P2109_순회강연 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] days = new int[10001];
        //p와 d 값 주어짐
        PriorityQueue<Money> pq = new PriorityQueue<>();
        StringTokenizer st ;

        for(int i = 0 ; i <N ;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.add(new Money(p,d));

        }
        //System.out.println(pq.size());
        //돈을 많이 벌수 있는 강연부터 순서대로 받는다 -> pq이용
        //가능한 날을 체크하는 형식으로 진행 날짜를 역순으로 1일까지 체크한다
        //그 날짜 안에만 하면되므로 만약 그 당일에 일정이 있으면 그 앞에 날짜 즉 -> 1일 더 땡겨서 가능하면 한다.
        int sum = 0;
        int time = 1;  //날짜
        while(!pq.isEmpty()){
            Money m = pq.poll();
            int now = m.d;
            if(days[now] == 0){
                sum+= m.p;
                days[now] = 1;
                //System.out.println(m.p);
            } else{
                while(days[now] !=0){   //빈곳 찾기
                    if(now == 0){
                        break;
                    }
                    now--;
                }
                if(now != 0){ //빈곳찾았으면
                   days[now] = 1;   //체크
                   sum+=m.p;
                  // System.out.println(m.p);
                }
                //빈곳 없으면 넘어감
            }

        }
        System.out.println(sum);
    }
}
