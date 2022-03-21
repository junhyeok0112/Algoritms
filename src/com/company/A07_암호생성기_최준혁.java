package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A07_암호생성기_최준혁 {

    static Queue<Integer> q ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = 0;
        StringBuilder sb = new StringBuilder();
        //StringTokenizer st = new StringTokenizer(br.readLine());
        while (t++ <10){
            StringTokenizer st =new StringTokenizer(br.readLine());
            st = new StringTokenizer(br.readLine());
            q = new LinkedList<>();
            int cnt = 1;
            for(int i = 0 ; i<8 ;i++){
                q.add(Integer.parseInt(st.nextToken()));
            }
            while(true){
                int cur = q.poll();
                cur -= cnt;
                if(cur <= 0){
                    cur = 0;
                    q.add(cur);
                    break;
                } else{
                    q.add(cur);
                }
                cnt++;
                if(cnt == 6) cnt = 1;       //싸이클 돌면 초기화
            }
            sb.append("#"+t+" ");
            for(int i= 0; i<8; i++){
                sb.append(q.poll()+" ");
            }
            sb.append("\n");

        }
        System.out.println(sb.toString());
    }
}
