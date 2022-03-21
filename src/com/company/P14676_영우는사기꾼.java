package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P14676_영우는사기꾼 {

    static int N,M,K;
    static int[] indeg;
    static ArrayList<Integer>[] adj;
    static ArrayList<Integer>[] build;
    static int[] now ;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        indeg = new int[N+1];
        adj = new ArrayList[N+1];
        build = new ArrayList[N+1];
        now = new int[N+1];
        for(int i =1 ; i<=N ;i++) adj[i] = new ArrayList<>();
        for(int i =1 ; i<=N ;i++) build[i] = new ArrayList<>();
        for(int i = 0 ; i<M ;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            adj[num1].add(num2);
            build[num2].add(num1);
            indeg[num2]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i<K ;i++){
            st = new StringTokenizer(br.readLine());
            int chk = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            q.add(chk);
            q.add(num);
        }


        while (!q.isEmpty()){
            int chk = q.poll();
            int num = q.poll();
            if(chk == 1){
                if(indeg[num] == 0){
                    now[num]++;
                    if( now[num] ==1 ) {                       //이미 지어진 연결된거면
                        for (int t : adj[num]) {
                            if (indeg[t] == 0) continue;     //이미 지을수 있는데 중복으로 지을경우
                            indeg[t]--;
                        }
                    }
                } else{
                    System.out.println("Lier!");
                    return;
                }
            } else{ //indeg[num] != 0 이면 애초에 지을 수 없으므로 위에서 걸러짐
                 if(now[num] == 0){
                     System.out.println("Lier!");
                     return;
                 }
                 else{
                    now[num]--;
                    if(now[num] == 0){
                        for (int t : adj[num]) {
                          indeg[t]++;
                        }
                    }
                }
            }
        }

        System.out.println("King-God-Emperor");
    }


}
