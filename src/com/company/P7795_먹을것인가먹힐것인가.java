package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P7795_먹을것인가먹힐것인가 {
    static int T,N,M,cnt ;
    static int[] A;
    static int[] B;

    static int eat(int A, int[] B){ //A가 찾는 값 , 즉 A보다 작은거 개수
        //B[L...R] 에서 A 미만의 수 (A보다 작은 수 )중 제일 오른쪽 인덱스를 return 하는 함수
        //그런게 없다면 L-1 return;
        //이분 탐색 코드는 정형화 되어있음
        int right = B.length-1;
        int left = 1;
        int middle;
        int result = left - 1;  //아무것도 갱신 안되면 이거 리턴
        while(left <= right){
            middle = (left + right) / 2 ;
            if(B[middle] >= A){
                right = middle - 1;
                //크거나 같으면 오른쪽에서 떙겨옴 -> 만약 같으면 R이 찾는 값의 왼쪽으로 가게되고 반복되면 L이 R보다 커져서 자동으로 종료됨
            } else if(B[middle] < A){
                left = middle + 1;
                result = middle;    //middle로 본 값보다 찾는 값이 더 크므로 middle까지는 모두 먹을 수 있음

            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while(T>0){
            // 입 력 과 정
            cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N+1];
            B = new int[M+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i<=N ;i++){
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i<=M ;i++){
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A ,1 , N+1);
            Arrays.sort(B, 1, M+1);

            for(int i = 1; i<=N;i++){
                cnt+=eat(A[i],B);
            }

            System.out.println(cnt);
            T--;

        }
    }
}
