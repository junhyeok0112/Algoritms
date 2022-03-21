package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class P1655_가운데를말해요 {

    static int N;
    //small은 내림차순 , Big은 오름차순
    static PriorityQueue<Integer> pqSmall = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> pqBig = new PriorityQueue<>();
    static int ans = 10001;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (pqSmall.size() == 0 && pqBig.size() == 0 && ans == 10001) {//초기값일 경우
                ans = num;   //초기 조건
                sb.append(ans).append("\n");
                continue;
            }
            if (num < ans) { //들어온게 ans보다 작을경우
                pqSmall.add(num);
            } else {//새로운 값이 ans 보다 크거나 같을경우
                pqBig.add(num);
            }
            if (pqSmall.size() == pqBig.size() || pqBig.size() - pqSmall.size() == 1) {    //크기 같거나 작은게 1개 적으면 ans가 중간값
                sb.append(ans).append("\n");
            } else if (pqBig.size() - pqSmall.size() >= 2) {   //작은 pq 크기가 2개이상 차이나면 pqBig의 제일 작은 값이 중간값이됨
                pqSmall.add(ans);
                ans = pqBig.poll();
                sb.append(ans).append("\n");
            } else if (pqSmall.size() - pqBig.size() >= 1) {  //pqBig에 있는 개수가 pqSmall에 있는 개수보다 작으면
                pqBig.add(ans);
                ans = pqSmall.poll();
                sb.append(ans).append("\n");
            }

        }

        System.out.println(sb);

    }
}
