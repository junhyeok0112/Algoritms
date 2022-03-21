package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P2164_카드2 {

    static int n;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i =1 ;i<=n ;i++){
            q.add(i);
        }

        while (!q.isEmpty()){
            if(q.size() == 1){
                break;
            }
            q.poll();
            if(q.size() == 1){
                break;
            }
            int cur = q.poll();
            q.add(cur);
        }
        System.out.println(q.poll());
    }
}
