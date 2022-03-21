package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class P2580{
    int x;
    int y;
    P2580(int x, int y ){
        this.x = x;
        this.y = y;
    }
}


public class P2580_스도쿠2 {

    static int[][] map = new int[9][9];
    static P2580[] arr = new P2580[81];
    static int zero = 0;
    static boolean flag = false;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        for(int i = 0 ; i< 9 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< 9 ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0 ){
                    arr[zero] = new P2580(i,j);
                    zero++;
                }
            }
        }
        // 0~zero 까지 저장되어 있음

        pro(0,0);

    }

    static void pro(int cnt , int idx){
        if(flag) return;
        if(cnt >= zero){
            for(int i = 0 ; i< 9; i++){
                for(int j = 0 ; j<9 ; j++){
                    System.out.print(map[i][j]+ " ");
                }
                System.out.println();
            }
            flag = true;
            return;
        }
        P2580 r = arr[idx];
        for(int i = 1 ; i<=9 ; i++){
            if(check(i , r.x ,r.y)){        //i를 넣을 수 있으면
                map[r.x][r.y] = i;
                pro(cnt+1, idx+1);
                map[r.x][r.y] = 0;
            }
        }
    }

    static boolean check(int num , int x, int y){
        //좌우 직선 확인
        for(int i = 0 ; i < 9; i++){
            if(map[x][i] == num) return false;
            if(map[i][y] == num) return false;
        }

        int startX = 3*(x/3);
        int startY = 3*(y/3);
        for(int i = 0 ; i<3 ;i++){
            for(int j =0 ; j<3; j++){
                if(map[startX+i][startY+j] ==num) return false;
            }
        }

        return true;

    }

}
