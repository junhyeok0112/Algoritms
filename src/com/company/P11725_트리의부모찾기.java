package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11725_트리의부모찾기 {

    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static boolean[] visit;
    static int[] ans;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        visit = new boolean[N+1];
        ans = new int[N+1];
        for(int i= 0 ; i<=N; i++){
            arr.add(new ArrayList<Integer>());
        }

        for(int i = 1 ; i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr.get(n1).add(n2);
            arr.get(n2).add(n1);
        }
        //System.out.println("하이");
        dfs(1,0 );
        for(int i = 2; i<=N; i++){
            sb.append(ans[i]).append('\n');
        }
        System.out.println(sb);



    }

    static void dfs(int n , int par){
        visit[n] = true;
        ans[n] = par;
        for(int j : arr.get(n)){
            if(!visit[j])
                dfs(j ,n);
        }
    }
}
