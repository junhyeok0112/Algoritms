package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5014_스타트링크 {

    static int f,s,g,u,d;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());       //총 F층
        s = Integer.parseInt(st.nextToken());       //강호가 있는 곳
        g = Integer.parseInt(st.nextToken());       //목적지
        u = Integer.parseInt(st.nextToken());       //위로 U층
        d = Integer.parseInt(st.nextToken());       //아래로 D층

        int[] move ={u,-d};

        int[] arr = new int[f+1];                   //층별로 선언
        boolean[] visit = new boolean[f+1];
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(arr, Integer.MAX_VALUE);
        q.add(s);
        visit[s] = true;
        arr[s] = 0;             //시작점
        int btn = 1;
        while (!q.isEmpty()){
            int qsize = q.size();
            for(int t= 0 ; t<qsize; t++){
                int r = q.poll();
                for(int i = 0 ; i<2 ;i++){
                    int n = r + move[i];
                    if( n< 1 || n>f || visit[n]) continue;
                    arr[n] = btn;
                    visit[n] = true;
                    q.add(n);
                }
            }
            btn++;
        }

        if(arr[g] == Integer.MAX_VALUE){
            System.out.println("use the stairs");
        } else{
            System.out.println(arr[g]);
        }

    }
}
