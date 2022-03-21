package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class B03 {
    int idx;
    int val;

    B03(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}


public class B03_탑_최준혁 {

    static int n;
    static int[] ans;
    static Stack<B03> s1 = new Stack<>();       //값들을 저장할 스택
    static Stack<B03> s2 = new Stack<>();     //뺀 숫자를 임시로 저장할 스택

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        //ans = new int[n];
        ans = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            s1.push(new B03(i, cur));
        }
        s2.push(s1.pop());
        while (!s1.isEmpty()) {
            B03 cur = s1.pop();

            while (!s2.isEmpty() && s2.peek().val <= cur.val) { //새로 넣을 값이 s2에 있는 값들보다 작을까지 반복
                B03 temp = s2.pop();         //얘가 새로 들어올 건물에 막힘
                ans[temp.idx] = cur.idx;
            }
            s2.push(cur);
        }

        for (int i = 1; i <= n; i++) {
            sb.append(ans[i] + " ");
        }
        System.out.println(sb.toString());

    }
}
