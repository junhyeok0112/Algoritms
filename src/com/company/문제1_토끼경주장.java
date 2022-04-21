package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제1_토끼경주장 {

    static int tc;
    static int[] height = new int[10];//각 경기장의 높이
    static int[] dif = new int[10];     //각 경기장들의 높이 차이 dif[0]은 0번지점에서 1번지점으로 갈때의 차이
    static int[][] rabbit = new int[5][2];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        int t = 0;
        while (t++<tc){
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i<10; i++){
                height[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0 ; i<9 ;i++){
                dif[i] = height[i+1] - height[i];
            }
            int ans = 0;
            for(int i = 0 ; i<5 ;i++){
                st = new StringTokenizer(br.readLine());
                int up = Integer.parseInt(st.nextToken());
                int down = Integer.parseInt(st.nextToken());
                rabbit[i][0] = up;
                rabbit[i][1] = down;
                boolean flag = true;
                for(int j = 0 ; j<9 ;j++){
                    if(dif[j] >=0 ){
                        //오르막길일떄
                        if(dif[j] > rabbit[i][0]) {
                            flag = false;
                            break;
                        }
                    } else{
                        //내리막길일떄
                        if(Math.abs(dif[j]) > rabbit[i][1]){
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag) ans++;     //가능한 토끼면 증가
            }

            sb.append(ans).append("\n");

        }
        System.out.println(sb.toString());

    }
}
