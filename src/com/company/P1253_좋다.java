package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1253_좋다 {
    //두 용액 문제와 비슷한 느낌으로 풀면됨
    //정렬후 타겟 수 결정 ->O(N)
    //다른 수 2개 결정해서 만들어지나 확인 ->O(N) ->따라서 O(N^2)

    static boolean find(int target_idx , int[] arr , int N){
        //주의 사항 : 원하는 숫자가 idx번재에 있다면 , 서로다른 두 수는 idx를 사용해서 만들어지면 안된다.
        int L = 1;
        int R = N ;
        int target = arr[target_idx];
        while(L<R){
            if(L ==target_idx) L++; //자기자신을 이용하면 안되므로 타겟넘버면 건너뜀
            else if(R == target_idx) R--;
            else{   //L과 R이 가리키는 값의 합이 target이면 return true
                if(arr[L]+arr[R] == target) return true;
                //여기서 부터 두 용액문제와 동일 -> 최대 + 최소 >0 or < 0인걸로 나눔
                else if(arr[L]+arr[R] > target) R--;    //가장 큰것과 가장 작은걸 더해도 target보다 큰거면 뭐랑 더해도 target보다 크므로제외
                //두용액에서는 target 이 0이다
                else L++;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] visit = new int[N+1]; //방문했는지 확인할 뱅ㄹ
        int cnt = 0;    //결과 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr , 1 ,N+1);   //정렬 O(NlogN)

        //주의 사항 : 원하는 숫자가 IDX번재에 있다면 , 서로다른 두 수는 idx를 사용해서 만들어지면 안된다.
        //배열을 한번만 훑으면서 타겟넘버를 지정하고 그 타겟넘버가 만들어 질 수 있는지 확인한다.
        for(int i = 1; i<= N ;i++){
            if(find(i , arr , N )){ //find가 가능한 값인지 확인하는 함수
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
