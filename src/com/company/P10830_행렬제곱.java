package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10830_행렬제곱 {

    static int N;
    static long B;
    static int[][] A;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        A = new int[N][N];
        for(int i = 0 ; i<N ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N ; j++ ){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //B가 1일때 A의 원소가 1000이면 0이출력 되야하므로 1000으로 나누어줌
        if(B == 1 ){
            for(int i = 0 ; i<N ;i++){
                for(int j = 0 ; j<N ; j++){
                    System.out.print(A[i][j] % 1000 + " ");
                }
                System.out.println();
            }
            return;
        }

        A = rec(A,B);
        for(int i = 0 ; i<N ;i++){
            for(int j = 0 ; j<N ; j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }

    }

    //A를 B번 제곱하는 것은 이 함수에서 진행
    static int[][] rec(int[][] A , Long B){ //A를 B번 제곱한다는 뜻
        if(B == 1){
            return A;
        }

        //시간을 절반으로 줄이기 위해 먼저 구해서 저장해둡니다. 즉 A^50 이면 A^25 ,A^25 2번 구해야하는데
        //A^25를 한번만 구해서 저장해둡니다.
        int[][] temp = rec(A ,B/2);

        if(B % 2 == 1){     //B가 홀수개면
            return cal(cal(temp,temp),A);
        } else{
            return cal(temp,temp);
        }
    }

    static int[][] cal(int[][] left, int[][] right){      //A를 제곱해주는 함수
        int[][] res = new int[N][N];
        for(int i = 0 ; i<N ;i++){
            for(int j = 0; j<N ; j++){
                for(int k = 0 ; k<N ;k++){
                    res[i][j] = (res[i][j] + (left[i][k] * right[k][j])) % 1000;
                }
            }
        }
        return res;
    }
}
