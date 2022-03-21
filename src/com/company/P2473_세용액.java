package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P2473_세용액 {

    static int N;
    static int[] arr;
    static long res = Long.MAX_VALUE;       //절대값이 작은거 구할예정
    static long v1 , v2 ,v3;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =1 ; i<=N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr,1,N+1);
        //하나의 포인트는 고정시키고 L과 R을 그 포인트 다음부터 체크
        for(int start = 1; start<=N-2 ;start++){        //start 뒤에 L,R 2개가 있어야하므로 N-2 까지
            int L = start +1;
            int R = N;
            long target = arr[start];                    //기준 , 고정점
            while(L<R){
                if(res > Math.abs(arr[L]+arr[R]+target)){           //int 들끼리 더하면 결과가 int 여서 범위 넘을떄 오버플로우 발생함
                    res = Math.abs(arr[L]+arr[R]+target);
                    v1 = target;
                    v2 = arr[L];
                    v3 = arr[R];
                }
                if(arr[L]+arr[R] + target > 0){     //target은 고정이므로 arr[L] + arr[R] 의 값을 -target과 가까이해야함 크면 R을 줄여야만 작아짐 -> 두용액일때와 비슷
                    //즉 양수일때 L을 움직이면 어짜피 양수이므로 R을 줄여서 0과 가깝게함 -> 두 용액과 비슷
                    R--;
                } else{                         //target보다 작으면 arr[L] +arr[R] 값을 크게해주어서 target과 가깝게해야함 즉 L의 값을 증가시켜야함 같을경우 정답은 저장되어있는 상태이다 0으로
                    L++;
                }
            }

        }
        System.out.println(v1+" " + v2 +" "+ v3);


    }
}
