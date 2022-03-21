package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1715_카드정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0 ; i<N; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }
        int sum = 0;
        //int 범위 안인 이유는 depth(logN) * 1억이다
        while(pq.size() != 1){
            int num1 = pq.poll();
            int num2 = pq.poll();
            int temp = num1 + num2;
            sum+=temp;
            pq.add(temp);

        }
        //sum+=pq.poll();
        System.out.println(sum);

    }
}
