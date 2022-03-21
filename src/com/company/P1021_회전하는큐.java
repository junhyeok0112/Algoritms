package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P1021_회전하는큐 {

    static int n,m;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<m ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> dq = new LinkedList<>();
        for(int i = 1 ; i<=n ;i++){
            dq.add(i);
        }
        int cnt = 0;

        for(int i = 0 ; i<m ;i++){
            //절반으로 나눔
            int idx = -1;
            for(int cur : dq){
                idx++;
                if(cur == arr[i]) break;    //몇번째에 있는지 찾기
            }
            int dqsize = dq.size();
            int half = dqsize /2 ;

            while (dq.peekFirst() != arr[i]){
                if(idx <= half){
                    dq.addLast(dq.pollFirst());
                    cnt++;
                } else{
                    dq.addFirst(dq.pollLast());
                    cnt++;
                }
            }
            dq.poll();
        }

        System.out.println(cnt);

    }
}
