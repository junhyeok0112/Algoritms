package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B01_달팽이숫자_최준혁 {

    static int n , t;
    static int[][] arr ;
    static int num = 1;         //1부터 값 채우면서 증가
    static int[] dx = {1,0,-1,0};   //우하좌상
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        int cur = 0;
        while(cur++ < t){
            n = Integer.parseInt(br.readLine());
            if(n == 1 ){    //1일때 1출력
                System.out.println("#"+cur);
                System.out.println(1);
                continue;
            }
            arr = new int[n][n];
            System.out.println("#"+cur);
            pro(0,n,0,0);

            num = 1;            //다쓴 num 초기화

        }


    }

    static void pro(int dir , int n ,int start , int end ){        //우하좌상 식으로 움직임 ,0~3 으로 나타냄 ,
        //dir = 0 , dir =2 일때만  n 감소시켜주면됨 -> 오른쪽과 왼쪽일때
        if(n ==0 ){ //다 채웠으면 n ==0일것이다.
            for(int i = 0 ; i<arr.length;i++){
                for(int j =0 ;j<arr.length; j++){
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
            return;
        }
        for(int i = 0 ; i<n ;i++){
            arr[start][end] = num;
            if(i != n-1 ){              //마지막에는 채우고 이동하면안됨
                start+=dy[dir];
                end+=dx[dir];
            }
            num++;
        }
        if(dir == 0 ||dir == 2){                //오른쪽과 왼쪽으로 이동했으면 가야할 칸이 1개 줄어드므로 n-1 , 또 그 다음시작점을 이동시켜주고 넘겨준다
            pro((dir+1) %4 , n-1 , start+dy[(dir+1) %4] , end + dx[(dir+1) %4]);
        }else{                                  //위 아래로 이동하면 가야할 거리 안줄어든다 .따라서 n 을넘겨줌
            pro((dir+1) %4 , n , start+dy[(dir+1) %4] , end + dx[(dir+1) %4]);
        }

    }


}
