package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//소수인지 체크, 그 후 투포인터로 움직이면서 체크
//어떤 수의 소수의 여부를 확인할 때는, 특정한 숫자의 제곱근 까지만 약수의 여부를 검증하면 O(N^1/2)의 시간복잡도로 빠르게 구할 수 있다
public class P1644_소수의연속합 {

    static boolean[] chk = new boolean[4000001];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //에라토스테네스의 체
        //1. 배열 생성 후 초기화
        //2 2부터 시작해서 특정 수의 배수에 해당하는 수를 모두 지운다. 지울 때 자기자신은 지우지 않고 이미 지워진 수는 건너뛴다
        //3. 2부터 시작하여 남은 수출력
        Arrays.fill(chk ,true);
        for(int i = 2 ; i<= 4000000; i++){
            if(!chk[i]) continue;       //이미 지워진거면 넘어가기
            for(int j = 2*i ; j<=4000000; j+=i){ //i의 배수를지우는 것이므로 j 에다가 i 값을 계속 더해줌
                chk[j] = false;
            }
        }

        int left = 2;
        int right = 3;
        int ans = 0;
        int n = Integer.parseInt(br.readLine());
        if(n == 2 || n== 3){
            System.out.println(1);
            return;
        }

        //이 소수들을 리스트에 넣어두면 중간 while문 없이 더 쉽게 탐색이 가능하다.. -> 이런 방법을 생각
        //뭔가 더 효율적으로 못 짤까 ?를 생각해보자자

       if(!chk[n]) ans = 0;
        //n은 5 이상
        int sum = 5;
        while (left<= n && right <=n){
            if(sum == n){
                ans++;
                sum -= left;        //현재 left에 더해져있는값 뺌
                left++;
                right++;
                while (left<=n &&  !chk[left] ){
                    left++;
                }
                while (right <= n &&!chk[right] ) {
                    right++;
                }
                sum +=right;
            } else if(sum < n){
                right++;
                while (right <= n && !chk[right]) {
                    right++;
                }
                sum+=right;
            } else{
                sum -= left;
                left++;
                while (left<=n && !chk[left] ){
                    left++;
                }
            }
        }
        System.out.println(ans);
    }
}
