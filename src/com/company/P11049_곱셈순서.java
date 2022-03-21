package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Matrix{
    int x;
    int y;
    Matrix(int x, int y ){
        this.x = x;
        this.y = y;
    }
}


public class P11049_곱셈순서 {

    static int N;
    static ArrayList<Matrix> arr = new ArrayList<>();
    static long[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr.add(new Matrix(0,0));   //필요 없는수
        for(int i = 0 ; i<N ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.add(new Matrix(x,y));
        }
        dp = new long[N+1][N+1];

        //2개씩 곱했을때의 값 초기화화
//       for(int i =1 ; i<N ;i++){
//            dp[i][i+1] = (long)arr.get(i).x * arr.get(i).y * arr.get(i+1).y;
//        }

       //큰 곳부터 초기화 해줌
       for(int len = 2 ;len <=N ;len++){ //몇개 합칠지 -> len이 3 이면 1~3까지 3개 합치는것 , 이렇게 하나씩 늘려가야함
           for(int i = 1 ;i<N ; i++){  //시작점 지정Q
               int j = i + len - 1;     //몇개까지 합칠지 갯수
               if(j >N ) continue;      //범위 넘으면 넘어감
               dp[i][j] = Integer.MAX_VALUE;
               for(int k = i ; k<j; k++){  // [1][2~j] , [1,2][3~j] ..앞에 k를 기준으로 파티션
                    dp[i][j] = Math.min(dp[i][j] , dp[i][k]+dp[k+1][j] + arr.get(i).x * arr.get(k).y * arr.get(j).y);
               }
           }
       }
       System.out.println(dp[1][N]);
    }
}
