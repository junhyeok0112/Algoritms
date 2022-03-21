package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P15681_트리와쿼리 {

    static int N,R,Q;
    static ArrayList<Integer>[] tree;
    static boolean[] visit;
    static int[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1];
        visit = new boolean[N+1];
        dp = new int[N+1];
        for(int i = 1 ; i<=N ;i++) tree[i] = new ArrayList<>();

        for(int i =0 ;i<N-1; i++){                  //트리 생성
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            tree[num1].add(num2);
            tree[num2].add(num1);
        }

        dfs(R);

        for(int i = 0 ;i<Q ; i++){
            st = new StringTokenizer(br.readLine());
            int res = Integer.parseInt(st.nextToken());
            sb.append(dp[res]).append("\n");
        }

        System.out.println(sb);
    }

    static int dfs(int start){
        visit[start]= true;
        int val = 0;            //subtree의 정점 개수 저장
        val++;                  //자기 자신 세어주기
        for(int i : tree[start]){
            if(!visit[i]){  //방문 안했으면
                val += dfs(i);          //자식들의 정점개수 return받아서 나의 총 정점개수에 더해줌
            }
        }
        dp[start] = val;                //나의 정점 개수를 dp에 반영
        return val;                     //나의 정점 개수를 부모한테 return 부모는 이 값가지고 부모 본인의 정점 개수 갱신.
    }
}
