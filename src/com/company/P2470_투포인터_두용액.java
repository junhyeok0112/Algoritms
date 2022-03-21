package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2470_투포인터_두용액 {
    static int N;
    static int[] arr;
    static int ansMin = Integer.MAX_VALUE;  //합이 작은 값 갱신

    public static void main(String[] args) throws IOException {
        //L : 남아 있는 것들 중 제일 작은 원소
        //R : 남아 있는 것들 중 제일 큰 원소
        //최소 + 최대 < 0 : 최소 입장에서는 최선책을 만난상태 ! 짝을 찾았으니 삭제(더 고려 X)
        //최소 + 최대 > 0 : 최대 입장에서는 최선책을 만난상태 ! 짝을 찾았으니 삭제(더 고려 X)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr , 1 , N+1);
        int L = 1 , R = N;
        int res1 = 0 , res2 = 0 ;   //정답을 저장할 각각의 변수
        while(L <R){    //L == R인 상황이면 용액이 한 개 뿐인 것이므로 L<R일 때까지만 반복한다.
            int temp = arr[L] + arr[R];

            if(ansMin > Math.abs(temp)){
                ansMin = Math.abs(temp);
                res1 = arr[L];
                res2= arr[R];
            }
            if(temp > 0){ //최소 + 최대 > 0 : 최대 입장에서는 최선책을 만난상태 ! 짝을 찾았으니 삭제(더 고려 X)
                //최대 + 최소가 0보다 크므로 최대값이 어떠한 다른 값을 만나도 처음 값보다 크므로 최대값은 최선의 짝을 만남
                R--;
            } else if(temp < 0){ //최소 + 최대 < 0 : 최소 입장에서는 최선책을 만난상태 ! 짝을 찾았으니 삭제(더 고려 X)
                //최대 + 최소가 0보다 작므로 최소값이 어떠한 다른 값을 만나도 처음 값보다 크므로 최대값은 최선의 짝을 만남
                L++;
            } else{ //0이면 정답으로 출력
                System.out.println(arr[L]+ " " + arr[R]);
                return;
            }

        }
        System.out.println(res1+ " " + res2);

    }
}
