package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//모든 경로 보면 될 것 같은데 ? 에서 플로이드 와샬 시작
public class P11403_경로찾기_re {

    static int n ;
    static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i = 0 ; i<n ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<n ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //플로이드 와샬로 map 채우기 ,i ->K ->j로 가는 경로 있는지 체크
        for(int  k = 0 ; k< n; k++){
            for(int i = 0 ; i< n;i ++){
                if(i == k) continue;        //같은 시작이면 건너뜀
                for(int j = 0 ; j<n ;j++){
                    if(map[i][k] == 1 && map[k][j] == 1){
                        map[i][j] = 1;
                    }
                }
            }
        }

        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ; j<n ;j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
