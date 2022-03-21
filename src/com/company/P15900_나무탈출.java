package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P15900_나무탈출 {

    static int N ;
    static int[] visit;
    static ArrayList<Integer>[] tree;
    static int cnt = 0;

    //성원이가 마지막에 움직이면 이김 -> 즉 움직일 수 있는횟수가 홀수면 이김
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        visit = new int[N+1];   
        for(int i= 1; i<=N ;i++) tree[i] = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0 ;i <N-1 ;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            tree[num1].add(num2);
            tree[num2].add(num1);
        }
        dfs(1 , 1);
        //System.out.println(cnt);
        if(cnt % 2 == 1){           //홀수 번 움직이기 가능 -> 따라서 성원이가 먼저 시작했으니까 성원이가 놓으면 끝남
            System.out.println("Yes");
        } else{
            System.out.println("No");
        }
    }

    static void dfs(int num , int visitNum){
        visit[num] += visitNum;
        boolean chk = false;
        for(int k : tree[num]){
            if(visit[k] ==0) {  //방문안한거면
                chk = true;         //리프노드 체크 단, 이때 tree[k].size == 1 이면 리프노드라고 할 수 있다
                dfs(k, visitNum+1);
            }
        }
        if(!chk){
            cnt += visit[num] - 1;      //리프노드면 움직일 수 있는 총 횟수 더해줌 , 1부터 시작했으므로 1빼줘야함
        }
    }
}
