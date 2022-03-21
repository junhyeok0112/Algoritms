package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C09_Magnetic_최준혁 {

    static int n ;
    static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int tc = 1; tc<=10 ; tc++){
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for(int i = 0; i< n ;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j<n ;j++){
                    map[i][j] =Integer.parseInt(st.nextToken());
                }
            }
            int ans = 0;
            //세로로 봐야함
            for(int i = 0 ; i<n ;i++){
                int now = 0;        //현재 제일 위에 있는거
                for(int j = 0 ; j<n ;j++){
                    if(map[j][i] == 0 ) continue;
                    if(map[j][i] == 1){ //내려오는 것들이고
                       now = 1;
                    } else{ //지금 만난게 1일때         올라가는 것들인데
                        //이전이 0이나 1인거는 관계 없음
                        if(now == 1){ //교착상태 없었을때 , 이제 교착상태 생성
                            ans++;
                            now = 0;
                        }
                    }
                }
            }
            System.out.println("#"+tc+" " +ans);
        }
    }
}
