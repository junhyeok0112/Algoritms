package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11444_피보나치수6 {
    static long n;
    static long[][] A = {{1,1},{1,0}};
    //https://www.acmicpc.net/problem/11444 참고
    //피보나치 수는 A([1,1] ,[1,0]) 을 가지고 n 제곱을 통해서 Fn을 구할수 있다
    //A^n = ([Fn+1, Fn] ,[Fn,Fn-1])이다 .
    //따라서 A^n의 (1,1) 출력
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        A = divide(A , n-1);          //A 를 n승함
        System.out.println(A[0][0]);
    }

    //행렬 곱하는 함수
    static long[][] multi(long[][] A , long[][] B){
        long[][] ans = new long[2][2];
        for(int i = 0 ; i<2 ;i++){
            for(int j=0; j<2;j++){
                for(int k = 0 ; k<2;k++){
                    //왼쪽 행렬에서는 행 고정 , 오른쪽 행렬에서는 열 고정 , 따라서 움직이는 변수는 1개 k만 필요
                    ans[i][j] = (ans[i][j] + (A[i][k] * B[k][j])) % 1000000007;
                }
            }
        }
        return ans;
    }

    //거듭제곱 하는 함수 , 이러면 logn 만에 처리가능
    static long[][] divide(long[][] A , long B){       //A를 B제곱 해서 결과를 리턴하는 함수
        if(B <= 1){
            return A;
        }

        long[][] temp = divide(A ,B/2);           //B제곱을 전부 하는건 너무 오래걸리므로 절반씩 쪼갬 -> 이때 절반쪼갠걸 저장해두면 메모이제이션으로 접근가능
        //B가 홀수일때 짝수일때 구분해야함
        if(B % 2 == 1){
            return multi(multi(temp,temp) , A);     //만약 B가 5이면 A^4 * A 해야함
        } else{
            return multi(temp , temp);
        }
    }
}
