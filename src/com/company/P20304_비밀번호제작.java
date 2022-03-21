package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class P20304{
    int num;
    int count;
    P20304(int num , int count){
        this.num = num;
        this.count = count;
    }
}

public class P20304_비밀번호제작 {

    static int n,m;
    static Queue<P20304> q = new LinkedList<>();
    static int[] visit;
    //안전도가 가장 높은 비밀번호를 구하라
    //왜 모든 수를 안볼까 ? -> 1~N까지 비교해가면서 왜 안하고 비트연산으로할까?


    //큐에 비밀번호가 될 수 있는 수들을 저장해두는것 , 근데 ..
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visit = new int[n+1];
        Arrays.fill(visit, -1 );
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<m ;i++){
            q.add(new P20304(Integer.parseInt(st.nextToken()) ,0));
        }

        //아래 형식이면 0~ N 까지 모두 다 체크 가능함
        while (!q.isEmpty()){
            P20304 cur = q.poll();
            for(int temp = 1 ; temp<=n ; temp = temp<<1 ){
                //1비트 차이나는 숫자 만들어서 next로 저장하기
                int next = (cur.num & temp) > 0 ? (cur.num - temp) : (cur.num + temp);
                if(next <= n && visit[next] == -1){ //범위 안이고 방문 안한 곳이면
                    visit[next] = cur.count + 1 ;   //cur과 거리 1 떨어져있는 거리 저장
                    q.add(new P20304(next, visit[next]));
                }
            }
        }

        int res = 0;
        for(int i = 0 ; i<= n; i++){
            res = Math.max(res , visit[i]);
        }
        System.out.println(res);
    }
}
