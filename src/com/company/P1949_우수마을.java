package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1949_우수마을 {

    static int N;
    static int[] arr;
    static boolean[] visit;
    static ArrayList<Integer>[] tree;
    static int[][] dp;

    //문제에서는 rooted Tree로 주어진게 아니지만 root =1로 하고 rooted Tree로 정하고 해도된다
    //트리에서 dp문제 , 경우의 수 세야하는 문제는 dp라고 생각해도될듯? ->그리고 아래에서부터 무조건 올라온다라 생각 . 자식 이용해서 부모 구함
    //트리에서 dp는 dfs 이용

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N+1];
        visit = new boolean[N+1];
        tree = new ArrayList[N+1];
        dp = new int[N+1][2];               //dp[i][0] 은 i를 root로 하는 subtree에서 root를 선택하지 않고서 가능한 최대 주민수
                                                    //dp[i][1] 은 i를 root로 하는 subtree에서 root를 선택하고서 가능한 최대 주민 수, 따라서 정답은 max(dp[1][0] , dp[1][1])
        for(int i =1 ; i<=N ;i++) tree[i] = new ArrayList<>();
        for(int i =1 ; i<=N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0 ; i<N-1 ;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            tree[num1].add(num2);
            tree[num2].add(num1);
        }

        //dp의 초기값 : 단말노드인 경우 -> 정점이 한개뿐이라서. 0 or 본인 값
        //점화식 구하기 : root를 고르면 자식들은 인접해서 고를 수 없음. dp[Root,R][1] = num[R] + 시그마dy[child][0]  ,자식은 고르지 않는 subtree의 값들합 + root번째
        // root고르지 않을 경우 자식들을 고를 수 있다 . 즉 자식들의 값들의 합의 최대갑 구하면됨 -> dp[root,R][0] = 시그마(max(dp[child][0] , dp[child][1]))의 값
        dfs(1);         //임의로 1이 root라 생각 -> 어디를 root로 하든 결과같음

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
    static void dfs(int start){
        visit[start] = true;
        //leaf node 도달하면 for문 안도므로 자동으로 초기값도 셋팅
        dp[start][0] = 0;
        dp[start][1] = arr[start];              //arr에 주민 수 더해져 있음
        for(int y : tree[start]){
            if(!visit[y]){
                dfs(y);                     //y했으면 dp[y][0] , dp[y][1]의 값이 구해져 있음
                dp[start][1] += dp[y][0];   //root가 선택 되었을 때
                dp[start][0] += Math.max(dp[y][1], dp[y][0]);
            }
        }
    }
}
