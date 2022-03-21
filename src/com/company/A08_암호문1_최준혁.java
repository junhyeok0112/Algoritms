package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A08_암호문1_최준혁 {

    static Deque<Integer> dq;
    static Stack<Integer> input;
    static Stack<Integer> sta;
    static int n , cmd;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st ;
        int t = 0;
        while (t++<10){
            dq = new LinkedList<>();
            input = new Stack<>();
            sta = new Stack<>();                //뺸값들 임시로 저장
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i< n;i++){
                dq.addLast(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i< cmd ;i++){           //명령어 처리
                st.nextToken();                     //I문자열 처리
                int start = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());     //몇개를 넣을지
                for(int j = 0;j<cnt; j++){
                    input.push(Integer.parseInt(st.nextToken()));
                }

                for(int j = 0 ; j<start;j++){       //start까지 빼기
                    sta.push(dq.pollFirst());       //앞에서부터빼기
                }
                for(int j = 0; j<cnt;j++){          //추가해야하는 명령어 넣기
                    dq.addFirst(input.pop());
                }
                for(int j = 0; j<start;j++){          //추가해야하는 명령어 넣기
                    dq.addFirst(sta.pop());
                }
            }

            sb.append("#"+t+" ");
            int dqsize = dq.size();
            for(int i = 0 ; i<10;i++){
                sb.append(dq.pollFirst()+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
