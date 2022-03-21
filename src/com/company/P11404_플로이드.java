package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class P11404_플로이드 {
    //모든 정점에서 모든 정점으로의 최단 경로 구할 때 사용

    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        //map = new ArrayList[n+1];
        int[][] ans = new int[n+1][n+1];
        for(int i = 0 ; i<=n ;i++){
            for(int j = 0 ; j<=n ;j++){
                if(i==j) {
                    ans[i][j] = 0;
                    continue;
                }
                ans[i][j] = 1000000001;  //최대 값들로 전부 초기화
            }
        }

        StringTokenizer st ;
        for(int i = 0 ; i<m ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            //map[a].add(new P11404(b,c));
            if(ans[a][b] >= c){
                ans[a][b] = c;
            }

        }



        //플로이드 알고리즘 실행
        for(int k = 1 ; k<=n ;k++){
            for(int i = 1; i<=n ;i++){
                if(i==k) continue;
                for(int j = 1; j<=n ; j++){
                    if( ans[i][j] > ans[i][k] + ans[k][j]){      //거쳐가는게 더 짧으면 갱신 단 , 무한대 값이면 갈 수 없음
                        ans[i][j] = ans[i][k] + ans[k][j];
                    }
                }
            }
        }

        for(int i =1 ; i<=n ;i++){
            for(int j =1 ; j<=n ;j++){
                if(ans[i][j] >= 100000001){
                    System.out.print(0+" ");        //최대값이면 방문 안한곳
                }else{
                    System.out.print(ans[i][j]+ " ");
                }
            }
            System.out.println();
        }

    }
}
