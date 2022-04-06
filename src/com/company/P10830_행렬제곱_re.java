package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10830_행렬제곱_re {

    static int n;
    static long B;
    static int[][] A;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        A = new int[n][n];
        for(int i = 0 ; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<n ;j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //B가 1일때 A의 원소가 1000이면 0이출력 되야하므로 1000으로 나누어줌
        if(B == 1 ){
            for(int i = 0 ; i<n ;i++){
                for(int j = 0 ; j<n ; j++){
                    System.out.print(A[i][j] % 1000 + " ");
                }
                System.out.println();
            }
            return;
        }


        A = divide(A,B);
        for(int i = 0 ; i<n;i++){
            for(int j = 0 ;j<n;j++){
                System.out.print(A[i][j]+ " ");
            }
            System.out.println();
        }

    }

    //행렬 곱하는 함수
    static int[][] multi(int[][] A , int[][] B){
        int[][] ans = new int[n][n];
        for(int i = 0 ; i<n ;i++){

            for(int j=0; j<n;j++){

                for(int k = 0 ; k<n;k++){
                    //왼쪽 행렬에서는 행 고정 , 오른쪽 행렬에서는 열 고정 , 따라서 움직이는 변수는 1개 k만 필요
                    ans[i][j] = (ans[i][j] + (A[i][k] * B[k][j])) % 1000;
                }
            }
        }
        return ans;
    }

    //거듭제곱 하는 함수 , 이러면 logn 만에 처리가능
    static int[][] divide(int[][] A , long B){       //A를 B제곱 해서 결과를 리턴하는 함수
        if(B == 1){
            return A;
        }

        int[][] temp = divide(A ,B/2);           //B제곱을 전부 하는건 너무 오래걸리므로 절반씩 쪼갬 -> 이때 절반쪼갠걸 저장해두면 메모이제이션으로 접근가능
        //B가 홀수일때 짝수일때 구분해야함
        if(B % 2 == 1){
            return multi(multi(temp,temp) , A);     //만약 B가 5이면 A^4 * A 해야함
        } else{
            return multi(temp , temp);
        }
    }
}
