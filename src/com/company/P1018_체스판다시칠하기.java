package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1018_체스판다시칠하기 {

    static int m,n;
    static char[][] map;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for(int i = 0; i<n ;i++){
            String str = br.readLine();
            for(int j = 0 ; j<m ;j++){
                map[i][j] = str.charAt(j);
            }
        }

        //모든 경우 보기기
       for(int i = 0 ; i<n-7;i++){
            for(int j = 0 ; j<m-7 ;j++){
                //시작지점 정하기
                int resB = 0;               //시작지점이 B 일때
                for(int u = 0 ;u<8 ;u++){     //8개씩보기
                    for(int k = 0 ;k<8 ;k++){ //8개씩보기
                        if( u % 2 == 0){                //세로의 시작이 짝수일때
                            if(k % 2 == 0){             //가로도 짝수일때 , W인 경우를 세어야함
                                if(map[i+u][j+k] == 'W') resB++;
                            } else{
                                if(map[i+u][j+k] == 'B') resB++;
                            }
                        } else{
                            if(k % 2 == 0){             //가로가 홀수일때 , B인 경우를 세어야함
                                if(map[i+u][j+k] == 'B') resB++;
                            } else{                     //가로가 짝수
                                if(map[i+u][j+k] == 'W') resB++;
                            }
                        }
                    }
                }

                int resW = 0;               //시작지점이 B 일때
                for(int u = 0 ;u<8 ;u++){     //8개씩보기
                    for(int k = 0 ;k<8 ;k++){ //8개씩보기
                        if( u % 2 == 0){                //세로의 시작이 짝수일때
                            if(k % 2 == 0){             //가로도 짝수일때 , B인 경우를 세어야함
                                if(map[i+u][j+k] == 'B') resW++;
                            } else{
                                if(map[i+u][j+k] == 'W') resW++;
                            }
                        } else{
                            if(k % 2 == 0){             //가로도 짝수일때 , B인 경우를 세어야함
                                if(map[i+u][j+k] == 'W') resW++;
                            } else{
                                if(map[i+u][j+k] == 'B') resW++;
                            }
                        }
                    }
                }

                ans = Math.min(ans , Math.min(resB,resW));
            }
        }
       System.out.println(ans);
    }
}
