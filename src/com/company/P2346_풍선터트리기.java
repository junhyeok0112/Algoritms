package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class P2346{
    int num;
    int papaer;
    P2346(int num , int papaer){
        this.num = num;
        this.papaer = papaer;
    }
}

public class P2346_풍선터트리기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Deque<P2346> dq = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=n ;i++){
            int paper = Integer.parseInt(st.nextToken());
            dq.add(new P2346(i , paper));
        }

        while (!dq.isEmpty()){
            P2346 cur = dq.pollFirst();
            sb.append(cur.num).append(" ");
            if(dq.size() == 0 )break;
            int move = Math.abs(cur.papaer);
            if(cur.papaer <0){
                for(int i = 0 ; i<move-1; i++){
                    dq.addFirst(dq.pollLast());
                }
            } else{
                for(int i = 0 ; i<move-1;i++){
                    dq.addLast(dq.pollFirst());
                }
            }
        }
        System.out.println(sb.toString());
    }
}
