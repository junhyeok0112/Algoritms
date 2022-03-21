package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14889_스타트와링크 {

    static int min = Integer.MAX_VALUE;
    static int n;
    static int[][] map;
    static boolean[] select;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n =Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        select = new boolean[n+1];
        StringTokenizer st ;
        for(int i = 1 ; i<=n ;i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 1; j<= n ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pro(0 ,0);
        System.out.println(min);

    }

    static void pro(int cnt, int prev) {
        if(cnt >= n/2){
            int t = 0;
            int f = 0;
            for(int i =1 ; i< n ;i++){
                for(int j = i+1 ;j<=n ;j++){
                    if(select[i] == select[j]){
                        if(select[i]){  //true로 같으면
                            t += map[i][j];
                            t += map[j][i];
                        } else{
                            f += map[i][j];
                            f += map[j][i];
                        }
                    }
                }
            }
            min = Math.min(min ,Math.abs(t-f));
            return;
        }

        for(int i = prev+1; i<= n ;i++){
            if(!select[i]){
                select[i] = true;
                pro(cnt+1 , i);
                select[i] = false;
            }
        }
    }

}
