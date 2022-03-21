package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12886_돌그룹 {

    static int A,B,C;
    static boolean[][] visit;       //봤던거를 또 확인하기 위한 변수 , 2개만 체크하면 다른 거의 수는 정해지므로 2차원배열
    static int ans = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visit = new boolean[1501][1501];
        if((A+B+C) % 3 != 0){
            System.out.println(0);
            return;
        }

        dfs(A,B,C);
        System.out.println(ans);

    }

    static void dfs(int A, int B , int C){
        if(A == B && B == C){
            ans = 1;
            return;
        }

        int min = Math.min(A,B);
        int max = Math.max(A,B);
        if(!visit[min*2][max-min]){
            visit[min*2][max-min] = visit[max-min][min*2] =  true;
            dfs(min+min , max-min , C );
        }

        min = Math.min(A,C);
        max = Math.max(A,C);
        if(!visit[min*2][max-min]){
            visit[min*2][max-min] = visit[max-min][min*2] =  true;
            dfs(min+min , max-min , B );
        }

        min = Math.min(C,B);
        max = Math.max(C,B);
        if(!visit[min*2][max-min]){
            visit[min*2][max-min] = visit[max-min][min*2] =  true;
            dfs(min+min , max-min , A );
        }

    }



}
