package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class P1956{
    int y ;
    int dis;
    P1956(int y , int dis){
        this.y = y;
        this.dis = dis;
    }
}

public class P1956_운동 {

    static int v, e;
    static int[][] ans ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        ans = new int[v+1][v+1];
        for(int i = 0 ; i<=v ;i++){
            for(int j = 0 ; j<=v ; j++){
                ans[i][j] = 400000000;
            }
        }

        for(int i = 0 ; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ans[a][b] = c;
        }

        for(int k = 1; k<=v ;k++){
            for(int i =1; i<=v; i++){
                for(int j =1 ; j<=v ;j++){
                    ans[i][j] = Math.min(ans[i][j] , ans[i][k] + ans[k][j] );
                }
            }
        }

        int answer = 400000000;
        for(int i = 1;  i<=v; i++){
            answer = Math.min(answer, ans[i][i]);
        }

        if(answer >= 400000000){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }

    }
}
